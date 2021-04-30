package com.dal4.news.Retrofit.DataBase;

import com.dal4.news.Retrofit.Model.NewsModel;
import com.dal4.news.Retrofit.Network.Remote.NewsDao;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataBaseClient {


    private final static String BASE_URL = "http://newsapi.org/";
    private NewsDao newsDao;


    private static DataBaseClient retrofitClient;

    private DataBaseClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        newsDao = retrofit.create(NewsDao.class);
    }

    public static DataBaseClient getInstance() {
        if (retrofitClient == null) {
            retrofitClient = new DataBaseClient();
        }

        return retrofitClient;
    }

    public Call<NewsModel> getNews(String country, String category, String key) {
        return newsDao.getNews(country, category, key);
    }
}