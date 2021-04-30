package com.dal4.news.Retrofit.Network.Remote;

import com.dal4.news.Retrofit.Model.NewsModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsDao {

    @GET("v2/top-headlines")
    Call<NewsModel> getNews(
            @Query("country") String country,
            @Query("category") String category,
            @Query("apiKey") String apiKey
    );
}