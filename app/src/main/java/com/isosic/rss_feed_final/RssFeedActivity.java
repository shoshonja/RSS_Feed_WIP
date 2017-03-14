package com.isosic.rss_feed_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;

import java.util.ArrayList;


public class RssFeedActivity extends AppCompatActivity {

    private ListView lvRSSFeeds;
    private Intent intentSent;
    private Intent intentRecieved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_feed);

        initialize();
        setup();

    }

    private void initialize() {
        lvRSSFeeds = (ListView) findViewById(R.id.lvRSSFeeds);
        intentSent = new Intent(RssFeedActivity.this, WebViewActivity.class);
        intentRecieved = getIntent();
    }


    public void setup() {

        final ArrayList<RssFeedItems> feedArray = (ArrayList<RssFeedItems>) intentRecieved.getSerializableExtra("FEEDS");
        CustomAdapter adapter = new CustomAdapter(RssFeedActivity.this, feedArray);
        lvRSSFeeds.setAdapter(adapter);

        lvRSSFeeds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intentSent.putExtra("URL", feedArray.get((int)l).getLink());
                startActivity(intentSent);
            }
        });
    }
}
