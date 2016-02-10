package com.example.claudiocavallaro.progettogad.persistenza;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.claudiocavallaro.progettogad.Activity.CellActivity;
import com.example.claudiocavallaro.progettogad.R;
import com.example.claudiocavallaro.progettogad.modello.ListaGestori;
import com.example.claudiocavallaro.progettogad.modello.Telefono;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by claudiocavallaro on 09/02/16.
 */
public class RestCallCell extends AsyncTask<Object, Void, Object> {

    private static String url = "http://lapromozioneperte.netsons.org/cell.json";
    private ProgressDialog progressDialog;
    private static Context context;
    private CellActivity cellActivity;

    public RestCallCell(CellActivity cellActivity) {
        this.cellActivity = cellActivity;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        RestCallCell.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Caricamento lista smartphone");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        progressDialog.setMessage("Caricamento lista smartphone");
    }

    @Override
    protected Object doInBackground(Object... params) {
        if (ListaGestori.getListaTelefoni().size() == 0) {
            try {
                HttpClient request = new DefaultHttpClient();
                HttpGet get = new HttpGet(url);
                HttpResponse response = request.execute(get);
                int responseCode = response.getStatusLine().getStatusCode();
                if (responseCode == 200) {
                    InputStream inputStream = response.getEntity().getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String s = null;
                    StringBuffer sb = new StringBuffer();
                    while ((s = reader.readLine()) != null) {
                        sb.append(s);
                    }

                    JSONObject obj = new JSONObject(sb.toString());
                    JSONArray array = obj.getJSONArray("Cellulari");
                    for (int i = 0; i < array.length(); i++) {
                        JSONArray id = array.getJSONArray(i);
                        String marchio = id.getJSONObject(0).getString("Marchio");
                        JSONArray tel = id.getJSONArray(1);
                        for (int j = 0; j < tel.length(); j++) {
                            Telefono t = new Telefono(marchio);
                            String nome = tel.getJSONArray(j).getJSONObject(0).getString("Nome");
                            int capienza = tel.getJSONArray(j).getJSONObject(1).getInt("Capienza");
                            double prezzo1 = tel.getJSONArray(j).getJSONObject(2).getDouble("Prezzo1");
                            double prezzo2 = tel.getJSONArray(j).getJSONObject(3).getDouble("Prezzo2");

                            if (marchio.equals("Apple")) {
                                t.setLogo(R.drawable.apple);
                            }
                            if (marchio.equals("Samsung")) {
                                t.setLogo(R.drawable.samsung);
                            }

                            t.setModello(nome);
                            t.setCapienza(capienza);
                            t.setPrezzo1(prezzo1);
                            t.setPrezzo2(prezzo2);

                            ListaGestori.getListaTelefoni().add(t);

                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        progressDialog.dismiss();
        cellActivity.setInterfaccia();
    }
}
