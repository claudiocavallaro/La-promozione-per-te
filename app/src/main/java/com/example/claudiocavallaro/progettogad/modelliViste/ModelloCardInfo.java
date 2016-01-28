package com.example.claudiocavallaro.progettogad.modelliViste;

/**
 * Created by claudiocavallaro on 28/01/16.
 */
public class ModelloCardInfo {

    private String caratteristica;
    private int logo;

    public ModelloCardInfo(String caratteristica, int logo){
        this.setCaratteristica(caratteristica);
        this.setLogo(logo);
    }


    public String getCaratteristica() {
        return caratteristica;
    }

    public void setCaratteristica(String caratteristica) {
        this.caratteristica = caratteristica;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }
}
