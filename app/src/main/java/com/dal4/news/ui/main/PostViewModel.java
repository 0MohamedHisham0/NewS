package com.dal4.news.ui.main;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dal4.news.Retrofit.DataBase.DataBaseClient;
import com.dal4.news.Retrofit.Model.NewsModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends ViewModel {
    MutableLiveData<NewsModel> newsMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<NewsModel> getNewsMutableLiveData() {
        return newsMutableLiveData;
    }


    public void getNews(String country, String Category) {
        DataBaseClient.getInstance().getNews(country, Category, "73ecf74251894d8bb7d7def422fd04a6").enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                if (response.code() == 200 && response.isSuccessful()) {
                    newsMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {

            }
        });

    }
}
