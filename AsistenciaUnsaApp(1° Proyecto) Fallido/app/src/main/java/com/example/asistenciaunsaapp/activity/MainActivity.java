package com.example.asistenciaunsaapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.asistenciaunsaapp.R;

public class MainActivity extends AppCompatActivity {

    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start =(Button)findViewById(R.id.buttonstart);
        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent =new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}