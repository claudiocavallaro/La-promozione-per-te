package com.example.claudiocavallaro.progettogad.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.claudiocavallaro.progettogad.R;
import com.example.claudiocavallaro.progettogad.modelliViste.ListAdapter;
import com.example.claudiocavallaro.progettogad.modelliViste.ListAdapterCard;
import com.example.claudiocavallaro.progettogad.modelliViste.ModelloCardInfo;
import com.example.claudiocavallaro.progettogad.modello.Caratteristiche;
import com.example.claudiocavallaro.progettogad.modello.Gestore;
import com.example.claudiocavallaro.progettogad.modello.ListaGestori;
import com.example.claudiocavallaro.progettogad.modello.Promozione;
import com.facebook.share.model.ShareLinkContent;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by claudiocavallaro on 04/12/15.
 */
public class CardActivity extends AppCompatActivity {

    private String link;
    private Promozione p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        Intent i = getIntent();
        int id = i.getIntExtra("promo", 0);
        for (Gestore gestore : ListaGestori.getListaGestori()) {
            List<Promozione> lista = ListaGestori.getListaPromozioni();
            for (int j = 0; j < lista.size() ; j++) {
                p = lista.get(j);
                if (p.getId() == id) {
                    TextView tv = (TextView) findViewById(R.id.textGestore);
                    tv.setText(p.getGestore().getNomeGestore());
                    TextView tvNome = (TextView) findViewById(R.id.textNomeOfferta);
                    tvNome.setText(p.getNome());
                    TextView tvCosto = (TextView) findViewById(R.id.textCosto);
                    String costo = String.valueOf((int) p.getCosto());
                    tvCosto.setText(costo + " € / " + p.getDurata() + " giorni");

                    List<Caratteristiche> listaCar = p.getListaCaratteristiche();

                    ArrayList<ModelloCardInfo> models = new ArrayList<ModelloCardInfo>();


                    for (Caratteristiche c : listaCar) {
                        if (c.getNomeCaratteristica().contains("Minuti")){
                            if (c.getQuantita().equals("illimitati") || c.getQuantita().equals("Illimitati")){
                                models.add(new ModelloCardInfo(c.getNomeCaratteristica() + " " + c.getQuantita() , R.drawable.minu));
                            } else {
                                models.add(new ModelloCardInfo(c.getQuantita() + " " + c.getNomeCaratteristica(), R.drawable.minu));
                            }
                        }
                        if (c.getNomeCaratteristica().contains("SMS")){
                            if (c.getQuantita().equals("illimitati") || c.getQuantita().equals("Illimitati")){
                                models.add(new ModelloCardInfo(c.getNomeCaratteristica() + " " + c.getQuantita() , R.drawable.sms));
                            } else {
                                models.add(new ModelloCardInfo(c.getQuantita() + " " + c.getNomeCaratteristica(), R.drawable.sms));
                            }
                        }
                        if (c.getNomeCaratteristica().contains("Internet")){
                            models.add(new ModelloCardInfo(c.getQuantita() + "GB " + c.getNomeCaratteristica(), R.drawable.internet));
                        }

                    }

                    ListAdapterCard listAdapterCard = new ListAdapterCard(this, models);
                    ListView listView = (ListView) findViewById(R.id.textOffertaBig);
                    listView.setAdapter(listAdapterCard);


                    TextView tvInformazioni = (TextView) findViewById(R.id.textInformazioni);
                    String info = "";
                    link = p.getInfo();
                    if (link.contains(" - ")) {
                        String[] result = link.split(" - ");
                        link = result[1];
                        info = result[0];
                    } else {
                        info = "";
                    }

                    System.out.println(info);

                    if (info.equals(" ")){
                        tvInformazioni.setText("\nPer maggiori informazioni visita il sito web cliccando sul bottone sottostante");
                    } else {
                        tvInformazioni.setText(info + "\n\nPer maggiori informazioni visita il sito web cliccando sul bottone sottostante");
                    }


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

                    /*ImageView add = (ImageView) findViewById(R.id.addButton);
                    add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            p.setFav(true);
                            Toast.makeText(getApplicationContext(), "Promozione aggiunta alle preferite", Toast.LENGTH_LONG).show();
                        }
                    });

                    ImageView remove = (ImageView) findViewById(R.id.removeButton);
                    add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            p.setFav(false);
                            Toast.makeText(getApplicationContext(), "Promozione rimossa dalle preferite", Toast.LENGTH_LONG).show();
                        }
                    });*/

                }
            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_card, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            link = p.getInfo();
            if (link.contains(" - ")) {
                String[] result = link.split(" - ");
                link = result[1];
            }
            String message = "Ciao volevo consigliarti questa promozione " + p.getNome() + " della " + p.getGestore().getNomeGestore() + "\nPer maggiori informazioni clicca" +
                    " qui\n" + link;
            Intent intent = sendIntent.putExtra(Intent.EXTRA_TEXT, message);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
        return super.onOptionsItemSelected(item);
    }


}
