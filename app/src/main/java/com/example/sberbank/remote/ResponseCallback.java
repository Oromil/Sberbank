package com.example.sberbank.remote;

public interface ResponseCallback<M> {
    void onSuccess(M response);
    void onFailure(Exception e);
}
