package com.isosic.rss_feed_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;


public class RssFeedActivity extends AppCompatActivity {

    private ListView lvRSSFeeds;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_feed);

        initialize();
        setup();

    }

    private void initialize() {
        lvRSSFeeds = (ListView) findViewById(R.id.lvRSSFeeds);
        intent = new Intent(RssFeedActivity.this, WebViewActivity.class);
    }


    public void setup() {

        CustomAdapter adapter = new CustomAdapter(RssFeedActivity.this, RssFeedReader.feedItemList);
        lvRSSFeeds.setAdapter(adapter);

        lvRSSFeeds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent.putExtra("URL", RssFeedReader.feedItemList.get((int)l).getLink());
                startActivity(intent);
            }
        });
    }
}
