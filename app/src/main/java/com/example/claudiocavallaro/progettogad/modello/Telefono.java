package com.example.claudiocavallaro.progettogad.modello;

import java.util.ArrayList;

/**
 * Created by claudiocavallaro on 09/02/16.
 */
public class Telefono {

    private String marchio;
    private int logo;
    private String modello;
    private int capienza;
    private double prezzo1;
    private double prezzo2;

    private Specifiche specifiche;

    public Telefono(String marchio, String modello, int capienza, double prezzo1, double prezzo2){
        this.setMarchio(marchio);
        this.setModello(modello);
        this.setCapienza(capienza);
        this.setPrezzo1(prezzo1);
        this.setPrezzo2(prezzo2);
    }

    public Telefono(String marchio){
        this.marchio = marchio;
    }


    public String getMarchio() {
        return marchio;
    }

    public void setMarchio(String marchio) {
        this.marchio = marchio;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    public double getPrezzo1() {
        return prezzo1;
    }

    public void setPrezzo1(double prezzo1) {
        this.prezzo1 = prezzo1;
    }

    public double getPrezzo2() {
        return prezzo2;
    }

    public void setPrezzo2(double prezzo2) {
        this.prezzo2 = prezzo2;
    }


    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String toString(){
        return this.marchio + " " + this.modello + " " + this.capienza + " GB " + this.prezzo1 + " € " + this.prezzo2 + " € ";
    }

    public String getTelef(){
        return this.getModello() + " " + this.getCapienza() + " GB";
    }

    public Specifiche getSpecifiche() {
        return specifiche;
    }

    public void setSpecifiche(Specifiche specifiche) {
        this.specifiche = specifiche;
    }
}
