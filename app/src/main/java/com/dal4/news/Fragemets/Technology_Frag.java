package com.dal4.news.Fragemets;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.dal4.news.R;
import com.dal4.news.Retrofit.DataBase.DataBaseClient;
import com.dal4.news.Retrofit.Model.NewsModel;
import com.dal4.news.constance.NewsAdapter;
import com.dal4.news.ui.main.PostViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Technology_Frag extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private PostViewModel postViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.technology_frag, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        initPostModel();
    }

    private void initViews() {
        recyclerView = view.findViewById(R.id.technology_RV);
        progressBar = view.findViewById(R.id.PB_Tech);
    }

    private void initPostModel() {
        postViewModel = ViewModelProviders.of(requireActivity()).get(PostViewModel.class);
        postViewModel.getNews("us", "technology");
        postViewModel.getNewsMutableLiveData().observe(getViewLifecycleOwner(), new Observer<NewsModel>() {
            @Override
            public void onChanged(NewsModel newsModel) {
                progressBar.setVisibility(View.GONE);
                recyclerView.setAdapter(new NewsAdapter(newsModel.getArticles(), getContext()));
            }
        });
    }

}
