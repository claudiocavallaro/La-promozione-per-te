package com.example.claudiocavallaro.progettogad.modello;

import java.util.ArrayList;

/**
 * Created by claudiocavallaro on 27/11/15.
 */
public class ListaGestori {
    private static ArrayList<Gestore> listaGestori = new ArrayList<Gestore>();
    private static ArrayList<Promozione> listaPromozioni = new ArrayList<Promozione>();
    private static ArrayList<Promozione> listaPromoFav = new ArrayList<Promozione>();
    private static ArrayList<Telefono> listaTelefoni = new ArrayList<Telefono>();

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

    public static ArrayList<Telefono> getListaTelefoni() {
        return listaTelefoni;
    }

    public static void setListaTelefoni(ArrayList<Telefono> listaTelefoni) {
        ListaGestori.listaTelefoni = listaTelefoni;
    }

    public Gestore cercaGestore(String nome){
        for (Gestore gestore : ListaGestori.getListaGestori()){
            if (nome.equals(gestore.getNomeGestore())){
                return gestore;
            }
        }
        return null;
    }

    public static Telefono cercaTelefono(String nome){
        for (Telefono telefono : ListaGestori.getListaTelefoni()){
            if (nome.equals(telefono.getTelef())){
                return telefono;
            }
        }
        return null;
    }
}
