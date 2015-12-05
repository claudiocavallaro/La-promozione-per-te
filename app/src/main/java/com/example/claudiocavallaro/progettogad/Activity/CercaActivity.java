package com.example.claudiocavallaro.progettogad.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.claudiocavallaro.progettogad.ListAdapter;
import com.example.claudiocavallaro.progettogad.R;
import com.example.claudiocavallaro.progettogad.modello.Caratteristiche;
import com.example.claudiocavallaro.progettogad.modello.ListaGestori;
import com.example.claudiocavallaro.progettogad.modello.ModelloCardItem;
import com.example.claudiocavallaro.progettogad.modello.Promozione;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by claudiocavallaro on 04/12/15.
 */
public class CercaActivity extends AppCompatActivity {

    private ArrayList<ModelloCardItem> models = new ArrayList<ModelloCardItem>();
    private List<Promozione> appoggio = new ArrayList<Promozione>();
    private RecyclerView mRecycler;
    private RecyclerView.LayoutManager layoutManager;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerca);

        final EditText editText = (EditText) findViewById(R.id.editText);
        final EditText editMinuti = (EditText) findViewById(R.id.editMinuti);
        final EditText editSms = (EditText) findViewById(R.id.editSMS);
        final EditText editInternet = (EditText) findViewById(R.id.editInternet);
        Button cerca = (Button) findViewById(R.id.buttoCerca);
        cerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SERVE A CHIUDERE LA TASTIERA ALLA PRESSIONE DEL TASTO CERCA
                if (v != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                //-----------------------------------------------------------


                //CONTROLLI SE I CAMPI SONO VUOTI O MENO
                if (editText.getText().toString().equals("") || editInternet.getText().equals("") || editMinuti.getText().equals("") || editSms.getText().equals("")) {
                    Toast.makeText(getApplicationContext(), "Uno o più campi sono vuoti\nImpossibile proseguire", Toast.LENGTH_LONG).show();
                } else {
                    int costo = new Integer(editText.getText().toString());
                    for (Promozione p : ListaGestori.getListaPromozioni()) {
                        if (costo == p.getCosto() || costo + 2 == p.getCosto() || costo > p.getCosto()) {
                            List<Caratteristiche> lista = p.getListaCaratteristiche();
                            int mioMinuti = new Integer(editMinuti.getText().toString());
                            int mioSms = new Integer(editSms.getText().toString());
                            double mioInternet = new Double(editInternet.getText().toString());
                            int minuti = 0;
                            int sms = 0;
                            double internet = 0;
                            for (Caratteristiche c : lista) {
                                if (c.getNomeCaratteristica().equals("Minuti verso tutti")) {
                                    if (c.getQuantita().equals("Illimitati")) {
                                        minuti = 60000;
                                    } else {
                                        try {
                                            minuti = new Integer(c.getQuantita());
                                        } catch (Exception e) {
                                            minuti = new Integer(c.getQuantita().substring(0, c.getQuantita().length() - 1));
                                        }
                                        System.out.println(minuti + " mio minuti " + mioMinuti);
                                    }
                                }
                                if (c.getNomeCaratteristica().equals("SMS verso tutti")) {
                                    if (c.getQuantita().equals("Illimitati")) {
                                        sms = 60000;
                                    } else {
                                        try {
                                            sms = new Integer(c.getQuantita());
                                        } catch (Exception e) {
                                            sms = new Integer(c.getQuantita().substring(0, c.getQuantita().length() - 1));
                                        }
                                    }

                                }
                                if (c.getNomeCaratteristica().equals("Internet in 4G")) {
                                    internet = new Double(c.getQuantita().trim());
                                }
                            }
                            if ((mioInternet <= internet) && (mioMinuti <= minuti)) {
                                appoggio.add(p);
                            }

                        }
                    }

                    for (Promozione p : appoggio) {
                        System.out.println("size " + appoggio.size());
                        String costoP = String.valueOf((int) p.getCosto());
                        models.add(new ModelloCardItem(p.getId(), p.getGestore().getLogo(), p.getNome(), p.getOfferta(), costoP + " €"));
                    }

                    mRecycler = (RecyclerView) findViewById(R.id.recycler_view);
                    layoutManager = new LinearLayoutManager(getApplicationContext());
                    mRecycler.setLayoutManager(layoutManager);

                    listAdapter = new ListAdapter(getApplicationContext(), models);
                    listAdapter.setClickListener(new ListAdapter.ClickListener() {
                        @Override
                        public void itemClicked(View view, int position) {
                            //Toast.makeText(getApplicationContext(), "ho cliccato " + models.get(position).getNome(), Toast.LENGTH_LONG).show();
                            Intent i = new Intent(CercaActivity.this, CardActivity.class);
                            i.putExtra("promo", models.get(position).getId());
                            startActivity(i);
                        }
                    });
                    mRecycler.setAdapter(listAdapter);
                }

            }
        });
    }
}
