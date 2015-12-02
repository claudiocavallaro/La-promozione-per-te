package com.example.claudiocavallaro.progettogad.persistenza;

import android.os.AsyncTask;
import android.util.Log;

import com.example.claudiocavallaro.progettogad.modello.Gestore;
import com.example.claudiocavallaro.progettogad.modello.ListaGestori;

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
 * Created by claudiocavallaro on 27/11/15.
 */

public class RestCall extends AsyncTask<Void, Void, Void> {

    private static String url = "http://192.168.2.8:8182/gad";

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
                    //JSONObject o = array.getJSONObject(i).getJSONObject("ID");
                    JSONArray gestore = array.getJSONObject(i).getJSONArray("ID");
                    for (int j = 0; j < gestore.length(); j++) {
                        String nomeGestore = gestore.getJSONObject(j).getString("Gestore");
                        Gestore g = new Gestore(nomeGestore);
                        ListaGestori.addGestore(g);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
