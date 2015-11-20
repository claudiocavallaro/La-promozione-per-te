package com.example.claudiocavallaro.progettogad.modello;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by claudiocavallaro on 22/10/15.
 */
public class Gestore {

    private String nome;
    private int logo;
    private ArrayList<Offerta> listaOfferte = new ArrayList<Offerta>();

    public Gestore(String nome, int logo){
        this.nome = nome;
        this.logo = logo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Offerta> getListaOfferte() {
        return listaOfferte;
    }

    public void setListaOfferte(ArrayList<Offerta> listaOfferte) {
        this.listaOfferte = listaOfferte;
    }

    public void addOfferta(Offerta offerta){
        this.listaOfferte.add(offerta);
    }

    public Offerta cercaOfferta(String nome){
        if (listaOfferte.isEmpty()){
            return null;
        }
        for (Offerta offerta : listaOfferte){
            if (offerta.getNome().equals(nome)){
                return offerta;
            }
        }
        return null;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }
}
