package com.example.snackdelivery.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.snackdelivery.Adaptador.CardListAdaptador;
import com.example.snackdelivery.Adaptador.CategoriaAdaptador;
import com.example.snackdelivery.Adaptador.PopularAdaptador;
import com.example.snackdelivery.Dominio.CategoriaDominio;
import com.example.snackdelivery.Dominio.SnackDominio;
import com.example.snackdelivery.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();
    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton=findViewById(R.id.card_btn);
        LinearLayout inicioBtn=findViewById(R.id.iniciobtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CarroListaActivity.class));
            }
        });

        inicioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
    }

    private void recyclerViewPopular() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<SnackDominio> snacklist = new ArrayList<>();
        snacklist.add(new SnackDominio("Cupcake de Chocolate", "popular1", "Estos deliciosos cupcakes son de lo mejor, pues son muy esponjosos y con un delicioso sabor a chocolate. ¡Tienen que probarlo alguna vez en su vida!!", 1.20));
        snacklist.add(new SnackDominio("Cheetos", "popular2","El intenso sabor a queso con una textura liviana y esponjosa. Los CHEETOS, bocadillos sabor a queso, están llenos de sabor y ¡hechos con queso de verdad!", 1.00));
        snacklist.add(new SnackDominio("Galleta OREO", "popular3", "Galletas de Oreo sabor pastel ahora en una presentación más grande para compartir. Son ideal para comerlas en el camino.", 0.80));
        snacklist.add(new SnackDominio("Coca Cola", "popular4", "Bebida gaseosa y refrescante vendida a nivel mundial en tiendas, restaurantes y máquinas expendedoras en más de doscientos países o territorios.", 2.50));

        adapter2 = new PopularAdaptador(snacklist);
        recyclerViewPopularList.setAdapter(adapter2);
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList=findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoriaDominio> categoryList=new ArrayList<>();
        categoryList.add(new CategoriaDominio("Galletas","cat1"));
        categoryList.add(new CategoriaDominio("Pasteles","cat2"));
        categoryList.add(new CategoriaDominio("Bebidas","cat3"));
        categoryList.add(new CategoriaDominio("Chizitos","cat4"));
        categoryList.add(new CategoriaDominio("Donuts","cat5"));

        adapter=new CategoriaAdaptador(categoryList);
        recyclerViewCategoryList.setAdapter(adapter);

    }
}