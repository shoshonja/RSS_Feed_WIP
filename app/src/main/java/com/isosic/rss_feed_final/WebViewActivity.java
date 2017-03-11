package com.isosic.rss_feed_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;
    private Intent intent;
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = (WebView) findViewById(R.id.wvWebView);
        intent = getIntent();
        url = intent.getStringExtra("URL");

        webView.loadUrl(url);
        finish();

    }

}
