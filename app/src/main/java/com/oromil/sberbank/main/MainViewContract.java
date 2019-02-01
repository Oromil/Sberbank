package com.oromil.sberbank.main;

import com.oromil.sberbank.base.ViewContract;
import com.oromil.sberbank.models.Post;

import java.util.List;

public interface MainViewContract extends ViewContract {

    void showProgress(boolean show);

    void updateData(List<Post> data);

    void showNetworkError();
}
