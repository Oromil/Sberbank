package com.example.sberbank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sberbank.models.Interactor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Interactor interactor = new Interactor();
    }
}
