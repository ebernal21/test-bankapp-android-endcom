package com.example.testbankapperickbernal.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.testbankapperickbernal.Presenter.MainPresenter;
import com.example.testbankapperickbernal.Presenter.RegisterCardPresenter;
import com.example.testbankapperickbernal.R;

public class RegisterCardView extends AppCompatActivity implements View.OnClickListener{

    RegisterCardPresenter registerCardPresenter;
    Button btnCancel;
    Button btnAdd;
    EditText etxtTarjeta;
    EditText etxtCuenta;
    EditText etxtIssure;
    EditText etxtNombre;
    EditText etxtMarca;
    EditText etxtEstatus;
    EditText etxtSaldo;
    EditText etxtTipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_card_view);
        registerCardPresenter = new RegisterCardPresenter(this, RegisterCardView.this);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
        etxtTarjeta = findViewById(R.id.etxtTarjeta);
        etxtCuenta = findViewById(R.id.etxtCuenta);
        etxtIssure= findViewById(R.id.etxtIssure);
        etxtNombre = findViewById(R.id.etxtNombre);
        etxtMarca = findViewById(R.id.etxtMarca);
        etxtEstatus = findViewById(R.id.etxtEstatus);
        etxtSaldo = findViewById(R.id.etxtSaldo);
        etxtTipo= findViewById(R.id.etxtTipo);
        btnAdd.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnAdd:
                registerCardPresenter.AddCard(etxtTarjeta,etxtCuenta,etxtIssure,etxtNombre,etxtMarca,etxtEstatus,etxtSaldo,etxtTipo);
                break;
            case R.id.btnCancel:
                registerCardPresenter.Cancel();
                break;
        }
    }
}