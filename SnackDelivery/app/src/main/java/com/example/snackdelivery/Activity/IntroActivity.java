package com.example.snackdelivery.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.snackdelivery.R;

public class IntroActivity extends AppCompatActivity {
    private Button introbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        introbtn = findViewById(R.id.salirBtn);
        introbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
            }
        });
    }
}