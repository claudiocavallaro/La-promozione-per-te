package com.example.claudiocavallaro.progettogad.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.claudiocavallaro.progettogad.R;
import com.example.claudiocavallaro.progettogad.modelliViste.SlidingTabLayout;
import com.example.claudiocavallaro.progettogad.vista.ViewPagerAdapter2;

/**
 * Created by claudiocavallaro on 09/02/16.
 */
public class ResultCellActivity extends AppCompatActivity {

    private String nome = "";

    private ViewPager pager;
    private ViewPagerAdapter2 adapter;
    private SlidingTabLayout tabs;

    CharSequence titles[] = {"Prezzi", "Rate","Specifiche"};
    int numOfTabs = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent i = getIntent();
        setNome(i.getStringExtra("nome"));
        this.setTitle(getNome());

        gestisciTabs();
    }

    public void gestisciTabs() {
        adapter = new ViewPagerAdapter2(getSupportFragmentManager(), titles, numOfTabs);

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true);

        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.colorAccent);
            }
        });

        tabs.setViewPager(pager);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
