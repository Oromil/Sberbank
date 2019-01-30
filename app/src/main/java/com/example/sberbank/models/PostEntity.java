package com.example.sberbank.models;

import android.support.annotation.NonNull;

public class PostEntity {

    private String title;
    private String content;
    private String creator;
    private String tags;
    private String date;
    private String time;
    private String imageUrl;
    private String link;

    private PostEntity(@NonNull Builder builder) {
        this.title = builder.title;
        this.content = builder.content;
        this.link = builder.link;
        this.creator = builder.creator;
        this.tags = builder.tags;
        this.date = builder.date;
        this.imageUrl = builder.imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTags() {
        return tags;
    }

    public String getLink() {
        return link;
    }

    public String getCreator() {
        return creator;
    }

    public static class Builder {
        private String title;
        private String content;
        private String creator;
        private String tags;
        private String date;
        private String time;
        private String imageUrl;
        private String link;

        public Builder(@NonNull String title, @NonNull String link) {
            this.title = title;
            this.link = link;
        }

        public Builder content(String content){
            this.content = content;
            return this;
        }

        public Builder date(String date) {
            this.date = date;
            return this;
        }

        public Builder tags(String tags) {
            this.tags = tags;
            return this;
        }

        public Builder creator(String creator) {
            this.creator = creator;
            return this;
        }

        public Builder imageUtl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public PostEntity build() {
            return new PostEntity(this);
        }
    }
}
