package com.example.claudiocavallaro.progettogad;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.claudiocavallaro.progettogad.modello.ModelloCardItem;
import java.util.ArrayList;


/**
 * Created by claudiocavallaro on 22/10/15.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
//asdasds

    private ArrayList<ModelloCardItem> models;
    private Context context;
    private LayoutInflater inflater;
    private ClickListener clickListener;

    public ListAdapter(Context context, ArrayList<ModelloCardItem> models){
        super();
        this.context = context;
        this.models = models;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ModelloCardItem modelloCardItem = models.get(i);
        viewHolder.titolo.setText(modelloCardItem.getNome());
        viewHolder.imgThumbnail.setImageResource(modelloCardItem.getLogo());
        viewHolder.offerta.setText(modelloCardItem.getOfferta());
        viewHolder.prezzo.setText(modelloCardItem.getPrezzo());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView imgThumbnail;
        public TextView titolo;
        public TextView offerta;
        public TextView prezzo;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView)itemView.findViewById(R.id.img_thumbnail);
            titolo = (TextView) itemView.findViewById(R.id.titoloOfferta);
            offerta = (TextView) itemView.findViewById(R.id.offerta);
            prezzo = (TextView) itemView.findViewById(R.id.prezzo);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (clickListener != null){
                clickListener.itemClicked(v, getPosition());
            }
        }
    }

    public interface ClickListener{
        public void itemClicked(View view, int position);
    }
}
