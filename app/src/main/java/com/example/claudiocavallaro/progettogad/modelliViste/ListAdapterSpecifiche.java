package com.example.claudiocavallaro.progettogad.modelliViste;

import android.content.ContentValues;
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
 * Created by claudiocavallaro on 10/02/16.
 */
public class ListAdapterSpecifiche extends ArrayAdapter<ModelloSpecifiche> {

    private Context context;
    private ArrayList<ModelloSpecifiche> models;

    public ListAdapterSpecifiche(Context context, ArrayList<ModelloSpecifiche> models){
        super(context, R.layout.single, models);
        this.context = context;
        this.models = models;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View rowView = null;

        if(!models.get(position).isGruppo()){
            rowView = inflater.inflate(R.layout.single, parent, false);

            ImageView imgView = (ImageView) rowView.findViewById(R.id.imageView);
            TextView tv = (TextView) rowView.findViewById(R.id.textView);

            imgView.setImageResource(models.get(position).getIcon());
            tv.setText(models.get(position).getTitle());
        } else {
            rowView = inflater.inflate(R.layout.group, parent, false);
            TextView title = (TextView) rowView.findViewById(R.id.header);
            title.setText(models.get(position).getTitle());

        }

        return rowView;
    }
}
