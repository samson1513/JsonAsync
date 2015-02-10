package com.example.JsonAsync;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import com.example.JsonAsync.models.CommentObj;

import java.util.concurrent.ExecutionException;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private ListView lvReport;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        lvReport = (ListView)findViewById(R.id.lvReport);
        try {
            prepareList();
        } catch (ExecutionException e) {
            e.printStackTrace();
            Log.d("List", "Execution");
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.d("List","Interrupted");
        }

    }

    private void prepareList() throws ExecutionException, InterruptedException {

        CommentObj commentObj = new ParseAsync().execute().get();
        ReportAdapter reportAdapter = new ReportAdapter(this,commentObj.comments);
        lvReport.setAdapter(reportAdapter);

    }
}
