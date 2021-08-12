package com.example.asistenciaunsaapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asistenciaunsaapp.R;
import com.example.asistenciaunsaapp.bean.FacultyBean;
import com.example.asistenciaunsaapp.db.DBAdapter;

import java.util.ArrayList;

public class ViewFacultyActivity extends AppCompatActivity {

    ArrayList<FacultyBean> facultyBeanList;
    private ListView listView ;
    private ArrayAdapter<String> listAdapter;

    DBAdapter dbAdapter = new DBAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.__listview_main);

        listView=(ListView)findViewById(R.id.listview);
        final ArrayList<String> facultyList = new ArrayList<String>();

        facultyBeanList=dbAdapter.getAllFaculty();

        for(FacultyBean facultyBean : facultyBeanList)
        {
            String users = " Nombre: " + facultyBean.getFaculty_firstname()+"\nApellido:"+facultyBean.getFaculty_lastname();

            facultyList.add(users);
            Log.d("users: ", users);

        }

        listAdapter = new ArrayAdapter<String>(this, R.layout.view_faculty_list, R.id.labelF, facultyList);
        listView.setAdapter( listAdapter );

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int position, long arg3) {



                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewFacultyActivity.this);

                alertDialogBuilder.setTitle(getTitle()+" Decision!");
                alertDialogBuilder.setMessage("Are you sure want to delete?");

                alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                        facultyList.remove(position);
                        listAdapter.notifyDataSetChanged();
                        listAdapter.notifyDataSetInvalidated();

                        dbAdapter.deleteFaculty(facultyBeanList.get(position).getFaculty_id());
                        facultyBeanList=dbAdapter.getAllFaculty();

                        for(FacultyBean facultyBean : facultyBeanList)
                        {
                            String users = " Nombre: " + facultyBean.getFaculty_firstname()+"\nApellido:"+facultyBean.getFaculty_lastname();
                            facultyList.add(users);
                            Log.d("users: ", users);

                        }

                    }

                });
                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // cancel the alert box and put a Toast to the user
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(), "You choose cancel",
                                Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                // show alert
                alertDialog.show();





                return false;
            }
        });

    }

}



