package com.example.claudiocavallaro.progettogad.modello;

/**
 * Created by claudiocavallaro on 15/12/15.
 */
public class News {

    private String titolo;
    private String link;

    public News(String titolo){
        this.setTitolo(titolo);
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
