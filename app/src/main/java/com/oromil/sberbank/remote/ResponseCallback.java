package com.oromil.sberbank.remote;

public interface ResponseCallback<M> {
    void onSuccess(M response);
    void onFailure(Exception e);
}
