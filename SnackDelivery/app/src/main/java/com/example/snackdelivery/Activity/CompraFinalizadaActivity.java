package com.example.snackdelivery.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.snackdelivery.R;

public class CompraFinalizadaActivity extends AppCompatActivity {
    private Button salirBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra_finalizada);

        salirBtn = findViewById(R.id.salirBtn);
        salirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CompraFinalizadaActivity.this, MainActivity.class));
            }
        });
    }
}