package com.example.claudiocavallaro.progettogad.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.claudiocavallaro.progettogad.modelliViste.Helper;
import com.example.claudiocavallaro.progettogad.modelliViste.ListAdapter;
import com.example.claudiocavallaro.progettogad.R;
import com.example.claudiocavallaro.progettogad.modello.Gestore;
import com.example.claudiocavallaro.progettogad.modello.ListaGestori;
import com.example.claudiocavallaro.progettogad.modelliViste.ModelloCardItem;
import com.example.claudiocavallaro.progettogad.modello.Promozione;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ArrayList<ModelloCardItem> models = new ArrayList<ModelloCardItem>();
    private RecyclerView mRecycler;
    private RecyclerView.LayoutManager layoutManager;
    private ListAdapter listAdapter;

    private Helper helper;
    private SQLiteDatabase database;
    private ArrayList<String> listaFav = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leggiFav();
        System.out.println(listaFav.size());

        setToolBar();
        setInterface();
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

    public void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void setInterface() {
        //ORDINAMENTO
        List<Promozione> appoggio = ListaGestori.getListaPromozioni();
        if (ListaGestori.getListaPromozioni().size() == 0) {
            ArrayList<Gestore> listaGestori = ListaGestori.getListaGestori();
            for (Gestore gestore : listaGestori) {
                List<Promozione> listaPromo = gestore.getListaPromo();
                for (Promozione p : listaPromo) {
                    appoggio.add(p);
                }
            }
        }


        Collections.sort(appoggio, new Comparator<Promozione>() {
            @Override
            public int compare(Promozione lhs, Promozione rhs) {
                if (lhs.getRapportoQP() == rhs.getRapportoQP()) {
                    return 0;
                } else if (lhs.getRapportoQP() > rhs.getRapportoQP()) {
                    return -1;
                }
                return 1;
            }
        });


        for (int i = 0; i < appoggio.size(); i++) {
            Promozione p = appoggio.get(i);
            p.setId(i);
            for (String s : listaFav){
                if (p.getId() == Integer.parseInt(s)){
                    p.setFav(true);
                    break;
                } else {
                    p.setFav(false);
                }
            }
            String costo = String.valueOf((int) p.getCosto());
            models.add(new ModelloCardItem(p.getId(), p.getGestore().getLogo(), p.getNome(), p.getOfferta(), costo + " €"));
        }

        /*for (int i = 0; i < listaFav.size(); i++) {
            String s = listaFav.get(i);
            for (int j = 0; j < appoggio.size(); j++) {
                System.out.println("Confronto string s " + s + " con " + appoggio.get(j).getId());
                if (appoggio.get(j).getId() == Integer.parseInt(s)) {
                    System.out.println("true");
                    appoggio.get(j).setFav(true);
                } else {
                    appoggio.get(j).setFav(false);
                }
            }
        }*/

        int k = 0;
        for (Promozione p : appoggio) {
            if (p.isFav() == true) {
                k++;
            }
        }
        System.out.println(k);

        //INTERFACCIA
        mRecycler = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(layoutManager);

        listAdapter = new ListAdapter(this, models);
        listAdapter.setPartenza(0);
        listAdapter.setClickListener(new ListAdapter.ClickListener() {
            @Override
            public void itemClicked(View view, int position) {
                Intent i = new Intent(MainActivity.this, CardActivity.class);
                i.putExtra("promo", models.get(position).getId());
                startActivity(i);
            }
        });


        mRecycler.setAdapter(listAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.info) {
            Intent i = new Intent(MainActivity.this, InfoActivity.class);
            startActivity(i);
        }
        if (id == R.id.action_cerca) {
            Intent i = new Intent(MainActivity.this, CercaActivity.class);
            startActivity(i);
        }
        if (id == R.id.action_cercaGestore) {
            Intent i = new Intent(MainActivity.this, GestoreActivity.class);
            startActivity(i);
        }
        if (id == R.id.action_fav) {
            Intent i = new Intent(MainActivity.this, FavActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
