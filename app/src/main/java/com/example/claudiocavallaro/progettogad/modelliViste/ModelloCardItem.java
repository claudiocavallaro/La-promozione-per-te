package com.example.claudiocavallaro.progettogad.modelliViste;

/**
 * Created by claudiocavallaro on 22/10/15.
 */
public class ModelloCardItem {

    private String nome;
    private int logo;
    private int id;
    private String offerta;
    private String prezzo;

    public ModelloCardItem(int id, int logo, String nome, String offerta, String prezzo) {
        this.id = id;
        this.logo = logo;
        this.nome = nome;
        this.offerta = offerta;
        this.prezzo = prezzo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getOfferta() {
        return offerta;
    }

    public void setOfferta(String offerta) {
        this.offerta = offerta;
    }

    public String getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(String prezzo) {
        this.prezzo = prezzo;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
