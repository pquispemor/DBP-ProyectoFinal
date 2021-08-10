package com.example.snackdelivery.Adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.snackdelivery.Dominio.CategoriaDominio;
import com.example.snackdelivery.R;

import java.util.ArrayList;

public class CategoriaAdaptador extends RecyclerView.Adapter<CategoriaAdaptador.ViewHolder> {
    ArrayList<CategoriaDominio> categoriaDominios;

    public CategoriaAdaptador(ArrayList<CategoriaDominio> categoriaDominios) {
        this.categoriaDominios = categoriaDominios;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cat,parent,false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.categoriaNombre.setText(categoriaDominios.get(position).getTitulo());
        String picUr1 = "";
        switch (position){
            case 0:{
                picUr1="cat1";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.categoria_background1));
                break;
            }
            case 1:{
                picUr1="cat2";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.categoria_background2));
                break;
            }
            case 2:{
                picUr1="cat3";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.categoria_background3));
                break;
            }
            case 3:{
                picUr1="cat4";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.categoria_background4));
                break;
            }
            case 4:{
                picUr1="cat5";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.categoria_background5));
                break;
            }
        }
        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(picUr1,"drawable",holder.itemView.getContext().getOpPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.categoriaFoto);

    }


    @Override
    public int getItemCount() {
        return categoriaDominios.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView categoriaNombre;
        ImageView categoriaFoto;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoriaNombre = itemView.findViewById(R.id.categoriaNombre);
            categoriaFoto = itemView.findViewById(R.id.categoriaFoto);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
