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
import com.example.claudiocavallaro.progettogad.modello.ListaGestori;
import com.example.claudiocavallaro.progettogad.modello.Promozione;
import com.example.claudiocavallaro.progettogad.modello.Telefono;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by claudiocavallaro on 09/02/16.
 */
public class TabPrezzi extends android.support.v4.app.Fragment {

    private ArrayList<ModelloCardGestore> models = new ArrayList<ModelloCardGestore>();
    private RecyclerView mRecycler;
    private RecyclerView.LayoutManager layoutManager;
    private ListAdapterGestore listAdapter;

    private ResultCellActivity resultCellActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tabprezzi, container, false);

        Telefono t = ListaGestori.cercaTelefono(resultCellActivity.getNome());

        models.add(new ModelloCardGestore(String.valueOf(t.getPrezzo1()) + " €", R.drawable.euronics));
        models.add(new ModelloCardGestore(String.valueOf(t.getPrezzo2()) + " €", R.drawable.stock1));

        mRecycler = (RecyclerView) v.findViewById(R.id.recycler_view);
        layoutManager = new GridLayoutManager(this.getContext(), 2);
        mRecycler.setLayoutManager(layoutManager);

        listAdapter = new ListAdapterGestore(this.getContext(), models);
        listAdapter.setClickListener(new ListAdapterGestore.ClickListener() {
            @Override
            public void itemClicked(View view, int position) {
                String url = "";
                if (models.get(position).getLogo() == R.drawable.stock1){
                    if (resultCellActivity.getNome().contains("Apple")){
                        url = "http://www.stockisti.com/it/telefonia/telefonia-mobile/smartphone/shopby/disponibile-si/garanzia-italia/marca-apple.html";
                    }
                    if (resultCellActivity.getNome().contains("Samsung")){
                        url = "http://www.stockisti.com/it/telefonia/telefonia-mobile/samsung-galaxy-s6-edge-plus-g928-32gb-black-sapphire.html";
                    }
                }

                if (models.get(position).getLogo() == R.drawable.euronics){
                    if (resultCellActivity.getNome().contains("Apple")){
                        url = "http://www.euronics.it/acquistaonline/telefonia-autoradio-e-gps/cellulari-e-smartphone/smartphone/?cid=cat110059&orderBy=PrezzoOrdSup&q=iphone+6s+plus&viewAll=true";
                    }
                    if (resultCellActivity.getNome().contains("Samsung")){
                        url = "http://www.euronics.it/acquistaonline/telefonia-autoradio-e-gps/cellulari-e-smartphone/smartphone/?cid=cat110059&orderBy=PrezzoOrdSup&q=s6+edge&viewAll=true";
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
