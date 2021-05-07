package com.example.apinews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import model.Article;
import model.NewsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondActivity extends AppCompatActivity {
ImageView imgDetails;
TextView titleDetails,descriptionDetails;
    private static final String TAG = "SecondActivity";

    Article article;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        imgDetails=(ImageView) findViewById(R.id.img_details);
        titleDetails=(TextView) findViewById(R.id.txt_details_title);
        descriptionDetails=(TextView) findViewById(R.id.txt_details_description);
//AdapterNews adapterNews=new AdapterNews(articleList,SecondActivity.this,newsInterface);
                    if(getIntent().hasExtra("News")) {

                        article = (Article) getIntent().getSerializableExtra("News");
                    }
                  titleDetails.setText(article.getTitle());
                descriptionDetails.setText(article.getDescription());
                Picasso.get().load(article.getUrlToImage()).into(imgDetails);

        imgDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsInterface.onNewsClick(article);


            }
        });}

   NewsInterface newsInterface= new NewsInterface() {
        @Override
        public void onNewsClick(Article article) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(article.getUrl()));
            startActivity(i);}};



//            Intent intent = new Intent(SecondActivity.this, WebViewActivity.class);
//            intent.putExtra("News",  article);
//            startActivity(intent);








                   // adapterNews = new AdapterNews(newsResponse.getArticles(), MainActivity.this, newsInterface);
                  //  recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                  //  recyclerView.setAdapter(adapterNews);



//                    for(Article article: newsResponse.getArticles()){
//                        Log.i(TAG, "onResponse: "+article.toString());
//
//                    }


//        Article article=articleList.get(position) ;
//     titleDetails.setText(article.getTitle());
//      descriptionDetails.setText(article.getDescription());
//       Picasso.get().load(article.getUrlToImage()).into(imgDetails);
    }
