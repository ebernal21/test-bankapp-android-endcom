package com.example.testbankapperickbernal.Presenter;

import static android.os.Build.VERSION_CODES.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.testbankapperickbernal.Models.AccountModel;
import com.example.testbankapperickbernal.Models.BalanceModel;
import com.example.testbankapperickbernal.Models.CardsModel;
import com.example.testbankapperickbernal.Models.MovementModel;

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
    ArrayList<CardsModel> list = new ArrayList<CardsModel>();
    private Context context;
    private Activity activity;
    private ProgressDialog pd;
    AccountModel account = new AccountModel();
    InputStream data = null; //new data received from API
    JSONObject result = null;//JSONobject
    String resultText = "";// result as string
    BalanceModel balanceModel = new BalanceModel();
    ArrayList<MovementModel> movementModels = new ArrayList<MovementModel>();

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

    public void GetCards(RecyclerView recyclerView)
    {
        class LoadCards extends AsyncTask<String, Void, ArrayList<CardsModel>>
        {
            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                pd = new ProgressDialog(context);
            }

            @Override
            protected ArrayList<CardsModel> doInBackground(String... args)
            {
                Log.d("Connecting to API", "Connecting");
                URL url;
                HttpURLConnection connection = null;
                try
                {
                    url = new URL("http://bankapp.endcom.mx/api/bankappTest/tarjetas");
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
                    list.clear();
                    String itemsString = result.getString("tarjetas");
                    JSONArray items = new JSONArray(itemsString);
                    for (int i = 0; i < items.length(); i++)
                    {
                        JSONObject item = items.getJSONObject(i);
                        int id = item.getInt("id");
                        String cardNumber = item.getString("tarjeta");
                        String nombre = item.getString("nombre");
                        int balance = item.getInt("saldo");
                        String estado = item.getString("estado");
                        String tipo = item.getString("tipo");
                        list.add(new CardsModel(id,cardNumber,nombre,balance,estado,tipo));
                    }
                }
                catch (JSONException e)
                {
                    Log.e("JSON Exception", e.toString());
                }
                //return array
                return list;
            }

            @Override
            protected void onPostExecute(ArrayList<CardsModel> list)
            {
                pd.dismiss();
                if (list.size() > 0)
                {
                    MainRecyclerAdapterCards adapter = new MainRecyclerAdapterCards(list,context);
                    recyclerView.setAdapter(adapter);
                }
            }
        }
        new LoadCards().execute();
    }

    /*public void GetAccountBalance(HorizontalScrollView horizontalScrollView, LinearLayout linearLayout)
    {
        //Ejecuta Asyntask para traer datos de cuenta
        class LoadBalance extends AsyncTask<String, Void, BalanceModel>
        {
            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                pd = new ProgressDialog(context);
            }

            @Override
            protected BalanceModel doInBackground(String... args)
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
                    String itemsString = result.getString("saldos");
                    JSONArray items = new JSONArray(itemsString);
                    for (int i = 0; i < items.length(); i++)
                    {
                        JSONObject item = items.getJSONObject(i);
                        int id = item.getInt("id");
                        String cuenta = item.getString("cuenta");
                        int ingresos = item.getInt("ingresos");
                        int saldoGeneral = item.getInt("saldoGeneral");
                        int gastos = item.getInt("gastos");
                        balanceModel.setId(id);
                        balanceModel.setAccount(cuenta);
                        balanceModel.setGeneralBalance(saldoGeneral);
                        balanceModel.setRevenue(ingresos);
                        balanceModel.setExpenses(gastos);
                    }
                }
                catch (JSONException e)
                {
                    Log.e("JSON Exception", e.toString());
                }
                return balanceModel;
            }

            @Override
            protected void onPostExecute(BalanceModel model)
            {
                pd.dismiss();
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                horizontalScrollView.setLayoutParams(layoutParams);

                LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.setLayoutParams(linearParams);

                horizontalScrollView.addView(linearLayout);

            }
        }
        new LoadBalance().execute();
    }*/

    public void GetMovements(RecyclerView recyclerView)
    {
        class LoadMovements extends AsyncTask<String, Void, ArrayList<MovementModel>>
        {
            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                pd = new ProgressDialog(context);
            }

            @Override
            protected ArrayList<MovementModel> doInBackground(String... args)
            {
                Log.d("Connecting to API", "Connecting");
                URL url;
                HttpURLConnection connection = null;
                try
                {
                    url = new URL("http://bankapp.endcom.mx/api/bankappTest/movimientos");
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
                    movementModels.clear();
                    String itemsString = result.getString("movimientos");
                    JSONArray items = new JSONArray(itemsString);
                    for (int i = 0; i < items.length(); i++)
                    {
                        JSONObject item = items.getJSONObject(i);
                        int id = item.getInt("id");
                        String fecha = item.getString("fecha");
                        String descripcion = item.getString("descripcion");
                        Double monto = item.getDouble("monto");
                        String tipo = item.getString("tipo");
                        movementModels.add(new MovementModel(id,fecha,descripcion,monto,tipo));
                    }
                }
                catch (JSONException e)
                {
                    Log.e("JSON Exception", e.toString());
                }
                return movementModels;
            }

            @Override
            protected void onPostExecute(ArrayList<MovementModel> list)
            {
                pd.dismiss();
                if (list.size() > 0)
                {
                    MainRecyclerAdapterMovements adapter = new MainRecyclerAdapterMovements(movementModels,context);
                    recyclerView.setAdapter(adapter);
                }
            }
        }
        new LoadMovements().execute();
    }
}
