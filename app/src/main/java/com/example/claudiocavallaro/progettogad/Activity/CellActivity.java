package com.example.claudiocavallaro.progettogad.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.claudiocavallaro.progettogad.R;
import com.example.claudiocavallaro.progettogad.modelliViste.ListAdapterCard;
import com.example.claudiocavallaro.progettogad.modelliViste.ModelloCardInfo;
import com.example.claudiocavallaro.progettogad.modelliViste.ModelloNews;
import com.example.claudiocavallaro.progettogad.modello.ListaGestori;
import com.example.claudiocavallaro.progettogad.modello.Telefono;
import com.example.claudiocavallaro.progettogad.persistenza.RestCallCell;

import java.util.ArrayList;

/**
 * Created by claudiocavallaro on 09/02/16.
 */
public class CellActivity extends AppCompatActivity {

    private ModelloCardInfo item;
    private String itemSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cell);


        RestCallCell restCallCell = new RestCallCell(this);
        RestCallCell.setContext(this);
        restCallCell.execute();

    }

    public void setInterfaccia() {
        ArrayList<ModelloCardInfo> modelloCardInfos = new ArrayList<ModelloCardInfo>();
        for (Telefono telefono : ListaGestori.getListaTelefoni()) {
            modelloCardInfos.add(new ModelloCardInfo(telefono.getModello() + " " + telefono.getCapienza() + " GB", telefono.getLogo()));
        }

        ListAdapterCard listAdapterCard = new ListAdapterCard(this, modelloCardInfos);
        final ListView listView = (ListView) findViewById(R.id.listTel);
        listView.setAdapter(listAdapterCard);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item = (ModelloCardInfo) listView.getItemAtPosition(position);
                itemSelected = item.getCaratteristica();

                Intent i = new Intent(getApplicationContext(), ResultCellActivity.class);
                i.putExtra("nome", itemSelected);
                startActivity(i);
            }
        });
    }
}
