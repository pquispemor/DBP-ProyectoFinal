package com.example.asistenciaunsaapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asistenciaunsaapp.R;
import com.example.asistenciaunsaapp.bean.AttendanceBean;
import com.example.asistenciaunsaapp.bean.AttendanceSessionBean;
import com.example.asistenciaunsaapp.bean.FacultyBean;
import com.example.asistenciaunsaapp.bean.StudentBean;
import com.example.asistenciaunsaapp.context.ApplicationContext;
import com.example.asistenciaunsaapp.db.DBAdapter;

import java.util.ArrayList;
import java.util.Calendar;

public class AddAttandanceSessionFacultyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_attandance_session_faculty);

    }

}