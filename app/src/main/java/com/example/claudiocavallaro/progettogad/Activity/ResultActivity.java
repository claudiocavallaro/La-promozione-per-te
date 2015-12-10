package com.example.claudiocavallaro.progettogad.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.claudiocavallaro.progettogad.R;

/**
 * Created by claudiocavallaro on 10/12/15.
 */
public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent i = getIntent();
        String nome = i.getStringExtra("nome");

        TextView tv = (TextView) findViewById(R.id.cacca);
        tv.setText(nome);

        this.setTitle(nome);
    }

}
