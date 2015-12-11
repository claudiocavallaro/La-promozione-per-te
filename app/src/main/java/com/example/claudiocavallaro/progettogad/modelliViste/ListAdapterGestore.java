package com.example.claudiocavallaro.progettogad.modelliViste;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.claudiocavallaro.progettogad.R;

import java.util.ArrayList;

/**
 * Created by claudiocavallaro on 10/12/15.
 */
public class ListAdapterGestore extends RecyclerView.Adapter<ListAdapterGestore.ViewHolder> {

    private ArrayList<ModelloCardGestore> models;
    private Context context;
    private LayoutInflater inflater;
    private ClickListener clickListener;

    public ListAdapterGestore(Context context, ArrayList<ModelloCardGestore> models) {
        super();
        this.context = context;
        this.models = models;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item_gestore, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ModelloCardGestore modelloCardGestore = models.get(i);
        viewHolder.titolo.setText(modelloCardGestore.getNome());
        viewHolder.imgThumbnail.setImageResource(modelloCardGestore.getLogo());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imgThumbnail;
        public TextView titolo;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.img_thumbnail);
            titolo = (TextView) itemView.findViewById(R.id.titoloOfferta);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.itemClicked(v, getPosition());
            }
        }
    }

    public interface ClickListener {
        public void itemClicked(View view, int position);
    }
}
