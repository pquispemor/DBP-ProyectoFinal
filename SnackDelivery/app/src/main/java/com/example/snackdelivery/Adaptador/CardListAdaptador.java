package com.example.snackdelivery.Adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.snackdelivery.Activity.MostrarDetallesActivity;
import com.example.snackdelivery.Ayudante.ManagementCard;
import com.example.snackdelivery.Dominio.SnackDominio;
import com.example.snackdelivery.Interfaz.ChangeNumberItemListener;
import com.example.snackdelivery.R;

import java.util.ArrayList;

public class CardListAdaptador extends RecyclerView.Adapter<CardListAdaptador.ViewHolder> {
    private ArrayList<SnackDominio> snackDominios;
    private ManagementCard managementCard;
    private ChangeNumberItemListener changeNumberItemListener;

    public CardListAdaptador(ArrayList<SnackDominio> SnackDominios, Context context, ChangeNumberItemListener changeNumberItemListener) {
        this.snackDominios = SnackDominios;
        managementCard = new ManagementCard(context);
        this.changeNumberItemListener = changeNumberItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_card,parent,false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titulo.setText(snackDominios.get(position).getTitulo());
        holder.precioCadaItem.setText(String.valueOf(snackDominios.get(position).getTarifa()));
        holder.totalCadaItem.setText(String.valueOf(Math.round((snackDominios.get(position).getNumeroTarjeta() * snackDominios.get(position).getTarifa()) * 100.0) / 100.0));
        holder.num.setText(String.valueOf(snackDominios.get(position).getNumeroTarjeta()));

        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(snackDominios.get(position).getFoto(),"drawable",holder.itemView.getContext().getOpPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.foto);

        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCard.plusNumberSnack(snackDominios, position, new ChangeNumberItemListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemListener.changed();
                    }
                });
            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCard.minusNumberSnack(snackDominios, position, new ChangeNumberItemListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemListener.changed();
                    }
                });
            }
        });

    }


    @Override
    public int getItemCount() {
        return snackDominios.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titulo, precioCadaItem;
        ImageView foto, plusItem, minusItem;
        TextView totalCadaItem, num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo2Txt);
            precioCadaItem = itemView.findViewById(R.id.precioCadaItem);
            foto = itemView.findViewById(R.id.fotoCard);
            totalCadaItem = itemView.findViewById(R.id.totalCadaItem);
            num = itemView.findViewById(R.id.numberItemTxt);
            plusItem = itemView.findViewById(R.id.plusBtnCard);
            minusItem = itemView.findViewById(R.id.minusBtnCard);
        }
    }
}
