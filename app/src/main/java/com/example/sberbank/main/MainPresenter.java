package com.example.sberbank.main;

import android.util.Log;

import com.example.sberbank.base.BasePresenter;
import com.example.sberbank.models.Rss;
import com.example.sberbank.remote.HabrPostsInteractor;
import com.example.sberbank.remote.ResponseCallback;

public class MainPresenter extends BasePresenter<MainViewContract> {

    private HabrPostsInteractor mHabrPostsInteractor;

    public MainPresenter(){
        mHabrPostsInteractor = HabrPostsInteractor.getInstance();
    }

    @Override
    public void onViewAttached() {
        mHabrPostsInteractor.getData(new ResponseCallback<Rss>() {
            @Override
            public void onSuccess(Rss response) {
                Log.d("", "");
                mView.updateData(response.getPosts());
                mView.showProgress(false);
            }

            @Override
            public void onFailure(Exception e) {

                e.printStackTrace();
            }
        });
    }
}
