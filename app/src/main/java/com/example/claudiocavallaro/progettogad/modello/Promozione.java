package com.example.claudiocavallaro.progettogad.modello;

import java.util.ArrayList;

/**
 * Created by claudiocavallaro on 27/11/15.
 */
public class Promozione {
    String nome, tipoPromozione;
    private double rapportoQP;
    private double costo;
    private String durata;
    private String info;
    private ArrayList<Caratteristiche> listaCaratteristiche = new ArrayList<Caratteristiche>();

	/*public Promozione(String nome, String rapportoQP, String tipoPromozione, double costo) {
        setNome(nome);
		setRapportoQP(rapportoQP);
		setTipoPromozione(tipoPromozione);
		setCosto(costo);
	}*/

    public ArrayList<Caratteristiche> getListaCaratteristiche() {
        return listaCaratteristiche;
    }

    public void setListaCaratteristiche(ArrayList<Caratteristiche> listaCaratteristiche) {
        this.listaCaratteristiche = listaCaratteristiche;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
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

    public String toString() {
        return this.nome + "\n" + "Prezzo : " + this.costo + "\nDurata : " + this.durata
                + "\n" + "CARATTERISTICHE: \n" + this.listaCaratteristiche + "\n" + "INFORMAZIONI: \n" + this.getInfo() + "\n";
    }
}
