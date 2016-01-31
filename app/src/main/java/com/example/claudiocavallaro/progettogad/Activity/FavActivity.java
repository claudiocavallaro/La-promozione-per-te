package com.example.claudiocavallaro.progettogad.Activity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.claudiocavallaro.progettogad.R;
import com.example.claudiocavallaro.progettogad.modelliViste.Helper;
import com.example.claudiocavallaro.progettogad.modelliViste.ListAdapter;
import com.example.claudiocavallaro.progettogad.modelliViste.ModelloCardItem;

import com.example.claudiocavallaro.progettogad.modello.ListaGestori;
import com.example.claudiocavallaro.progettogad.modello.Promozione;

import java.util.ArrayList;


/**
 * Created by claudiocavallaro on 28/01/16.
 */
public class FavActivity extends AppCompatActivity {

    private ArrayList<ModelloCardItem> models;
    private RecyclerView mRecycler;
    private RecyclerView.LayoutManager layoutManager;
    private ListAdapter listAdapter;
    private ArrayList<Promozione> listaPromo;

    private Helper helper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        stampaLista();

    }

    private void stampaLista() {
        listaPromo = new ArrayList<Promozione>();
        for (int i = 0 ; i < ListaGestori.getListaPromozioni().size(); i++){
            Promozione p = ListaGestori.getListaPromozioni().get(i);
            if (p.isFav() == true){
                p.setId(i);
                listaPromo.add(p);
            }
        }

        if (listaPromo.size() == 0){
            Toast.makeText(this, "Nessuna promozione inserita nei preferiti", Toast.LENGTH_SHORT).show();
        }

        models = new ArrayList<ModelloCardItem>();
        for (Promozione p : ListaGestori.getListaPromozioni()) {
            if (p.isFav() == true) {
                String costo = String.valueOf((int) p.getCosto());
                models.add(new ModelloCardItem(p.getId(), p.getGestore().getLogo(), p.getNome(), p.getOfferta(), costo + " €"));
            }

        }

        mRecycler = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(layoutManager);
        listAdapter = new ListAdapter(this, models);
        listAdapter.setPartenza(1);
        listAdapter.setClickListener(new ListAdapter.ClickListener() {
            @Override
            public void itemClicked(View view, int position) {
                //Toast.makeText(getApplicationContext(), "ho cliccato " + models.get(position).getNome(), Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), CardActivity.class);
                i.putExtra("promo", models.get(position).getId());
                startActivity(i);
            }
        });

        mRecycler.setAdapter(listAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.trash) {
            if (listAdapter.getSelectedItems().size() == 0) {
                Toast.makeText(this, "Nessuna promozione selezionata da eliminare", Toast.LENGTH_LONG).show();
            } else {


                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Eliminazione");
                builder.setMessage("Vuoi eliminare " + listAdapter.getSelectedItems().size() + " elementi ?");
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        helper = new Helper(getApplicationContext());
                        database = helper.getReadableDatabase();

                        for (int i = 0; i < listaPromo.size(); i++) {
                            Promozione p = listaPromo.get(i);
                            if (listAdapter.getSelectedItems().get(i) == true) {
                                database.delete("fav", "name =" + p.getId(), null);
                                p.setFav(false);
                            }
                        }
                        stampaLista();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
