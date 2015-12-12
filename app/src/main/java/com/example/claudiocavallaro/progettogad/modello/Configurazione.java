package com.example.claudiocavallaro.progettogad.modello;

/**
 * Created by claudiocavallaro on 12/12/15.
 */
public class Configurazione {

    private static String costo;

    public Configurazione(String costo) {
        this.setCosto(costo);
    }


    public static String getCosto() {
        return costo;
    }

    public static void setCosto(String costo) {
        Configurazione.costo = costo;
    }
}
