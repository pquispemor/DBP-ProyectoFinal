package com.example.asistenciaunsaapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asistenciaunsaapp.R;
import com.example.asistenciaunsaapp.bean.StudentBean;
import com.example.asistenciaunsaapp.db.DBAdapter;

public class AddStudentActivity extends AppCompatActivity {

    Button registerButton;
    EditText textFirstName;
    EditText textLastName;
    EditText textusername;
    EditText textpassword;
    EditText textcontact;
    EditText textaddress;
    Spinner spinnerbranch,spinneryear;
    String userrole,branch,year;
    private String[] branchString = new String[] { "DBP", "TI", "CC2", "AC"};
    private String[] yearString = new String[] {"A","B","C"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addstudent);

        spinnerbranch=(Spinner)findViewById(R.id.spinnerdept);
        spinneryear=(Spinner)findViewById(R.id.spinneryear);
        textFirstName=(EditText)findViewById(R.id.editTextFirstName);
        textLastName=(EditText)findViewById(R.id.editTextLastName);
        textcontact=(EditText)findViewById(R.id.editTextPhone);
        textaddress=(EditText)findViewById(R.id.editTextaddr);
        textusername=(EditText)findViewById(R.id.editTextUserName);
        textpassword=(EditText)findViewById(R.id.editTextPassword);
        registerButton=(Button)findViewById(R.id.RegisterButton);

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



        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //......................................validation
                String first_name = textFirstName.getText().toString();
                String last_name = textLastName.getText().toString();
                String phone_no = textcontact.getText().toString();
                String address = textaddress.getText().toString();
                String userName = textusername.getText().toString();
                String passWord = textpassword.getText().toString();

                if (TextUtils.isEmpty(first_name)) {
                    textFirstName.setError("Por favor ingrese su nombre");
                }

                else if (TextUtils.isEmpty(last_name)) {
                    textLastName.setError("Por favor ingrese su apellido");
                }
                else if (TextUtils.isEmpty(phone_no)) {
                    textcontact.setError("Por favor ingrese su numero de celular");
                }

                else if (TextUtils.isEmpty(address)) {
                    textaddress.setError("Por favor ingrese su email");
                }
                else if (TextUtils.isEmpty(userName)) {
                    textcontact.setError("Por favor ingrese su usuario");
                }
                else if (TextUtils.isEmpty(passWord)) {
                    textaddress.setError("Por favor ingrese su password");
                }
                else {

                    StudentBean studentBean = new StudentBean();

                    studentBean.setStudent_firstname(first_name);
                    studentBean.setStudent_lastname(last_name);
                    studentBean.setStudent_mobilenumber(phone_no);
                    studentBean.setStudent_address(address);
                    studentBean.setStudent_department(branch);
                    studentBean.setStudent_class(year);
                    studentBean.setStudent_username(userName);
                    studentBean.setStudent_password(passWord);

                    DBAdapter dbAdapter= new DBAdapter(AddStudentActivity.this);
                    dbAdapter.addStudent(studentBean);

                    Intent intent =new Intent(AddStudentActivity.this,MenuActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Estudiante Registrado Exitosamente", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}


