package com.example.claudiocavallaro.progettogad.modello;

import java.util.ArrayList;

/**
 * Created by claudiocavallaro on 27/11/15.
 */
public class Promozione {
    String nome, tipoPromozione;
    private double rapportoQP;
    private int costo;
    private String durata;
    private String info;
    private Gestore gestore;
    private ArrayList<Caratteristiche> listaCaratteristiche = new ArrayList<Caratteristiche>();

    public ArrayList<Caratteristiche> getListaCaratteristiche() {
        return listaCaratteristiche;
    }

    public void setListaCaratteristiche(ArrayList<Caratteristiche> listaCaratteristiche) {
        this.listaCaratteristiche = listaCaratteristiche;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getRapportoQP() {
        return rapportoQP;
    }

    public void setRapportoQP(double rapportoQP) {
        this.rapportoQP = rapportoQP;
    }

    public String getTipoPromozione() {
        return tipoPromozione;
    }

    public void setTipoPromozione(String tipoPromozione) {
        this.tipoPromozione = tipoPromozione;
    }

    public void addCaratteristica(Caratteristiche caratteristica) {
        this.listaCaratteristiche.add(caratteristica);
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public String getDurata() {
        return this.durata;
    }

    public String getOfferta() {
        ArrayList<String> lista = new ArrayList<String>();
        String tot = "";
        for (Caratteristiche c : this.listaCaratteristiche) {
            String singola;
            if (c.getNomeCaratteristica().equals("Internet in 4G") || c.getNomeCaratteristica().equals("Internet in 3G")) {
                singola = c.getQuantita() + " GB " + c.getNomeCaratteristica() + " ";
            } else {
                singola = c.getQuantita() + " " + c.getNomeCaratteristica() + " ";
            }
            lista.add(singola);
        }
        for (int i = 0; i < lista.size(); i++) {
            tot += lista.get(i);
        }
        return tot;
    }

    public String toString() {
        return this.nome + "\n" + "Prezzo : " + this.costo + "\nDurata : " + this.durata
                + "\n" + "CARATTERISTICHE: \n" + this.listaCaratteristiche + "\n" + "INFORMAZIONI: \n" + this.getInfo() + "\n";
    }

    public Gestore getGestore() {
        return gestore;
    }

    public void setGestore(Gestore gestore) {
        this.gestore = gestore;
    }
}
