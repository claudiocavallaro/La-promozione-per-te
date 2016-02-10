package com.example.claudiocavallaro.progettogad.vista;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.claudiocavallaro.progettogad.Activity.ResultCellActivity;
import com.example.claudiocavallaro.progettogad.R;
import com.example.claudiocavallaro.progettogad.modelliViste.ListAdapterSpecifiche;
import com.example.claudiocavallaro.progettogad.modelliViste.ModelloSpecifiche;
import com.example.claudiocavallaro.progettogad.modello.ListaGestori;
import com.example.claudiocavallaro.progettogad.modello.Telefono;

import java.util.ArrayList;

/**
 * Created by claudiocavallaro on 09/02/16.
 */
public class TabSpecifiche extends android.support.v4.app.Fragment {

    private ResultCellActivity resultCellActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tabspecifiche, container, false);

        Telefono t = ListaGestori.cercaTelefono(resultCellActivity.getNome());
        ArrayList<ModelloSpecifiche> modelloSpecifiches = new ArrayList<ModelloSpecifiche>();
        modelloSpecifiches.add(new ModelloSpecifiche("Generali"));
        if (t.getMarchio().equals("Apple")){
            modelloSpecifiches.add(new ModelloSpecifiche(R.mipmap.appleicon, "SO " + t.getSpecifiche().getSo()));
        } else {
            modelloSpecifiches.add(new ModelloSpecifiche(R.mipmap.android, "SO " + t.getSpecifiche().getSo()));
        }

        modelloSpecifiches.add(new ModelloSpecifiche(R.mipmap.dimensioni, "Dimensioni " + t.getSpecifiche().getDimensioni()));
        modelloSpecifiches.add(new ModelloSpecifiche(R.mipmap.bilancia, "Peso " + t.getSpecifiche().getPeso()));
        modelloSpecifiches.add(new ModelloSpecifiche(R.mipmap.battery, "Batteria " + t.getSpecifiche().getBatteria()));

        modelloSpecifiches.add(new ModelloSpecifiche("Dati tecnici"));
        modelloSpecifiches.add(new ModelloSpecifiche(R.mipmap.cpu, "Processore " + t.getSpecifiche().getProcessore()));
        modelloSpecifiches.add(new ModelloSpecifiche(R.mipmap.ram, "Ram " + t.getSpecifiche().getRam()));

        modelloSpecifiches.add(new ModelloSpecifiche("Video"));
        modelloSpecifiches.add(new ModelloSpecifiche(R.mipmap.display, "Schermo " + t.getSpecifiche().getSchermo()));
        modelloSpecifiches.add(new ModelloSpecifiche(R.mipmap.display, "Risoluzione " + t.getSpecifiche().getRisoluzione()));
        modelloSpecifiches.add(new ModelloSpecifiche(R.mipmap.camera, "Fotocamera " + t.getSpecifiche().getFotocamera()));
        modelloSpecifiches.add(new ModelloSpecifiche(R.mipmap.camera, "Frontale " + t.getSpecifiche().getFrontale()));

        ListAdapterSpecifiche listAdapter = new ListAdapterSpecifiche(this.getContext(), modelloSpecifiches);
        ListView listView = (ListView) v.findViewById(R.id.listViewCosa);
        listView.setAdapter(listAdapter);

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        resultCellActivity = (ResultCellActivity) activity;
    }
}
