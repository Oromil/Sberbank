package com.example.sberbank.remote;

import com.example.sberbank.models.Rss;

public class HabrPostsInteractor {

    private final String URL = "https://habrahabr.ru/rss/hubs/all/";

    private static HabrPostsInteractor instance;

    private HabrPostsInteractor() {
        if (instance != null)
            throw new RuntimeException();
    }

    public static HabrPostsInteractor getInstance() {
        if (instance == null) {
            return new HabrPostsInteractor();
        }
        return instance;
    }

    public void getData(ResponseCallback<Rss> callback) {
        new LoadingTask<>(Rss.class, callback).execute(URL);
    }
}
