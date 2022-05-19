package com.example.testbankapperickbernal.Presenter;

import static android.os.Build.VERSION_CODES.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
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

    public void GetLastLogin()
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
                    String success = result.getString("status");
                    if (TextUtils.equals(success,"Ok"))
                    {
                        String itemsString = result.getString("cuenta");
                        JSONObject results = new JSONObject(itemsString);
                            int id = results.getInt("id");
                            String cuenta = results.getString("cuenta");
                            String nombre = results.getString("nombre");
                            String ultimasesion = results.getString("ultimasesion");
                            account.setId(id);
                            account.setCuenta(cuenta);
                            account.setNombre(nombre);
                            account.setUltimasesion(ultimasesion);
                    }
                    else
                    {
                        Toast.makeText(context, "Error.",Toast.LENGTH_SHORT).show();
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
                activity.getActionBar().setTitle(model.getNombre() + "    Ultimo inicio:" +model.getUltimasesion() );
            }
        }
        new LoadServices().execute();
    }
    }
}
