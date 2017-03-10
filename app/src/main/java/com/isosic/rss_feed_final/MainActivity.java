package com.isosic.rss_feed_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView infoTextView;
    private Button btFetch;
    private Button btSelect;
    private String feedSite = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        setupListeners();
    }

    private void initialize() {
        infoTextView = (TextView) findViewById(R.id.tvTitle);
        btFetch = (Button) findViewById(R.id.btFetch);
        btSelect = (Button) findViewById(R.id.btSelect);
        feedSite = "https://www.pinkbike.com/pinkbike_xml_feed.php";
    }

    private void setupListeners() {
        btFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (feedSite != null) {
                    Intent intent = new Intent(MainActivity.this, RssFeedActivity.class);
                    intent.putExtra("FEED_SITE", feedSite);
                    startActivity(intent);
                } else
                    Toast.makeText(MainActivity.this, "Select your website first!", Toast.LENGTH_SHORT).show();

            }
        });

        btSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "This does nothing. Sod off!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
