package com.example.claudiocavallaro.progettogad.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.claudiocavallaro.progettogad.R;
import com.example.claudiocavallaro.progettogad.modello.Configurazione;

/**
 * Created by claudiocavallaro on 12/12/15.
 */
public class SettingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        final EditText costo = (EditText) findViewById(R.id.editCosto);
        Button salva = (Button) findViewById(R.id.button);
        salva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (costo.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Inserisci un margine valido", Toast.LENGTH_LONG).show();
                } else {
                    try {

                        Configurazione c = new Configurazione(costo.getText().toString());

                        Intent i = new Intent(SettingActivity.this, CercaActivity.class);
                        startActivity(i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }
}
