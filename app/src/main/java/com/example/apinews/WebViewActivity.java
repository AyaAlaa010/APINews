package com.example.apinews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.squareup.picasso.Picasso;

import model.Article;

public class WebViewActivity extends AppCompatActivity {
    private WebView webView;
    AdapterNews adapterNews;
    Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web_view);
        if(getIntent().hasExtra("News")) {

            article = (Article) getIntent().getSerializableExtra("News");
        }

       webView = (WebView) findViewById(R.id.webView1);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);


      //  adapterNews = new AdapterNews(newsResponse.getArticles(), WebViewActivity.this, newsInterface);
        webView.loadUrl(article.getUrl());

    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }
        else{

            super.onBackPressed();

        }
    }
}






