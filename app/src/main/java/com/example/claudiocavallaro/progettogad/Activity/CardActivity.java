package com.example.claudiocavallaro.progettogad.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.claudiocavallaro.progettogad.R;
import com.example.claudiocavallaro.progettogad.modello.Caratteristiche;
import com.example.claudiocavallaro.progettogad.modello.Gestore;
import com.example.claudiocavallaro.progettogad.modello.ListaGestori;
import com.example.claudiocavallaro.progettogad.modello.Promozione;

import java.util.List;

/**
 * Created by claudiocavallaro on 04/12/15.
 */
public class CardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        Intent i = getIntent();
        String nome = i.getStringExtra("promo");
        for (Gestore gestore : ListaGestori.getListaGestori()) {
            List<Promozione> lista = gestore.getListaPromo();
            for (Promozione p : lista) {
                if (p.getNome().equals(nome)) {
                    TextView tv = (TextView) findViewById(R.id.textGestore);
                    tv.setText(p.getGestore().getNomeGestore());
                    TextView tvNome = (TextView) findViewById(R.id.textNomeOfferta);
                    tvNome.setText(p.getNome());
                    TextView tvCosto = (TextView) findViewById(R.id.textCosto);
                    String costo = String.valueOf((int) p.getCosto());
                    tvCosto.setText(costo + " € / " + p.getDurata() + " giorni");
                    TextView tvOfferta = (TextView) findViewById(R.id.textOffertaBig);
                    List<Caratteristiche> listaCar = p.getListaCaratteristiche();
                    String car = "";
                    for (Caratteristiche c : listaCar) {
                        car += c.getCar();
                    }
                    tvOfferta.setText(car);
                }
            }
        }

    }


}
