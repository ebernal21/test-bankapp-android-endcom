package com.example.testbankapperickbernal.Presenter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.example.testbankapperickbernal.Models.AddCardModel;
import com.example.testbankapperickbernal.R;
import com.google.gson.Gson;

public class RegisterCardPresenter
{
    private Context context;
    private Activity activity;

    public RegisterCardPresenter(Context context)
    {
        this.context = context;
    }

    public RegisterCardPresenter(Context context, Activity activity)
    {
        this.context = context;
        this.activity = activity;
    }

    public void AddCard(EditText etxtTarjeta,EditText etxtCuenta,EditText etxtIssure,EditText etxtNombre,EditText etxtMarca,EditText etxtEstatus,EditText etxtSaldo,EditText etxtTipo)
    {
        AddCardModel model = new AddCardModel();
        model.setTarjeta(etxtTarjeta.getText().toString());
        model.setCuenta(etxtCuenta.getText().toString());
        model.setIssure(etxtIssure.getText().toString());
        model.setEstatus(etxtEstatus.getText().toString());
        model.setNombre(etxtNombre.getText().toString());
        model.setMarca(etxtMarca.getText().toString());
        model.setSaldo(Double.parseDouble(etxtSaldo.getText().toString()));
        model.setMarca(etxtTipo.getText().toString());

        Gson gson = new Gson();
        String json = gson.toJson(model);

        AlertDialog alerta = new AlertDialog.Builder(context).create();
        alerta.setTitle("JSON.");
        alerta.setMessage(json);
        alerta.setButton("OK", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
               return;
            }
        });
        alerta.show();
    }

    public void Cancel()
    {


    }
}
