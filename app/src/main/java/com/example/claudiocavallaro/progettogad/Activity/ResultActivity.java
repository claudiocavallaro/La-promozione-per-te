package com.example.claudiocavallaro.progettogad.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.claudiocavallaro.progettogad.R;
import com.example.claudiocavallaro.progettogad.modelliViste.SlidingTabLayout;
import com.example.claudiocavallaro.progettogad.vista.ViewPagerAdapter;

/**
 * Created by claudiocavallaro on 10/12/15.
 */
public class ResultActivity extends AppCompatActivity {

    private String nome = "";

    private ViewPager pager;
    private ViewPagerAdapter adapter;
    private SlidingTabLayout tabs;

    CharSequence titles[] = {"Promozioni", "News"};
    int numOfTabs = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent i = getIntent();
        nome = i.getStringExtra("nome");

        this.setTitle(nome);

        gestisciTabs();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    private void gestisciTabs() {
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), titles, numOfTabs);

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

}
