package com.example.claudiocavallaro.progettogad.persistenza;

import com.example.claudiocavallaro.progettogad.R;
import com.example.claudiocavallaro.progettogad.modello.Compagnie;
import com.example.claudiocavallaro.progettogad.modello.Gestore;
import com.example.claudiocavallaro.progettogad.modello.Offerta;

/**
 * Created by claudiocavallaro on 22/10/15.
 */
public class Mock {

    public Compagnie carica(){
        Compagnie c = new Compagnie();

        Gestore tre = new Gestore("H3G", R.drawable.tre);
        Gestore wind = new Gestore("WIND", R.drawable.wind);
        c.addGestore(tre);
        c.addGestore(wind);

        Offerta uno = new Offerta("offerta1", 10, 200, 200, 1, "3G");
        Offerta due = new Offerta("offerta2", 12, 400, 400, 2, "LTE");
        Offerta otre = new Offerta("offerta3", 14, 600, 600, 4, "LTE");

        tre.addOfferta(uno);
        tre.addOfferta(otre);
        wind.addOfferta(due);

        return c;
    }
}
