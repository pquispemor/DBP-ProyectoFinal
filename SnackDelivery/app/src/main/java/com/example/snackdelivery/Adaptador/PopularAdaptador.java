package com.example.snackdelivery.Adaptador;

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
import com.example.snackdelivery.Dominio.SnackDominio;
import com.example.snackdelivery.R;

import java.util.ArrayList;

public class PopularAdaptador extends RecyclerView.Adapter<PopularAdaptador.ViewHolder> {
    ArrayList<SnackDominio> snackDominios;

    public PopularAdaptador(ArrayList<SnackDominio> SnackDominios) {
        this.snackDominios = SnackDominios;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular,parent,false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titulo.setText(snackDominios.get(position).getTitulo());
        holder.tarifa.setText(String.valueOf(snackDominios.get(position).getTarifa()));

        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(snackDominios.get(position).getFoto(),"drawable",holder.itemView.getContext().getOpPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.foto);

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), MostrarDetallesActivity.class);
                intent.putExtra("object", snackDominios.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return snackDominios.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titulo, tarifa;
        ImageView foto;
        TextView addBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo);
            tarifa = itemView.findViewById(R.id.tarifa);
            foto = itemView.findViewById(R.id.foto);
            addBtn = itemView.findViewById(R.id.addBtn);
        }
    }
}
