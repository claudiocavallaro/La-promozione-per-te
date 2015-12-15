package com.example.claudiocavallaro.progettogad.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.claudiocavallaro.progettogad.R;
import com.example.claudiocavallaro.progettogad.modelliViste.SlidingTabLayout;
import com.example.claudiocavallaro.progettogad.persistenza.RestCall;
import com.example.claudiocavallaro.progettogad.persistenza.RestCallNews;
import com.example.claudiocavallaro.progettogad.vista.ViewPagerAdapter;

/**
 * Created by claudiocavallaro on 10/12/15.
 */
public class ResultActivity extends AppCompatActivity {

    private static String nome = "";

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


        RestCallNews call = new RestCallNews(this);
        RestCallNews.setContext(this);
        call.execute();


    }

    public static String getNome() {
        return nome;
    }

    public void gestisciTabs() {
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
