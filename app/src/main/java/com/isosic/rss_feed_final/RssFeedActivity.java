package com.isosic.rss_feed_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class RssFeedActivity extends AppCompatActivity {

    private URL webSiteURL;
    private ListView lvRSSFeeds;
    private RssFeedReader feedReader;

    //public TextView testTextView;
    //TODO custom listView to show all RSS data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_feed);

        initialize();
        SetupListeners();

        feedReader.execute();

    }


    private void initialize() {

        Intent intent = getIntent();
        try {
            webSiteURL = new URL(intent.getStringExtra("FEED_SITE"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //testTextView = (TextView) findViewById(R.id.idTestTextView);
        lvRSSFeeds = (ListView) findViewById(R.id.lvRSSFeeds);
        feedReader = new RssFeedReader(RssFeedActivity.this, webSiteURL);


    }


    private void SetupListeners() {

        lvRSSFeeds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
}
