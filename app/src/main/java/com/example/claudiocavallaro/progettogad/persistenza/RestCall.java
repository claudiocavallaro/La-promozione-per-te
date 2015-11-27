package com.example.claudiocavallaro.progettogad.persistenza;

import android.os.AsyncTask;

import com.example.claudiocavallaro.progettogad.modello.ListaGestori;

import java.util.List;

/**
 * Created by claudiocavallaro on 27/11/15.
 */
public class RestCall extends AsyncTask<Void, Void, Void> {

    private ListaGestori listaGestori = new ListaGestori();
    private static String url = "192.168.2.8:8182/gad";

    @Override
    protected Void doInBackground(Void... params) {

        return null;
    }
}
