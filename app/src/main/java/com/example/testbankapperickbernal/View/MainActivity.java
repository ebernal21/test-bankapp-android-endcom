package com.example.testbankapperickbernal.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.example.testbankapperickbernal.Presenter.MainPresenter;
import com.example.testbankapperickbernal.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private MainPresenter mainPresenter;
    TextView txtUserLastLogin;
    RecyclerView recyclerViewCards;
    //HorizontalScrollView horizontalScrollView;
    RecyclerView recyclerViewMovements;
    TextView txtAgregar;

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
        //horizontalScrollView = findViewById(R.id.horizontalScrollView);
        recyclerViewMovements = findViewById(R.id.recyclerViewMovements);
        recyclerViewMovements.setLayoutManager(new LinearLayoutManager(this));
        mainPresenter.GetMovements(recyclerViewMovements);
        txtAgregar = findViewById(R.id.textView3);
        txtAgregar.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.textView3:
                mainPresenter.OpenAddWindow();
                break;
        }
    }

}