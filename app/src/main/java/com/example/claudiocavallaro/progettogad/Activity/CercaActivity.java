package com.example.claudiocavallaro.progettogad.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.claudiocavallaro.progettogad.modelliViste.ListAdapter;
import com.example.claudiocavallaro.progettogad.R;
import com.example.claudiocavallaro.progettogad.modello.Caratteristiche;
import com.example.claudiocavallaro.progettogad.modello.Configurazione;
import com.example.claudiocavallaro.progettogad.modello.ListaGestori;
import com.example.claudiocavallaro.progettogad.modelliViste.ModelloCardItem;
import com.example.claudiocavallaro.progettogad.modello.Promozione;

import org.w3c.dom.Document;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

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
        int margine = 0;

        if (Configurazione.getCosto() == null) {
            Configurazione.setCosto("2");
            margine = new Integer(Configurazione.getCosto());
            System.out.println(Configurazione.getCosto());
        } else {
            margine = new Integer(Configurazione.getCosto());
            System.out.println(Configurazione.getCosto());
        }


        final EditText editText = (EditText) findViewById(R.id.editText);
        final EditText editMinuti = (EditText) findViewById(R.id.editMinuti);
        final EditText editSms = (EditText) findViewById(R.id.editSMS);
        final EditText editInternet = (EditText) findViewById(R.id.editInternet);
        Button cerca = (Button) findViewById(R.id.buttoCerca);
        final int finalMargine = margine;
        cerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appoggio.clear();
                models.clear();
                //SERVE A CHIUDERE LA TASTIERA ALLA PRESSIONE DEL TASTO CERCA
                if (v != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                //-----------------------------------------------------------

                //CONTROLLI SE I CAMPI SONO VUOTI O MENO
                if (editText.getText().toString().equals("")) {
                    System.out.println("vuoti");
                    Toast.makeText(getApplicationContext(), "Campo costo vuoto\nImpossibile proseguire", Toast.LENGTH_LONG).show();
                } else if (editInternet.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Campo internet vuoto\nImpossibile proseguire", Toast.LENGTH_LONG).show();
                } else if (editSms.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Campo sms vuoto\nImpossibile proseguire", Toast.LENGTH_LONG).show();
                } else if (editMinuti.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Campo minuti vuoto\nImpossibile proseguire", Toast.LENGTH_LONG).show();
                } else {
                    int costo = new Integer(editText.getText().toString());
                    for (Promozione p : ListaGestori.getListaPromozioni()) {
                        if (costo == p.getCosto() || costo + finalMargine == p.getCosto() || costo + finalMargine > p.getCosto()) {
                            List<Caratteristiche> lista = p.getListaCaratteristiche();
                            int mioMinuti = new Integer(editMinuti.getText().toString());
                            int mioSms = new Integer(editSms.getText().toString());
                            double mioInternet = new Double(editInternet.getText().toString());
                            int minuti = 0;
                            int sms = 0;
                            double internet = 0;
                            for (Caratteristiche c : lista) {
                                if (c.getNomeCaratteristica().equals("Minuti verso tutti")) {
                                    if (c.getQuantita().equals("Illimitati")|| c.getQuantita().equals("illimitati")) {
                                        minuti = 60000;
                                    } else {
                                        try {
                                            minuti = new Integer(c.getQuantita());
                                        } catch (Exception e) {
                                            minuti = new Integer(c.getQuantita().substring(0, c.getQuantita().length() - 1));
                                        }
                                        System.out.println(minuti + " mio minuti " + mioMinuti + " " + c.getQuantita());
                                    }
                                }
                                if (c.getNomeCaratteristica().equals("SMS verso tutti")) {
                                    if (c.getQuantita().equals("Illimitati") || c.getQuantita().equals("illimitati")) {
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
                            if ((mioInternet <= internet) && (mioMinuti <= minuti) && (mioSms <= sms)) {
                                appoggio.add(p);
                            }

                        }
                    }

                    if (appoggio.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Nessuna promozione trovata con le caratteristiche richieste !\nRiprovare", Toast.LENGTH_SHORT).show();
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cerca, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.setting) {
            Intent i = new Intent(CercaActivity.this, SettingActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
