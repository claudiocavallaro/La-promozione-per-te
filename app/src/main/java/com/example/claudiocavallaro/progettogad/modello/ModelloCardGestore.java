package com.example.claudiocavallaro.progettogad.modello;

/**
 * Created by claudiocavallaro on 09/12/15.
 */
public class ModelloCardGestore {

    private String nome;
    private int logo;

    public ModelloCardGestore(String nome, int logo) {
        this.setNome(nome);
        this.setLogo(logo);
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
}
