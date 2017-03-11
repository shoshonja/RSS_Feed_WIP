package com.isosic.rss_feed_final;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Button btFetch;
    //private Button btSelect;
    private String feedSite = null;
    private RssFeedReader rssFeedReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        setupListeners();

    }

    private void initialize() {
        btFetch = (Button) findViewById(R.id.btFetch);
        //btSelect = (Button) findViewById(R.id.btSelect);
        feedSite = "https://www.pinkbike.com/pinkbike_xml_feed.php";
    }

    private void setupListeners() {
        btFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (feedSite != null) {
                    try {
                        URL feedSiteUrl = new URL(feedSite);
                        rssFeedReader = new RssFeedReader(MainActivity.this,feedSiteUrl);
                        rssFeedReader.execute();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }

                } else
                    Toast.makeText(MainActivity.this, "Select your website first!", Toast.LENGTH_SHORT).show();

            }
        });
/*
        btSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "This does nothing. Sod off!", Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}
