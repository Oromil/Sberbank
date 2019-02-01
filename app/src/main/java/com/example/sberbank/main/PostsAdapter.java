package com.example.sberbank.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.Editable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.sberbank.R;
import com.example.sberbank.base.MyImageGetter;
import com.example.sberbank.models.Post;
import org.xml.sax.XMLReader;

import java.util.ArrayList;
import java.util.List;

public class PostsAdapter extends Adapter<PostsAdapter.PostViewHolder> {

    private List<Post> postsList;

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
        Post item = postsList.get(i);
        postViewHolder.tvTitle.setText(item.getTitle());
        postViewHolder.tvContent.setText(Html.fromHtml(item.getDescription(),
                postViewHolder.imageGetter,
                new Html.TagHandler() {
                    @Override
                    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
                    }
                }));

        postViewHolder.tvDate.setText(String.format(postViewHolder.context.getString(R.string.date_text), item.getDate(), item.getTime()));
        postViewHolder.tvTags.setText(String.format(postViewHolder.context.getString(R.string.tags_text), item.getTagsAsString()));
        if (item.getCreator()!=null)
            postViewHolder.tvCreator.setText(String.format(postViewHolder.context.getString(R.string.creator_text), item.getCreator()));
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public void updateData(List<Post> data) {
        postsList.clear();
        postsList.addAll(data);
        notifyDataSetChanged();
    }

    class PostViewHolder extends ViewHolder {

        Context context;
        TextView tvTitle;
        TextView tvContent;
        TextView tvDate;
        ImageView imageView;
        TextView tvTags;
        MyImageGetter imageGetter;
        TextView tvCreator;

        PostViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvContent = itemView.findViewById(R.id.tvContent);
            tvContent.setMovementMethod(LinkMovementMethod.getInstance());
            tvDate = itemView.findViewById(R.id.tvDate);
            imageView = itemView.findViewById(R.id.imageView);
            tvTags = itemView.findViewById(R.id.tvTags);
            imageGetter = new MyImageGetter(tvContent);
            tvCreator = itemView.findViewById(R.id.tvCreator);
        }
    }
}
