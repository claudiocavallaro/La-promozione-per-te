package com.example.claudiocavallaro.progettogad.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.claudiocavallaro.progettogad.R;

import com.example.claudiocavallaro.progettogad.modelliViste.Helper;
import com.example.claudiocavallaro.progettogad.modelliViste.ListAdapterCard;
import com.example.claudiocavallaro.progettogad.modelliViste.ModelloCardInfo;
import com.example.claudiocavallaro.progettogad.modello.Caratteristiche;

import com.example.claudiocavallaro.progettogad.modello.ListaGestori;
import com.example.claudiocavallaro.progettogad.modello.Promozione;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by claudiocavallaro on 04/12/15.
 */
public class CardActivity extends AppCompatActivity {

    private String link;
    private Promozione promo;
    private ArrayList<Promozione> lista = ListaGestori.getListaPromozioni();

    private ArrayList<String> listaFav = new ArrayList<String>();
    private Helper helper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        //leggiFav();
        //System.out.println(listaFav);

        Intent i = getIntent();
        int id = i.getIntExtra("promo", 0);
        System.out.println(id);

        System.out.println(ListaGestori.getListaPromozioni().size());

        for (Promozione p : lista) {
            if (p.getId() == id) {
                promo = p;
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
                    if (c.getNomeCaratteristica().contains("Minuti")) {
                        if (c.getQuantita().equals("illimitati") || c.getQuantita().equals("Illimitati")) {
                            models.add(new ModelloCardInfo(c.getNomeCaratteristica() + " " + c.getQuantita(), R.drawable.chiamate));
                        } else {
                            models.add(new ModelloCardInfo(c.getQuantita() + " " + c.getNomeCaratteristica(), R.drawable.chiamate));
                        }
                    }
                    if (c.getNomeCaratteristica().contains("SMS")) {
                        if (c.getQuantita().equals("illimitati") || c.getQuantita().equals("Illimitati")) {
                            models.add(new ModelloCardInfo(c.getNomeCaratteristica() + " " + c.getQuantita(), R.drawable.mess));
                        } else {
                            models.add(new ModelloCardInfo(c.getQuantita() + " " + c.getNomeCaratteristica(), R.drawable.mess));
                        }
                    }
                    if (c.getNomeCaratteristica().contains("Internet")) {
                        models.add(new ModelloCardInfo(c.getQuantita() + "GB " + c.getNomeCaratteristica(), R.drawable.www));
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

                if (info.equals(" ")) {
                    tvInformazioni.setText("\nPer maggiori informazioni visita il sito web cliccando sul bottone sottostante");
                } else {
                    tvInformazioni.setText(info + "\n\nPer maggiori informazioni visita il sito web cliccando sul bottone sottostante");
                }

                /*for (String s : listaFav) {
                    System.out.println("Stringa s " + s + " confronto con " + promo.getId() +  " e " + p.getId() );
                    if (promo.getId() == Integer.parseInt(s)) {
                        promo.setFav(true);
                        System.out.println("true");
                        break;
                    } else {
                        promo.setFav(false);
                    }
                }*/

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

    private void leggiFav() {
        helper = new Helper(this);
        database = helper.getReadableDatabase();
        String[] column = {"name"};
        Cursor cursor = database.query("fav", column, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String fav = cursor.getString(0);
            Log.v("id salvato", fav);
            listaFav.add(fav);
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
            link = promo.getInfo();
            if (link.contains(" - ")) {
                String[] result = link.split(" - ");
                link = result[1];
            }
            String message = "Ciao volevo consigliarti questa promozione " + promo.getNome() + " della " + promo.getGestore().getNomeGestore() + "\nPer maggiori informazioni clicca" +
                    " qui\n" + link;
            Intent intent = sendIntent.putExtra(Intent.EXTRA_TEXT, message);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
        if (id == R.id.fav) {
            System.out.println(promo.toString());
            if (promo.isFav() == true) {
                Toast.makeText(this, "Promozione già inclusa nella lista dei preferiti", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Promozione inserita con successo", Toast.LENGTH_LONG).show();
                inserisci(promo.getId());
                promo.setFav(true);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void inserisci(int id) {
        helper = new Helper(this);
        database = helper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", String.valueOf(id));
        database.insert("fav", "name", contentValues);

        String[] column = {"name"};
        Cursor cursor = database.query("fav", column, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String fav = cursor.getString(0);
            Log.v("id salvato", fav);
            listaFav.add(fav);
        }

    }


}
