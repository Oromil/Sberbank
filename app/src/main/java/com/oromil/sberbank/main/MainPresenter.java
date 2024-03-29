package com.oromil.sberbank.main;

import com.oromil.sberbank.base.BasePresenter;
import com.oromil.sberbank.models.Rss;
import com.oromil.sberbank.remote.HabrPostsInteractor;
import com.oromil.sberbank.remote.ResponseCallback;

public class MainPresenter extends BasePresenter<MainViewContract> {

    private HabrPostsInteractor mHabrPostsInteractor;

    MainPresenter() {
        mHabrPostsInteractor = HabrPostsInteractor.getInstance();
    }

    @Override
    public void onViewAttached() {
        loadData();
    }

    void loadData() {
        mHabrPostsInteractor.getData(new ResponseCallback<Rss>() {
            @Override
            public void onSuccess(Rss response) {
                mView.updateData(response.getPosts());
                mView.showProgress(false);
            }

            @Override
            public void onFailure(Exception e) {
                mView.showNetworkError();
                mView.showProgress(false);
                e.printStackTrace();
            }
        });
    }
}
