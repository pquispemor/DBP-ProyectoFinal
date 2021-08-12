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

public class AddAttandanceSessionActivity extends AppCompatActivity {
    private ImageButton date;
    private Calendar cal;
    private int day;
    private int month;
    private int dyear;
    private EditText dateEditText;
    Button submit;
    Button viewAttendance;
    Button viewTotalAttendance;
    Spinner spinnerbranch,spinneryear,spinnerSubject;
    String branch = "DBP";
    String year = "A";

    private String[] branchString = new String[] { "DBP", "TI", "CC2", "AC"};
    private String[] yearString = new String[] {"A","B","C"};
    AttendanceSessionBean attendanceSessionBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_attandance_session);


        spinnerbranch=(Spinner)findViewById(R.id.spinner1);
        spinneryear=(Spinner)findViewById(R.id.spinneryear);
        spinnerSubject=(Spinner)findViewById(R.id.spinnerSE);

        ArrayAdapter<String> adapter_branch = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, branchString);
        adapter_branch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerbranch.setAdapter(adapter_branch);
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

        ///......................spinner2
        ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, yearString);
        adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneryear.setAdapter(adapter_year);
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

        ArrayAdapter<String> adapter_subject = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter_subject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubject.setAdapter(adapter_subject);
        spinnerSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });


        date = (ImageButton) findViewById(R.id.DateImageButton);
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        dyear = cal.get(Calendar.YEAR);
        dateEditText = (EditText) findViewById(R.id.DateEditText);
        date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                showDialog(0);

            }
        });

        submit=(Button)findViewById(R.id.buttonsubmit);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                AttendanceSessionBean attendanceSessionBean = new AttendanceSessionBean();
                FacultyBean bean=((ApplicationContext)AddAttandanceSessionActivity.this.getApplicationContext()).getFacultyBean();

                attendanceSessionBean.setAttendance_session_faculty_id(bean.getFaculty_id());
                attendanceSessionBean.setAttendance_session_department(branch);
                attendanceSessionBean.setAttendance_session_class(year);
                attendanceSessionBean.setAttendance_session_date(dateEditText.getText().toString());

                DBAdapter dbAdapter = new DBAdapter(AddAttandanceSessionActivity.this);
                int sessionId=	dbAdapter.addAttendanceSession(attendanceSessionBean);

                ArrayList<StudentBean> studentBeanList=dbAdapter.getAllStudentByBranchYear(branch, year);
                ((ApplicationContext)AddAttandanceSessionActivity.this.getApplicationContext()).setStudentBeanList(studentBeanList);


                Intent intent = new Intent(AddAttandanceSessionActivity.this,AddAttendanceActivity.class);
                intent.putExtra("sessionId", sessionId);
                startActivity(intent);
            }
        });

        viewAttendance=(Button)findViewById(R.id.viewAttendancebutton);
        viewAttendance.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                AttendanceSessionBean attendanceSessionBean = new AttendanceSessionBean();
                FacultyBean bean=((ApplicationContext)AddAttandanceSessionActivity.this.getApplicationContext()).getFacultyBean();

                attendanceSessionBean.setAttendance_session_faculty_id(bean.getFaculty_id());
                attendanceSessionBean.setAttendance_session_department(branch);
                attendanceSessionBean.setAttendance_session_class(year);
                attendanceSessionBean.setAttendance_session_date(dateEditText.getText().toString());

                DBAdapter dbAdapter = new DBAdapter(AddAttandanceSessionActivity.this);

                ArrayList<AttendanceBean> attendanceBeanList = dbAdapter.getAttendanceBySessionID(attendanceSessionBean);
                ((ApplicationContext)AddAttandanceSessionActivity.this.getApplicationContext()).setAttendanceBeanList(attendanceBeanList);

                Intent intent = new Intent(AddAttandanceSessionActivity.this,ViewAttendancePerStudentActivity.class);
                startActivity(intent);

            }
        });

        viewTotalAttendance=(Button)findViewById(R.id.viewTotalAttendanceButton);
        viewTotalAttendance.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                AttendanceSessionBean attendanceSessionBean = new AttendanceSessionBean();
                FacultyBean bean=((ApplicationContext)AddAttandanceSessionActivity.this.getApplicationContext()).getFacultyBean();

                attendanceSessionBean.setAttendance_session_faculty_id(bean.getFaculty_id());
                attendanceSessionBean.setAttendance_session_department(branch);
                attendanceSessionBean.setAttendance_session_class(year);

                DBAdapter dbAdapter = new DBAdapter(AddAttandanceSessionActivity.this);

                ArrayList<AttendanceBean> attendanceBeanList = dbAdapter.getTotalAttendanceBySessionID(attendanceSessionBean);
                ((ApplicationContext)AddAttandanceSessionActivity.this.getApplicationContext()).setAttendanceBeanList(attendanceBeanList);

                Intent intent = new Intent(AddAttandanceSessionActivity.this,ViewAttendanceByFacultyActivity.class);
                startActivity(intent);

            }
        });
    }
    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, datePickerListener, dyear, month, day);
    }
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            dateEditText.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
                    + selectedYear);
        }
    };

}
