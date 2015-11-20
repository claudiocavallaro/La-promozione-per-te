package com.example.claudiocavallaro.progettogad.modello;

import java.util.ArrayList;

/**
 * Created by claudiocavallaro on 22/10/15.
 */
public class Compagnie {

    private ArrayList<Gestore> listaGestori = new ArrayList<Gestore>();

    public ArrayList<Gestore> getListaGestori() {
        return listaGestori;
    }

    public void setListaGestori(ArrayList<Gestore> listaGestori) {
        this.listaGestori = listaGestori;
    }

    public void addGestore(Gestore gestore){
        this.listaGestori.add(gestore);
    }

    public Gestore cercaGestore(String nome){
        if (listaGestori.isEmpty()){
            return null;
        }
        for (Gestore gestore : listaGestori){
            if (gestore.getNome().equals(nome)){
                return gestore;
            }
        }
        return null;
    }
}
