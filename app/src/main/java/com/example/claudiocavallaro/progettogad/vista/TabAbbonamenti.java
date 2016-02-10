package com.example.claudiocavallaro.progettogad.vista;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.claudiocavallaro.progettogad.Activity.ResultActivity;
import com.example.claudiocavallaro.progettogad.Activity.ResultCellActivity;
import com.example.claudiocavallaro.progettogad.R;
import com.example.claudiocavallaro.progettogad.modelliViste.ListAdapterGestore;
import com.example.claudiocavallaro.progettogad.modelliViste.ModelloCardGestore;
import com.example.claudiocavallaro.progettogad.modello.Gestore;
import com.example.claudiocavallaro.progettogad.modello.ListaGestori;
import com.example.claudiocavallaro.progettogad.modello.Promozione;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by claudiocavallaro on 09/02/16.
 */
public class TabAbbonamenti extends android.support.v4.app.Fragment {

    private ResultCellActivity resultCellActivity;
    private ArrayList<ModelloCardGestore> models = new ArrayList<ModelloCardGestore>();
    private List<Promozione> appoggio = new ArrayList<Promozione>();
    private RecyclerView mRecycler;
    private RecyclerView.LayoutManager layoutManager;
    private ListAdapterGestore listAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tababbonamenti, container, false);

        for (Gestore g : ListaGestori.getListaGestori()) {
            models.add(new ModelloCardGestore(g.getNomeGestore(), g.getLogo()));
        }
        mRecycler = (RecyclerView) v.findViewById(R.id.recycler_view);
        layoutManager = new GridLayoutManager(this.getContext(), 2);
        mRecycler.setLayoutManager(layoutManager);

        listAdapter = new ListAdapterGestore(this.getContext(), models);
        listAdapter.setClickListener(new ListAdapterGestore.ClickListener() {
            @Override
            public void itemClicked(View view, int position) {
                String url = "";
                String nome = models.get(position).getNome();

                if (nome.equals("TIM")){
                    if (resultCellActivity.getNome().contains("Apple")){
                        url = "https://www.tim.it/offerte/mobile/smartphone-e-tablet-rateizzati/iphone";
                    }
                    if(resultCellActivity.getNome().contains(("Samsung"))){
                        url = "https://www.tim.it/offerte/mobile/smartphone-e-tablet-rateizzati/scegli-il-tuo-smartphone";
                    }
                }

                if (nome.equals("VODAFONE")){
                    if (resultCellActivity.getNome().contains("Apple")){
                        url = "http://www.vodafone.it/portal/Privati/Tariffe-e-Prodotti/Prodotti/Smartphone/Scheda-iPhone-6s-Plus";
                    }
                    if (resultCellActivity.getNome().contains("Samsung")){
                        url = "http://www.vodafone.it/portal/Privati/Tariffe-e-Prodotti/Prodotti/Smartphone/Scheda-Samsung-Galaxy-S6-edge-";
                    }
                }

                if (nome.equals("WIND")){
                    url = "http://www.wind.it/it/privati/telefoni_e_contenuti/telefono_incluso/ricaricabile/";
                }

                if (nome.equals("TRE")){
                    if (resultCellActivity.getNome().contains("Apple")){
                        url = "http://www.tre.it/prodotti/apple";
                    }
                    if (resultCellActivity.getNome().contains("Samsung")){
                        url = "http://www.tre.it/prodotti/samsung-galaxy-S6-edge-plus";
                    }
                }
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);


            }
        });
        mRecycler.setAdapter(listAdapter);

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        resultCellActivity = (ResultCellActivity) activity;
    }
}
