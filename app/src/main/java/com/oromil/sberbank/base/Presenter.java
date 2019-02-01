package com.oromil.sberbank.base;

public interface Presenter<V extends ViewContract> {

    void attachView(V view);
    void detachView();
    void onViewAttached();
}
