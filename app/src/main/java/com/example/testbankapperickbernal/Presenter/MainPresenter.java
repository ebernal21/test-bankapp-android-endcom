package com.example.testbankapperickbernal.Presenter;

import static android.os.Build.VERSION_CODES.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testbankapperickbernal.Models.AccountModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainPresenter
{
    private Context context;
    private Activity activity;
    private ProgressDialog pd;
    AccountModel account = new AccountModel();
    InputStream data = null; //new data received from API
    JSONObject result = null;//JSONobject
    String resultText = "";// result as string

    public MainPresenter(Context context)
    {
        this.context = context;
    }

    public MainPresenter(Context context, Activity activity)
    {
        this.context = context;
        this.activity = activity;
    }

    public void GetLastLogin(TextView textView)
    {
        //Ejecuta Asyntask para traer datos de cuenta
        class LoadServices extends AsyncTask<String, Void, AccountModel>
        {
            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                pd = new ProgressDialog(context);
            }

            @Override
            protected AccountModel doInBackground(String... args)
            {
                Log.d("Connecting to API", "Connecting");
                URL url;
                HttpURLConnection connection = null;
                try
                {
                    url = new URL("http://bankapp.endcom.mx/api/bankappTest/cuenta");
                    connection = (HttpURLConnection) url.openConnection();
                    Log.d("Debug", "Connection Established");
                    data = connection.getInputStream();
                    StringBuilder rawData = new StringBuilder();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(data));
                    String dataLine = "";
                    while ((dataLine = reader.readLine()) != null)
                    {
                        rawData.append(dataLine);
                    }
                    resultText = rawData.toString();
                    Log.d("Raw Data Received", resultText);
                }
                catch (MalformedURLException e)
                {
                    Log.e("URL Exception", e.toString());
                }
                catch (IOException e)
                {
                    Log.e("IO Exception", e.toString());
                }
                catch (Exception e)
                {
                    Log.e("Exception", e.toString());
                }
                finally
                {
                    if (connection != null) connection.disconnect();
                }
                //parse JSON
                try
                {
                    result = new JSONObject(resultText);
                    Log.d("Data in JSON Format", result.toString());
                }
                catch (JSONException e)
                {
                    Log.e("JSON Exception", e.toString());
                }
                catch (Exception e)
                {
                    Log.e("Generic Exception", e.toString());
                }
                //parse JSON to class
                try
                {
                        String itemsString = result.getString("cuenta");
                        JSONArray items = new JSONArray(itemsString);
                        for (int i = 0; i < items.length(); i++)
                        {
                            JSONObject item = items.getJSONObject(i);
                            int id = item.getInt("id");
                            String cuenta = item.getString("cuenta");
                            String nombre = item.getString("nombre");
                            String ultimasesion = item.getString("ultimaSesion");
                            account.setId(id);
                            account.setCuenta(cuenta);
                            account.setNombre(nombre);
                            account.setUltimasesion(ultimasesion);
                        }
                }
                catch (JSONException e)
                {
                    Log.e("JSON Exception", e.toString());
                }
                //return array
                return account;
            }

            @Override
            protected void onPostExecute(AccountModel model) {
                pd.dismiss();
                textView.setText(model.getNombre() + "    Ultimo inicio:" +model.getUltimasesion());
            }
        }
        new LoadServices().execute();
    }
}
