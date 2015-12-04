package com.example.claudiocavallaro.progettogad.persistenza;

import android.os.AsyncTask;
import android.util.Log;

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

public class RestCall extends AsyncTask<Void, Void, Void> {

    private static String url = "http://192.168.2.8:8182/gad";
    private ListaGestori listaGestori = new ListaGestori();

    @Override
    protected Void doInBackground(Void... params) {
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
                    JSONArray promo = id.getJSONArray(1);
                    for (int j = 0; j < promo.length(); j++) {
                        Promozione p = new Promozione();
                        String nomeP = promo.getJSONArray(j).getJSONObject(0).getString("Nome");
                        String costoP = promo.getJSONArray(j).getJSONObject(1).getString("Costo");
                        String durataP = promo.getJSONArray(j).getJSONObject(2).getString("Durata");
                        String rapportoP = promo.getJSONArray(j).getJSONObject(3).getString("RapportoQP");

                        p.setNome(nomeP);
                        p.setCosto(new Double(costoP));
                        p.setDurata(durataP);
                        p.setRapportoQP(new Double(rapportoP));

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
                        System.out.println(p);
                        g.addPromo(p);
                    }
                    ListaGestori.addGestore(g);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
