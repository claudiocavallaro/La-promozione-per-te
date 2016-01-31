package com.example.claudiocavallaro.progettogad.persistenza;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.claudiocavallaro.progettogad.Activity.MainActivity;
import com.example.claudiocavallaro.progettogad.Activity.SplashScrennActivity;

import com.example.claudiocavallaro.progettogad.R;
import com.example.claudiocavallaro.progettogad.modello.Caratteristiche;
import com.example.claudiocavallaro.progettogad.modello.Gestore;
import com.example.claudiocavallaro.progettogad.modello.ListaGestori;
import com.example.claudiocavallaro.progettogad.modello.Promozione;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

/**
 * Created by claudiocavallaro on 27/11/15.
 */

public class RestCall extends AsyncTask<Object, Void, Object> {

    private static String url = "http://192.168.2.8:8182/gad";

    private ProgressDialog progressDialog;
    private ProgressBar spinner;
    private static Context context;
    private SplashScrennActivity mActivity;


    public RestCall(SplashScrennActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        spinner = (ProgressBar) mActivity.findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);
        /*progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Caricamento");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();*/
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        //progressDialog.setMessage("Caricamento");
    }

    @Override
    protected Object doInBackground(Object... params) {
        System.out.println("Eseguo restcall");
        if (ListaGestori.getListaGestori().size() == 0){
            System.out.println("è vuoto proseguo");
            try {
                HttpClient request = new DefaultHttpClient();
                HttpGet get = new HttpGet(url);
                HttpResponse response = request.execute(get);
                int responseCode = response.getStatusLine().getStatusCode();
                if (responseCode == 200) {
                    InputStream istream = response.getEntity().getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(istream));
                    String s = null;
                    StringBuffer sb = new StringBuffer();
                    while ((s = reader.readLine()) != null) {
                        sb.append(s);
                    }

                    JSONObject obj = new JSONObject(sb.toString());
                    JSONArray array = obj.getJSONArray("Progetto");
                    for (int i = 0; i < array.length(); i++) {
                        JSONArray id = array.getJSONArray(i);
                        String nome = id.getJSONObject(0).getString("Gestore");
                        Gestore g = new Gestore(nome);
                        if (nome.equals("VODAFONE")) {
                            g.setLogo(R.drawable.vodata);
                        }
                        if (nome.equals("TIM")) {
                            g.setLogo(R.drawable.tim);
                        }
                        if (nome.equals("WIND")) {
                            g.setLogo(R.drawable.windta);
                        }
                        if (nome.equals("TRE")) {
                            g.setLogo(R.drawable.treta);
                        }
                        JSONArray promo = id.getJSONArray(1);
                        for (int j = 0; j < promo.length(); j++) {
                            Promozione p = new Promozione();
                            String nomeP = promo.getJSONArray(j).getJSONObject(0).getString("Nome");
                            String costoP = promo.getJSONArray(j).getJSONObject(1).getString("Costo");
                            String durataP = promo.getJSONArray(j).getJSONObject(2).getString("Durata");
                            String rapportoP = promo.getJSONArray(j).getJSONObject(3).getString("RapportoQP");
                            String infoP = promo.getJSONArray(j).getJSONObject(4).getString("Informazioni");

                            p.setNome(nomeP);
                            double costo = new Double(costoP);
                            p.setCosto(((int) costo));
                            p.setDurata(durataP);
                            p.setRapportoQP(new Double(rapportoP));
                            p.setInfo(infoP);

                            JSONArray car = promo.getJSONArray(j).getJSONArray(5);
                            for (int y = 0; y < car.length(); y++) {
                                JSONObject ob = car.getJSONObject(y);
                                Iterator<String> keys = ob.keys();
                                while (keys.hasNext()) {
                                    String key = keys.next();
                                    String quantita = ob.getString(key);
                                    Caratteristiche caratteristiche = new Caratteristiche(key, quantita);
                                    p.addCaratteristica(caratteristiche);
                                }
                            }

                            p.setGestore(g);
                            //System.out.println(p);
                            g.addPromo(p);
                        }
                        ListaGestori.addGestore(g);
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("è pieno dovrei fermarmi");
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        //progressDialog.dismiss();
        spinner.setVisibility(View.INVISIBLE);
        Intent i = new Intent(mActivity.getApplicationContext(), MainActivity.class);
        context.startActivity(i);
        mActivity.finish();
    }

    public Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        RestCall.context = context;
    }
}
