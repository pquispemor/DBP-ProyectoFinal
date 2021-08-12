package com.example.asistenciaunsaapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asistenciaunsaapp.R;
import com.example.asistenciaunsaapp.bean.FacultyBean;
import com.example.asistenciaunsaapp.db.DBAdapter;

public class AddFacultyActivity extends AppCompatActivity {

    Button registerButton;
    EditText textFirstName;
    EditText textLastName;
    EditText textcontact;
    EditText textaddress;
    EditText textusername;
    EditText textpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfaculty);

        textFirstName=(EditText)findViewById(R.id.editTextFirstName);
        textLastName=(EditText)findViewById(R.id.editTextLastName);
        textcontact=(EditText)findViewById(R.id.editTextPhone);
        textaddress=(EditText)findViewById(R.id.editTextaddr);
        textusername=(EditText)findViewById(R.id.editTextUserName);
        textpassword=(EditText)findViewById(R.id.editTextPassword);
        registerButton=(Button)findViewById(R.id.RegisterButton);

        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String first_name = textFirstName.getText().toString();
                String last_name = textLastName.getText().toString();
                String phone_no = textcontact.getText().toString();
                String address = textaddress.getText().toString();
                String userName = textusername.getText().toString();
                String passWord = textpassword.getText().toString();

                if (TextUtils.isEmpty(first_name)) {
                    textFirstName.setError("por favor ingrese su nombre");
                }
                else if (TextUtils.isEmpty(last_name)) {
                    textLastName.setError("Por favor ingrese su apellido");
                }
                else if (TextUtils.isEmpty(phone_no)) {
                    textcontact.setError("Por favor ingrese su numero de celular");
                }

                else if (TextUtils.isEmpty(address)) {
                    textaddress.setError("por favor ingrese su email");
                }
                else if (TextUtils.isEmpty(userName)) {
                    textcontact.setError("por favor ingrese su usuario");
                }
                else if (TextUtils.isEmpty(passWord)) {
                    textaddress.setError("por favor ingrese su contraseña");
                }
                else {

                    FacultyBean facultyBean = new FacultyBean();
                    facultyBean.setFaculty_firstname(first_name);
                    facultyBean.setFaculty_lastname(last_name);
                    facultyBean.setFaculty_mobilenumber(phone_no);
                    facultyBean.setFaculty_address(address);
                    facultyBean.setFaculty_username(userName);
                    facultyBean.setFaculty_password(passWord);

                    DBAdapter dbAdapter = new DBAdapter(AddFacultyActivity.this);
                    dbAdapter.addFaculty(facultyBean);

                    Intent intent =new Intent(AddFacultyActivity.this,MenuActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Profesor agregado exitosamente", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}

