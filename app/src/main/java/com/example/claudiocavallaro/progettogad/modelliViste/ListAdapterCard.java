package com.example.claudiocavallaro.progettogad.modelliViste;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.claudiocavallaro.progettogad.R;

import java.util.ArrayList;

/**
 * Created by claudiocavallaro on 28/01/16.
 */
public class ListAdapterCard extends ArrayAdapter<ModelloCardInfo> {

    private Context context;
    private ArrayList<ModelloCardInfo> models;

    public ListAdapterCard(Context context, ArrayList<ModelloCardInfo> models) {
        super(context, R.layout.card, models);
        this.context = context;
        this.models = models;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.card, parent, false);

        TextView off = (TextView) row.findViewById(R.id.textOfferta);
        off.setText(models.get(position).getCaratteristica());

        ImageView imageView = (ImageView) row.findViewById(R.id.imgOfferta);
        imageView.setImageResource(models.get(position).getLogo());

        return row;
    }
}
