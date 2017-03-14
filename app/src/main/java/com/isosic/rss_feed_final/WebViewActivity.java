package com.isosic.rss_feed_final;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;
    private Intent intent;
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        initialize();
        display();
        //finish();

    }



    private void initialize() {
        webView = (WebView) findViewById(R.id.wvWebView);
        intent = getIntent();
        url = intent.getStringExtra("URL");
    }

    private void display() {
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

    }

}
