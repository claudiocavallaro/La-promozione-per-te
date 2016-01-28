package com.example.claudiocavallaro.progettogad.modello;

import java.util.ArrayList;

/**
 * Created by claudiocavallaro on 27/11/15.
 */
public class Gestore {
    private String nomeGestore;
    private int logo;
    private ArrayList<Promozione> listaPromo = new ArrayList<Promozione>();

    private ArrayList<News> listaNews = new ArrayList<News>();

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


    public Promozione cercaPromozione(String nomePromo) {
        for (Promozione p : this.listaPromo) {
            if (p.getNome().equals(nomePromo)) {
                return p;
            }
        }
        return null;
    }

    public void addNews(News news){
        this.getListaNews().add(news);
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public ArrayList<News> getListaNews() {
        return listaNews;
    }

    public void setListaNews(ArrayList<News> listaNews) {
        this.listaNews = listaNews;
    }

}
