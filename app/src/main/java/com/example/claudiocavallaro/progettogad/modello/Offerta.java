package com.example.claudiocavallaro.progettogad.modello;

/**
 * Created by claudiocavallaro on 22/10/15.
 */
public class Offerta {

    private String nome;
    private float prezzo;
    private int minuti;
    private int sms;
    private int internet;
    private String lte;

    public Offerta(String nome, float prezzo, int minuti, int sms, int internet, String lte){
        this.setNome(nome);
        this.setPrezzo(prezzo);
        this.setMinuti(minuti);
        this.setSms(sms);
        this.setInternet(internet);
        this.setLte(lte);
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPrezzo() {
        return prezzo + "€";
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public int getMinuti() {
        return minuti;
    }

    public void setMinuti(int minuti) {
        this.minuti = minuti;
    }

    public int getSms() {
        return sms;
    }

    public void setSms(int sms) {
        this.sms = sms;
    }

    public int getInternet() {
        return internet;
    }

    public void setInternet(int internet) {
        this.internet = internet;
    }

    public String getLte() {
        return lte;
    }

    public void setLte(String lte) {
        this.lte = lte;
    }

    public String getOfferta(){
        return minuti + " minuti " + sms + " sms " + internet + "GB in " + lte;
    }
}
