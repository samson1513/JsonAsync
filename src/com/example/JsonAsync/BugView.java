package com.example.JsonAsync;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.JsonAsync.models.BugsObj;

/**
 * Created by samson on 10.02.2015.
 */
public class BugView extends LinearLayout {

    private TextView tvData;

    public BugView(Context context) {
        super(context);
        inflate(context,R.layout.bug_view,this);

        tvData = (TextView)findViewById(R.id.tvData);

    }

    public void setDataOfBug(BugsObj _bug){

        String str = "attachment_id: " + _bug.attachment_id + '\n' +
                "author: " + _bug.author + '\n' +
                "bug_id: " + String.valueOf(_bug.bug_id) + '\n' +
                "creation_time: " + _bug.creation_time + '\n' +
                "creator: " + _bug.creator + '\n' +
                "id: " + String.valueOf(_bug.id) + '\n' +
                "id_private: " + String.valueOf(_bug.is_private) + '\n' +
                "raw_text: " + _bug.raw_text + '\n' +
                "tags: " + _bug.tags + '\n' +
                "text: " + _bug.text + '\n' +
                "time: " + _bug.time;

        tvData.setText(str);

    }
}
