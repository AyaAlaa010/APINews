package com.example.apinews;

import model.NewsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
    @GET ("v2/top-headlines?country=eg&category=sport&apiKey=fa72aea7f1af46a6a45be8aa23e21b64")
    Call<NewsResponse> getBusinessNews();
    @GET ("v2/top-headlines?apiKey=fa72aea7f1af46a6a45be8aa23e21b64")
    Call<NewsResponse> geNews(@Query("country") String country,
                              @Query("category") String category);
}
