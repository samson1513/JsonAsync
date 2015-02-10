package com.example.JsonAsync;

import android.os.AsyncTask;
import android.util.Log;
import com.example.JsonAsync.models.BugsObj;
import com.example.JsonAsync.models.CommentObj;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;

/**
 * Created by samson on 06.02.2015.
 */
public class ParseAsync extends AsyncTask<Void,Void,CommentObj> {


    @Override
    protected CommentObj doInBackground(Void... params) {

        CommentObj report = null;
        String json = "";

        try {
            json = loadJson();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            report = parseJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return report;
    }

//    public ReportObj getReport() throws IOException, URISyntaxException, JSONException {
//
//        String json = loadJson();
//
//        ReportObj reportObj = parseReport(json);
//
//        return reportObj;
//    }

    //region GetJsonToString
    public String loadJson() throws URISyntaxException, IOException {
        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 15000);
        HttpConnectionParams.setSoTimeout(httpParams, 15000);

        URI uri = new URI("https://bugzilla.mozilla.org/rest/bug/707428/comment");

        HttpClient httpClient = new DefaultHttpClient(httpParams);
        HttpGet httpGet = new HttpGet(uri);
        HttpResponse response = httpClient.execute(httpGet);

        String reportJson = inputStreamToString(response.getEntity().getContent());

        return reportJson;
    }
    private String inputStreamToString(InputStream is) throws IOException {
        String str = "";
        String line = "";

        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        while ((line = rd.readLine()) != null) {
            str += line;
        }

        return str;
    }
    //endregion

    //region Parsing
    public CommentObj parseJson(String _json) throws JSONException {

        JSONObject rootObj = new JSONObject(_json);

        JSONObject jBugs = rootObj.getJSONObject("bugs");
        Iterator key = jBugs.keys();
        String currentKey = String.valueOf(key.next());

        JSONObject jCom = jBugs.optJSONObject(currentKey);

        JSONArray jComArray = jCom.getJSONArray("comments");
        CommentObj commentObj = new CommentObj();

        for(int i = 0; i < jComArray.length();++i){

            Gson gson = new Gson();
            BugsObj bug = gson.fromJson(jComArray.getString(i),BugsObj.class);
            commentObj.comments.add(bug);
        }

        return commentObj;
    }

    @Override
    protected void onPostExecute(CommentObj commentObj) {
        super.onPostExecute(commentObj);
        Log.d("Async", "parse is finished");
    }

    //endregion
}
