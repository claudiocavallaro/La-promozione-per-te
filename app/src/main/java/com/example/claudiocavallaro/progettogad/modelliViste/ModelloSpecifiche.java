package com.example.claudiocavallaro.progettogad.modelliViste;

/**
 * Created by claudiocavallaro on 10/02/16.
 */
public class ModelloSpecifiche {

    private String title;
    private int icon;

    private String sottoTitolo;

    private boolean gruppo = false;

    public ModelloSpecifiche(String title){
        this.setTitle(title);
        this.setIcon(-1);
        this.setGruppo(true);
    }

    public ModelloSpecifiche(int icon, String title){
        super();
        this.setIcon(icon);
        this.setTitle(title);
    }

    public ModelloSpecifiche(int icon, String title, String sottoTitolo){
        super();
        this.setIcon(icon);
        this.setTitle(title);
        this.setSottoTitolo(sottoTitolo);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getSottoTitolo() {
        return sottoTitolo;
    }

    public void setSottoTitolo(String sottoTitolo) {
        this.sottoTitolo = sottoTitolo;
    }

    public boolean isGruppo() {
        return gruppo;
    }

    public void setGruppo(boolean gruppo) {
        this.gruppo = gruppo;
    }
}
