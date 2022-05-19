package com.example.testbankapperickbernal.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;

import com.example.testbankapperickbernal.Presenter.MainPresenter;
import com.example.testbankapperickbernal.R;

public class MainActivity extends AppCompatActivity {

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenter(this,MainActivity.this);
        mainPresenter.GetLastLogin();
    }
}