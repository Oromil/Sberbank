package com.example.sberbank.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ProgressBar;
import com.example.sberbank.R;

public class MainActivity extends AppCompatActivity implements MainViewContract {

    private MainPresenter mPresenter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        mPresenter = new MainPresenter();
        mPresenter.attachView(this);
    }

    private void initViews(){
        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    public void showProgress(boolean show) {
        progressBar.setVisibility(View.GONE);
    }
}
