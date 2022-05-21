package com.example.testbankapperickbernal.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.example.testbankapperickbernal.Presenter.MainPresenter;
import com.example.testbankapperickbernal.R;

public class MainActivity extends AppCompatActivity {

    private MainPresenter mainPresenter;
    TextView txtUserLastLogin;
    RecyclerView recyclerViewCards;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenter(this,MainActivity.this);
        txtUserLastLogin = findViewById(R.id.txtUserLastLogin);
        recyclerViewCards = findViewById(R.id.recyclerViewCards);
        recyclerViewCards.setLayoutManager(new LinearLayoutManager(this));
        mainPresenter.GetLastLogin(txtUserLastLogin);
        mainPresenter.GetCards(recyclerViewCards);
    }
}