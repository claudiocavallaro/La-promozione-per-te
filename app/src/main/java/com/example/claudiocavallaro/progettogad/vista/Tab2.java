package com.example.claudiocavallaro.progettogad.vista;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.claudiocavallaro.progettogad.Activity.ResultActivity;
import com.example.claudiocavallaro.progettogad.R;
import com.example.claudiocavallaro.progettogad.modelliViste.ListAdapterNews;
import com.example.claudiocavallaro.progettogad.modelliViste.ModelloNews;
import com.example.claudiocavallaro.progettogad.modello.Gestore;
import com.example.claudiocavallaro.progettogad.modello.ListaGestori;
import com.example.claudiocavallaro.progettogad.modello.News;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;


public class Tab2 extends android.support.v4.app.Fragment {

    private ResultActivity resultActivity;
    private ModelloNews item;
    private String itemSelected;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println(getId());
        View v = inflater.inflate(R.layout.tab2, container, false);

        String nome = ResultActivity.getNome();
        ArrayList<ModelloNews> models = new ArrayList<ModelloNews>();

        for (Gestore g : ListaGestori.getListaGestori()){
            if (g.getNomeGestore().equals(nome)){
                if (g.getListaNews().size() > models.size()){
                    List<News> listaNews = g.getListaNews();
                    for(News news : listaNews){
                        models.add(new ModelloNews(news.getTitolo(), news.getLink()));
                    }
                }
            }
        }

        ListAdapterNews adapterNews = new ListAdapterNews(this.getActivity(), models);
        final ListView lv = (ListView) v.findViewById(R.id.listView);

        lv.setAdapter(adapterNews);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item = (ModelloNews) lv.getItemAtPosition(position);
                itemSelected = item.getLink();

                System.out.println(itemSelected);


                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(itemSelected));
                startActivity(i);

            }
        });

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        resultActivity = (ResultActivity) activity;
    }
}
