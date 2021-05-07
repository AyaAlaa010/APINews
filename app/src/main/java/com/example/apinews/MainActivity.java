package com.example.apinews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.Serializable;

import model.Article;
import model.NewsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
RecyclerView recyclerView;
AdapterNews adapterNews;
    private static final String TAG = "MainActivity";
    NewsApi newsApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.myrecycler);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        newsApi = retrofit.create(NewsApi.class);
        getNews("sports");


    }

        public void getNews(String category){
            newsApi.geNews("eg",category).enqueue(new Callback<NewsResponse>() {
                @Override
                public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        NewsResponse newsResponse = response.body();
                        Log.i(TAG, "onResponse " + newsResponse.getArticles().size());

                        adapterNews = new AdapterNews(newsResponse.getArticles(), MainActivity.this, newsInterface);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                        recyclerView.setAdapter(adapterNews);
                    }


//                    for(Article article: newsResponse.getArticles()){
//                        Log.i(TAG, "onResponse: "+article.toString());
//
//                    }
                }



                @Override
                public void onFailure(Call<NewsResponse> call, Throwable t) {
                    String errormessage = t.getLocalizedMessage();
                    Toast.makeText(MainActivity.this, errormessage, Toast.LENGTH_LONG).show();

                }
            }); }


    NewsInterface newsInterface= new NewsInterface() {
        @Override
        public void onNewsClick(Article article) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("News",  article);
            startActivity(intent);

        }

    };

}