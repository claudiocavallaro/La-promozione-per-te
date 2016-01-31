package com.example.claudiocavallaro.progettogad.modello;

import java.util.ArrayList;

/**
 * Created by claudiocavallaro on 27/11/15.
 */
public class ListaGestori {
    private static ArrayList<Gestore> listaGestori = new ArrayList<Gestore>();
    private static ArrayList<Promozione> listaPromozioni = new ArrayList<Promozione>();
    private static ArrayList<Promozione> listaPromoFav = new ArrayList<Promozione>();

    public static ArrayList<Gestore> getListaGestori() {
        return listaGestori;
    }

    public static void addGestore(Gestore gestore) {
        listaGestori.add(gestore);
    }


    public ListaGestori(){}

    public static ArrayList<Promozione> getListaPromozioni() {
        return listaPromozioni;
    }

    public static void setListaPromozioni(ArrayList<Promozione> listaPromozioni) {
        ListaGestori.listaPromozioni = listaPromozioni;
    }


    public static ArrayList<Promozione> getListaPromoFav() {
        return listaPromoFav;
    }

    public static void setListaPromoFav(ArrayList<Promozione> listaPromoFav) {
        ListaGestori.listaPromoFav = listaPromoFav;
    }
}
