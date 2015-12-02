package com.example.claudiocavallaro.progettogad.persistenza;

import android.os.AsyncTask;

import com.example.claudiocavallaro.progettogad.modello.ListaGestori;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by claudiocavallaro on 27/11/15.
 */

public class RestCall extends AsyncTask<Void, Void, Void> {

    private ListaGestori listaGestori = new ListaGestori();
    private static String url = "192.168.2.8:8182/gad";

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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
