package com.example.claudiocavallaro.progettogad.modello;

/**
 * Created by claudiocavallaro on 27/11/15.
 */
public class Caratteristiche {
    String nomeCaratteristica;
    String quantita;

    public Caratteristiche(String nomeCaratteristica, String quantita) {
        setNomeCaratteristica(nomeCaratteristica);
        setQuantita(quantita);
    }

    public String getNomeCaratteristica() {
        return nomeCaratteristica;
    }

    public void setNomeCaratteristica(String nomeCaratteristica) {
        this.nomeCaratteristica = nomeCaratteristica;
    }

    public String getQuantita() {
        return quantita;
    }

    public void setQuantita(String quantita) {
        this.quantita = quantita;
    }

    public String toString() {
        return nomeCaratteristica + " " + quantita;
    }
}
