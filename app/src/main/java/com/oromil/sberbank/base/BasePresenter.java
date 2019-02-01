package com.oromil.sberbank.base;

public abstract class BasePresenter<V extends ViewContract> implements Presenter<V> {

    protected V mView;

    @Override
    public void attachView(V view) {
        mView = view;
        onViewAttached();
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
