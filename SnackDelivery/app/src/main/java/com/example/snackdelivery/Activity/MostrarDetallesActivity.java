package com.example.snackdelivery.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.snackdelivery.Ayudante.ManagementCard;
import com.example.snackdelivery.Dominio.SnackDominio;
import com.example.snackdelivery.R;

public class MostrarDetallesActivity extends AppCompatActivity {
    private TextView addToCardBtn;
    private TextView tituloTxt, precioTxt, descripcionTxt, numeroOrdenTxt;
    private ImageView plusBtn, minusBtn, snackFoto;
    private SnackDominio object;
    private int numeroOrden = 1;
    private ManagementCard managementCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_detalles);

        managementCard = new ManagementCard(this);
        initView(); // Vista
        getBundle(); //Conseguir paquete
    }

    private void getBundle() {
        object = (SnackDominio) getIntent().getSerializableExtra("object");

        int drawableResourceId=this.getResources().getIdentifier(object.getFoto(),"drawable",this.getPackageName());

        Glide.with(this)
                .load(drawableResourceId)
                .into(snackFoto);

        tituloTxt.setText(object.getTitulo());
        precioTxt.setText("S/"+object.getTarifa());
        descripcionTxt.setText(object.getDescripcion());
        numeroOrdenTxt.setText(String.valueOf(numeroOrden));

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeroOrden = numeroOrden + 1;
                numeroOrdenTxt.setText(String.valueOf(numeroOrden));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numeroOrden>1){
                    numeroOrden=numeroOrden-1;
                }
                numeroOrdenTxt.setText(String.valueOf(numeroOrden));
            }
        });

        addToCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumeroTarjeta(numeroOrden);
                managementCard.insertarSnack(object);
            }
        });

    }

    private void initView() {
        addToCardBtn = findViewById(R.id.addToCardBtn);
        tituloTxt = findViewById(R.id.tituloTxt);
        precioTxt = findViewById(R.id.precioTxt);
        descripcionTxt = findViewById(R.id.descripcionTxt);
        numeroOrdenTxt = findViewById(R.id.numeroOrdenTxt);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        snackFoto = findViewById(R.id.snackFoto);
    }
}