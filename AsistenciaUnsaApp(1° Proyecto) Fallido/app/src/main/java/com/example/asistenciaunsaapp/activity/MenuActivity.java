package com.example.asistenciaunsaapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.asistenciaunsaapp.R;
import com.example.asistenciaunsaapp.bean.AttendanceBean;
import com.example.asistenciaunsaapp.context.ApplicationContext;
import com.example.asistenciaunsaapp.db.DBAdapter;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    Button addStudent;
    Button addFaculty;
    Button viewStudent;
    Button viewFaculty;
    Button logout;
    Button attendancePerStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        addStudent =(Button)findViewById(R.id.buttonaddstudent);
        addFaculty =(Button)findViewById(R.id.buttonaddfaculty);
        viewStudent =(Button)findViewById(R.id.buttonViewstudent);
        viewFaculty =(Button)findViewById(R.id.buttonviewfaculty);
        logout =(Button)findViewById(R.id.buttonlogout);

        addStudent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent =new Intent(MenuActivity.this,AddStudentActivity.class);
                startActivity(intent);
            }
        });

        addFaculty.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent =new Intent(MenuActivity.this,AddFacultyActivity.class);
                startActivity(intent);
            }
        });

        viewFaculty.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent =new Intent(MenuActivity.this,ViewFacultyActivity.class);
                startActivity(intent);
            }
        });


        viewStudent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent =new Intent(MenuActivity.this,ViewStudentActivity.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent =new Intent(MenuActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        attendancePerStudent=(Button)findViewById(R.id.attendancePerStudentButton);
        attendancePerStudent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                DBAdapter dbAdapter = new DBAdapter(MenuActivity.this);
                ArrayList<AttendanceBean> attendanceBeanList=dbAdapter.getAllAttendanceByStudent();
                ((ApplicationContext)MenuActivity.this.getApplicationContext()).setAttendanceBeanList(attendanceBeanList);

                Intent intent = new Intent(MenuActivity.this,ViewAttendancePerStudentActivity.class);
                startActivity(intent);

            }
        });


    }
}
