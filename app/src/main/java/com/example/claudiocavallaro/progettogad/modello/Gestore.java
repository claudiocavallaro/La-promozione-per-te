package com.example.claudiocavallaro.progettogad.modello;

import java.util.ArrayList;

/**
 * Created by claudiocavallaro on 27/11/15.
 */
public class Gestore {
    private String nomeGestore;
    private ArrayList<Promozione> listaPromo = new ArrayList<Promozione>();

    public Gestore(String nomeGestore) {
        setNomeGestore(nomeGestore);
    }

    public String getNomeGestore() {
        return nomeGestore;
    }

    public void setNomeGestore(String nomeGestore) {
        this.nomeGestore = nomeGestore;
    }

    public ArrayList<Promozione> getListaPromo() {
        return listaPromo;
    }

    public void addPromo(Promozione promo) {
        listaPromo.add(promo);
    }

    public String toString() {
        return this.nomeGestore;
    }
}
