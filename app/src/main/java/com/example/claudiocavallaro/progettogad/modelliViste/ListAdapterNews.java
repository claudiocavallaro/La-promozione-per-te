package com.example.claudiocavallaro.progettogad.modelliViste;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.claudiocavallaro.progettogad.R;

import java.util.ArrayList;

/**
 * Created by claudiocavallaro on 15/12/15.
 */
public class ListAdapterNews extends ArrayAdapter<ModelloNews> {

    private Context context;
    private ArrayList<ModelloNews> models;

    public ListAdapterNews(Context context, ArrayList<ModelloNews> models) {
        super(context, R.layout.news, models);
        this.context = context;
        this.models = models;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.news, parent, false);

        TextView news = (TextView) row.findViewById(R.id.news);
        news.setText(models.get(position).getTitolo());

        return row;
    }
}
