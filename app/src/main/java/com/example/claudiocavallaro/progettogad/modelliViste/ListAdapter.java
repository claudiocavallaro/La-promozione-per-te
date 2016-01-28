package com.example.claudiocavallaro.progettogad.modelliViste;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.claudiocavallaro.progettogad.R;

import java.util.ArrayList;


/**
 * Created by claudiocavallaro on 22/10/15.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {


    private ArrayList<ModelloCardItem> models;
    private Context context;
    private LayoutInflater inflater;
    private ClickListener clickListener;
    private LongClickListener longClickListener;
    private SparseBooleanArray selectedItems = new SparseBooleanArray();


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
        viewHolder.itemView.setSelected(getSelectedItems().get(i, false));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }

    public LongClickListener getLongClickListener() {
        return longClickListener;
    }

    public void setLongClickListener(LongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public SparseBooleanArray getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(SparseBooleanArray selectedItems) {
        this.selectedItems = selectedItems;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgThumbnail;
        public TextView titolo;
        public TextView offerta;
        public TextView prezzo;

        public ViewHolder(final View itemView) {
            super(itemView);
            imgThumbnail = (ImageView)itemView.findViewById(R.id.img_thumbnail);
            titolo = (TextView) itemView.findViewById(R.id.titoloOfferta);
            offerta = (TextView) itemView.findViewById(R.id.offerta);
            prezzo = (TextView) itemView.findViewById(R.id.prezzo);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null){
                        clickListener.itemClicked(v, getAdapterPosition());
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    /*if (getLongClickListener() != null){
                        getLongClickListener().itemClicked(v, getAdapterPosition());
                    }*/
                    if (getSelectedItems().get(getAdapterPosition(), false)){
                        getSelectedItems().delete(getAdapterPosition());
                        itemView.setSelected(false);
                    } else {
                        getSelectedItems().put(getAdapterPosition(), true);
                        itemView.setSelected(true);
                        System.out.println(getSelectedItems());
                    }
                    return true;  //QUESTO TRUE FA SI CHE AL LUNGO CLICK NON SI INTERPRETI ANCHE IL CLICK CORTO
                }
            });
        }

    }

    public interface ClickListener{
        public void itemClicked(View view, int position);
    }

    public interface LongClickListener{
        public void itemClicked(View view, int position);
    }
}
