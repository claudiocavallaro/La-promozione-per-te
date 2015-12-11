package com.example.claudiocavallaro.progettogad.vista;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.claudiocavallaro.progettogad.Activity.CardActivity;
import com.example.claudiocavallaro.progettogad.Activity.ResultActivity;
import com.example.claudiocavallaro.progettogad.R;
import com.example.claudiocavallaro.progettogad.modelliViste.ListAdapter;
import com.example.claudiocavallaro.progettogad.modelliViste.ModelloCardItem;
import com.example.claudiocavallaro.progettogad.modello.Gestore;
import com.example.claudiocavallaro.progettogad.modello.ListaGestori;
import com.example.claudiocavallaro.progettogad.modello.Promozione;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by claudiocavallaro on 11/12/15.
 */
public class Tab1 extends android.support.v4.app.Fragment {

    private ResultActivity result;
    private ArrayList<ModelloCardItem> models = new ArrayList<ModelloCardItem>();
    private RecyclerView mRecycler;
    private RecyclerView.LayoutManager layoutManager;
    private ListAdapter listAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab1, container, false);

        String nome = result.getNome();
        for (Gestore g : ListaGestori.getListaGestori()) {
            if (g.getNomeGestore().equals(nome)) {
                List<Promozione> listaPromo = g.getListaPromo();
                for (Promozione p : listaPromo) {
                    String costoP = String.valueOf((int) p.getCosto());
                    models.add(new ModelloCardItem(p.getId(), p.getGestore().getLogo(), p.getNome(), p.getOfferta(), costoP + " €"));
                }
            }
        }

        mRecycler = (RecyclerView) v.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this.getActivity());
        mRecycler.setLayoutManager(layoutManager);

        listAdapter = new ListAdapter(result.getApplicationContext(), models);
        listAdapter.setClickListener(new ListAdapter.ClickListener() {
            @Override
            public void itemClicked(View view, int position) {
                Intent i = new Intent(result.getApplicationContext(), CardActivity.class);
                i.putExtra("promo", models.get(position).getId());
                startActivity(i);
            }
        });
        mRecycler.setAdapter(listAdapter);

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        result = (ResultActivity) activity;
    }

}
