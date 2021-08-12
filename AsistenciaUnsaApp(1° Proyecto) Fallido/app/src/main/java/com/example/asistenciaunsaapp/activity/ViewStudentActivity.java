package com.example.asistenciaunsaapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.asistenciaunsaapp.R;

public class ViewStudentActivity extends AppCompatActivity {

    Spinner spinnerbranch,spinneryear;
    String userrole,branch,year;
    private String[] branchString = new String[] { "DBP", "TI", "CC2", "AC"};
    private String[] yearString = new String[] {"A","B","C"};

    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewstudent);

        spinnerbranch=(Spinner)findViewById(R.id.spinnerbranchView);
        spinneryear=(Spinner)findViewById(R.id.spinneryearView);


        spinnerbranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                branch =(String) spinnerbranch.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        ArrayAdapter<String> adapter_branch = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, branchString);
        adapter_branch
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerbranch.setAdapter(adapter_branch);

        ///......................spinner2

        spinneryear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                year =(String) spinneryear.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, yearString);
        adapter_year
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneryear.setAdapter(adapter_year);

        submit=(Button)findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(ViewStudentActivity.this,ViewStudentByBranchYear.class);
                intent.putExtra("branch", branch);
                intent.putExtra("year", year);
                startActivity(intent);

            }
        });

    }

}
