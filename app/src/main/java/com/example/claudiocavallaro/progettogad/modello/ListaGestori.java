package com.example.claudiocavallaro.progettogad.modello;

import java.util.ArrayList;

/**
 * Created by claudiocavallaro on 27/11/15.
 */
public class ListaGestori {
    private static ArrayList<Gestore> listaGestori = new ArrayList<Gestore>();

    public static ArrayList<Gestore> getListaGestori() {
        return listaGestori;
    }

    public static void addGestore(Gestore gestore) {
        listaGestori.add(gestore);
    }
}
