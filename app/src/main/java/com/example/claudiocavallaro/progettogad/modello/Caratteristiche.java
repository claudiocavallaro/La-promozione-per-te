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

    public String getCar() {
        if (this.getNomeCaratteristica().equals("Internet in 4G") || this.getNomeCaratteristica().equals("Internet in 3G")) {
            return " - " + this.getQuantita() + " GB " + this.getNomeCaratteristica() + "\n\n";
        } else if (this.getNomeCaratteristica().equals("Minuti illimitati verso un numero Vodafone")) {
            return " - " + this.getNomeCaratteristica() + "\n\n";
        } else if (this.getQuantita().equals("Illimitati") && !(this.getNomeCaratteristica().equals("Minuti illimitati verso un numero Vodafone"))) {
            return " - " + this.getNomeCaratteristica() + " " + this.getQuantita() + "\n\n";
        } else {
            return " - " + this.getQuantita() + " " + this.getNomeCaratteristica() + "\n\n";
        }
    }
}
