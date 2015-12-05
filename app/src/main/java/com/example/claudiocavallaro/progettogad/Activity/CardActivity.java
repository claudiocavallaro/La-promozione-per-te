package com.example.claudiocavallaro.progettogad.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

    private String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        Intent i = getIntent();
        int id = i.getIntExtra("promo", 0);
        for (Gestore gestore : ListaGestori.getListaGestori()) {
            List<Promozione> lista = ListaGestori.getListaPromozioni();
            for (Promozione p : lista) {
                if (p.getId() == id) {
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
                    //System.out.println(car);
                    tvOfferta.setText(car);

                    TextView tvInformazioni = (TextView) findViewById(R.id.textInformazioni);
                    String info = "";
                    link = p.getInfo();
                    if (link.contains(" - ")) {
                        String[] result = link.split(" - ");
                        link = result[1];
                        info = result[0];
                    } else {
                        info = link;
                    }

                    System.out.println(info);

                    tvInformazioni.setText(info + "\n\nPer maggiori informazioni visita il sito web cliccando sul bottone sottostante");

                    System.out.println(link);
                    Button bottone = (Button) findViewById(R.id.buttonLink);
                    bottone.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(link));
                            startActivity(i);
                        }
                    });
                }
            }
        }

    }


}
