package com.example.snackdelivery.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.snackdelivery.Adaptador.CardListAdaptador;
import com.example.snackdelivery.Ayudante.ManagementCard;
import com.example.snackdelivery.Interfaz.ChangeNumberItemListener;
import com.example.snackdelivery.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CarroListaActivity extends AppCompatActivity {
    private RecyclerView.Adapter adaptador;
    private RecyclerView recyclerViewList;
    private ManagementCard managementCard;
    private TextView totalPrecioTxt, impuestoTxt, deliveryTxt, totalTxt, vacioTxt;
    private Double impuesto;
    private ScrollView scrollView;
    private TextView confirBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro_lista);

        managementCard=new ManagementCard(this);
        initView();
        initList();
        calculateCard();
        bottomNavigation();

        confirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CarroListaActivity.this, CompraFinalizadaActivity.class));
            }
        });


    }


    private void bottomNavigation() {
        FloatingActionButton floatingActionButton=findViewById(R.id.card_btn);
        LinearLayout inicioBtn=findViewById(R.id.iniciobtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CarroListaActivity.this, CarroListaActivity.class));
            }
        });

        inicioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CarroListaActivity.this, MainActivity.class));
            }
        });
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adaptador = new CardListAdaptador(managementCard.getListCard(), this, new ChangeNumberItemListener() {
            @Override
            public void changed() {

            }
        });
        recyclerViewList.setAdapter(adaptador);
        if (managementCard.getListCard().isEmpty()){
            vacioTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else {
            vacioTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCard(){
        double porcientoImpuesto = 0.02;
        double delivery = 10;

        impuesto = Math.round((managementCard.getTotalPrecio() * porcientoImpuesto) * 100.0) /100.0;
        double total = Math.round((managementCard.getTotalPrecio() + impuesto + delivery) * 100.0) / 100.0;
        double itemTotal = Math.round(managementCard.getTotalPrecio() * 100.0) / 100.0;

        totalPrecioTxt.setText("S/" + itemTotal);
        impuestoTxt.setText("S/" + impuesto);
        deliveryTxt.setText("S/" + delivery);
        totalTxt.setText("S/" + total);
    }

    private void initView() {
        recyclerViewList=findViewById(R.id.recyclerView3);
        totalPrecioTxt=findViewById(R.id.totalPrecioTxt);
        impuestoTxt=findViewById(R.id.impuestoTxt);
        deliveryTxt=findViewById(R.id.deliveryTxt);
        totalTxt=findViewById(R.id.totalTxt);
        vacioTxt=findViewById(R.id.vacioTxt);
        scrollView=findViewById(R.id.scrollView3);
        confirBtn=findViewById(R.id.confirmarBtn);

    }
}