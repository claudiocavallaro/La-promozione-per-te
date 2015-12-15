package com.example.claudiocavallaro.progettogad.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.claudiocavallaro.progettogad.modelliViste.ListAdapterGestore;
import com.example.claudiocavallaro.progettogad.R;
import com.example.claudiocavallaro.progettogad.modello.Gestore;
import com.example.claudiocavallaro.progettogad.modello.ListaGestori;
import com.example.claudiocavallaro.progettogad.modelliViste.ModelloCardGestore;
import com.example.claudiocavallaro.progettogad.modello.Promozione;
import com.example.claudiocavallaro.progettogad.persistenza.RestCallNews;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by claudiocavallaro on 07/12/15.
 */
public class GestoreActivity extends AppCompatActivity {

    private ArrayList<ModelloCardGestore> models = new ArrayList<ModelloCardGestore>();
    private List<Promozione> appoggio = new ArrayList<Promozione>();
    private RecyclerView mRecycler;
    private RecyclerView.LayoutManager layoutManager;
    private ListAdapterGestore listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestore);

        for (Gestore g : ListaGestori.getListaGestori()) {
            models.add(new ModelloCardGestore(g.getNomeGestore(), g.getLogo()));
        }
        mRecycler = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new GridLayoutManager(this, 2);
        mRecycler.setLayoutManager(layoutManager);

        listAdapter = new ListAdapterGestore(this, models);
        listAdapter.setClickListener(new ListAdapterGestore.ClickListener() {
            @Override
            public void itemClicked(View view, int position) {
                //Toast.makeText(getApplicationContext(), "ho cliccato " + models.get(position).getNome(), Toast.LENGTH_LONG).show();

                String nome = models.get(position).getNome();
                Intent i = new Intent(getApplicationContext(), ResultActivity.class);
                i.putExtra("nome", nome);
                startActivity(i);
            }
        });
        mRecycler.setAdapter(listAdapter);


    }
}
