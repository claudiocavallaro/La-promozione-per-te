package com.example.claudiocavallaro.progettogad.modelliViste;

/**
 * Created by claudiocavallaro on 15/12/15.
 */
public class ModelloNews {

    private String titolo;
    private String link;

    public ModelloNews(String titolo, String link){
        this.setTitolo(titolo);
        this.setLink(link);
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
