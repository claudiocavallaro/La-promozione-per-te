package com.example.claudiocavallaro.progettogad.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.claudiocavallaro.progettogad.R;
import com.example.claudiocavallaro.progettogad.modello.Gestore;
import com.example.claudiocavallaro.progettogad.modello.ListaGestori;
import com.example.claudiocavallaro.progettogad.modello.Promozione;

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
        Promozione p = null;
        for (Gestore gestore : ListaGestori.getListaGestori()) {
            p = gestore.cercaPromozione(nome);
        }
        TextView tv = (TextView) findViewById(R.id.textGestore);
        tv.setText(p.getGestore().getNomeGestore());
        TextView tvNome = (TextView) findViewById(R.id.textNomeOfferta);
        tvNome.setText(p.getNome());
    }


}
