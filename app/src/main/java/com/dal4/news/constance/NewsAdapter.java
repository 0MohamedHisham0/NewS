package com.dal4.news.constance;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dal4.news.R;
import com.dal4.news.Retrofit.Model.NewsModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<VH> {

    private List<NewsModel.ArticlesDetail> list;
    private Context context;

    public NewsAdapter(List<NewsModel.ArticlesDetail> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        final NewsModel.ArticlesDetail newsModel = list.get(position);

        String title = newsModel.getTitle();
        String Author = newsModel.getAuthor();
        String Desc = newsModel.getDescription();
        String Date = newsModel.getPublishedAt();
        String URLImage = newsModel.getUrlToImage();


        loadImage(holder.imageView, URLImage, holder.progressBarItem);
        holder.title.setText(title);
        holder.author.setText(Author);
        holder.description.setText(Desc);
        holder.date.setText(Date);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(newsModel.getUrl()); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private void loadImage(final ImageView imageView, final String imageUrl, final ProgressBar progressBar) {
        Picasso.get()
                .load(imageUrl)
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(imageView, new com.squareup.picasso.Callback() {

                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {
                        String updatedImageUrl;
                        if (imageUrl.contains("https")) {
                            updatedImageUrl = imageUrl.replace("https", "http");
                        } else {
                            updatedImageUrl = imageUrl.replace("http", "https");

                        }
                        loadImage(imageView, updatedImageUrl, progressBar);
                    }
                });
    }

    public List<NewsModel.ArticlesDetail> getList() {
        return list;
    }

    public void setList(List<NewsModel.ArticlesDetail> list) {
        this.list = list;
    }
}

class VH extends RecyclerView.ViewHolder {

    TextView title, author, date, description;
    ImageView imageView;
    Button button;
    ProgressBar progressBarItem;

    public VH(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.Title_item);
        author = itemView.findViewById(R.id.Author_item);
        date = itemView.findViewById(R.id.Date);
        description = itemView.findViewById(R.id.Description_item);
        imageView = itemView.findViewById(R.id.Image_item);
        button = itemView.findViewById(R.id.ShowMore_item);
        progressBarItem = itemView.findViewById(R.id.PB_Item);

    }


}