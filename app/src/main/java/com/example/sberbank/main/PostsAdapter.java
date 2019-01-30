package com.example.sberbank.main;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.sberbank.R;
import com.example.sberbank.models.PostEntity;
import com.example.sberbank.remote.ImageLoader;
import com.example.sberbank.remote.ResponseCallback;

import java.util.ArrayList;
import java.util.List;

public class PostsAdapter extends Adapter<PostsAdapter.PostViewHolder> {

    private List<PostEntity> postsList;

    public PostsAdapter() {
        postsList = new ArrayList();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = View.inflate(viewGroup.getContext(), R.layout.item, null);
        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final PostViewHolder postViewHolder, int i) {
        postViewHolder.imageView.setImageBitmap(null);
        PostEntity item = postsList.get(i);
        postViewHolder.tvTitle.setText(item.getTitle());
        postViewHolder.tvContent.setText(item.getContent());
        postViewHolder.tvDate.setText(item.getDate());
        postViewHolder.tvTags.setText(item.getTags());
        new ImageLoader(new ResponseCallback<Bitmap>() {
            @Override
            public void onSuccess(Bitmap response) {
                postViewHolder.imageView.setVisibility(View.VISIBLE);
                postViewHolder.imageView.setImageBitmap(response);
            }

            @Override
            public void onFailure(Exception e) {
                postViewHolder.imageView.setVisibility(View.GONE);
            }
        }).execute(item.getImageUrl());
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public void updateData(List<PostEntity> data) {
        postsList.clear();
        postsList.addAll(data);
        notifyDataSetChanged();
    }

    class PostViewHolder extends ViewHolder {

        protected TextView tvTitle;
        protected TextView tvContent;
        protected TextView tvDate;
        protected ImageView imageView;
        TextView tvTags;

        public PostViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvContent = itemView.findViewById(R.id.tvContent);
            tvDate = itemView.findViewById(R.id.tvDate);
            imageView = itemView.findViewById(R.id.imageView);
            tvTags = itemView.findViewById(R.id.tvTags);
        }
    }
}
