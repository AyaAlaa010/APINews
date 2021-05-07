package com.example.apinews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import model.Article;

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.newsHolder>{
    List<Article> articleList;
    Context context;
    NewsInterface newsInterface;
    public AdapterNews(List<Article> articleList,Context context, NewsInterface newsInterface) {
        this.context=context;
        this.articleList = articleList;
        this.newsInterface=newsInterface;
    }

    @NonNull
    @Override
    public newsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.items_specific_name,parent,false);
        newsHolder mynewsHolder=new newsHolder(view);
        return mynewsHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull newsHolder holder, int position) {
        Article article=articleList.get(position) ;
        holder.txtTitle.setText(article.getTitle());
        Picasso.get().load(article.getUrlToImage()).into(holder.imgNews);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsInterface.onNewsClick(article);

            }
        });



    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }


    public class newsHolder extends RecyclerView.ViewHolder{
ImageView imgNews;
TextView txtTitle;

    public newsHolder(@NonNull View itemView) {
        super(itemView);
        imgNews=itemView.findViewById(R.id.img_news);
        txtTitle=itemView.findViewById(R.id.title_news);
    }
}



}
