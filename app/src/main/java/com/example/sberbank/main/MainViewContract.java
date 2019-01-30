package com.example.sberbank.main;

import com.example.sberbank.base.ViewContract;
import com.example.sberbank.models.PostEntity;

import java.util.List;

public interface MainViewContract extends ViewContract {

    void showProgress(boolean show);

    void updateData(List<PostEntity> data);
}
