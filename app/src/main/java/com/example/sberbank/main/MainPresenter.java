package com.example.sberbank.main;

import android.util.Log;

import com.example.sberbank.base.BasePresenter;
import com.example.sberbank.models.Post;
import com.example.sberbank.models.PostEntity;
import com.example.sberbank.models.Rss;
import com.example.sberbank.remote.HabrPostsInteractor;
import com.example.sberbank.remote.ResponseCallback;

import java.util.ArrayList;
import java.util.List;

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
                List<PostEntity>postEntities = new ArrayList<>();

                for (Post post: response.getPosts()){
                    postEntities.add(PostMapper.map(post));
                }


                mView.updateData(postEntities);
                mView.showProgress(false);
            }

            @Override
            public void onFailure(Exception e) {

                e.printStackTrace();
            }
        });
    }
}
