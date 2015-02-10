package com.example.JsonAsync;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.example.JsonAsync.models.BugsObj;

import java.util.ArrayList;

/**
 * Created by samson on 10.02.2015.
 */
public class ReportAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<BugsObj> report;

    public ReportAdapter(Context _context, ArrayList<BugsObj> _report) {
        mContext = _context;
        report = _report;
    }


    @Override
    public int getCount() {
        return report.size();
    }

    @Override
    public Object getItem(int position) {
        return report.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BugView bugView = new BugView(mContext);
        BugsObj bug = report.get(position);
        bugView.setDataOfBug(bug);

        return bugView;
    }
}
