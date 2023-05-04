package com.example.appprojectsqlite;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Menu implements View.OnClickListener {
    RecyclerView recyclerView;
    private Button btnShow;
    StudentAdapter studentAdapter;
    StudentHelper studentHelper;
    List<Student> studentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("     welcome ...");
        actionBar.setSubtitle("      Student System");
        actionBar.setIcon(R.drawable.shcool);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnShow = findViewById(R.id.btnShow);
        btnShow.setOnClickListener(this);

        studentHelper = new StudentHelper(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnShow){
            CardView cv = findViewById(R.id.topicsCard);
            cv.setVisibility(View.VISIBLE);
            studentHelper.open();
            studentList = studentHelper.getAllStudents();
            studentAdapter = new StudentAdapter(this, studentList);
            recyclerView.setAdapter(studentAdapter);
            studentAdapter.setListener(new StudentAdapter.MyStudentListener() {
                @Override
                public void onStudentClicked(int position, View view) {
                    Intent intent = new Intent(MainActivity.this,EditDetails.class);
                    int id = (int) studentList.get(position).getStudentId();
                    String firstName = studentList.get(position).getFirstName();
                    String lastName = studentList.get(position).getLastName();
                    String Address = studentList.get(position).getAddress();
                    int avg = studentList.get(position).getAvg();
                    intent.putExtra("id",id);
                    intent.putExtra("firstName",firstName);
                    intent.putExtra("lastName",lastName);
                    intent.putExtra("address",Address);
                    intent.putExtra("avg",avg);
                    startActivity(intent);
                    String str ="ID_" + id +" : " + firstName + " " + lastName;
                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onLongStudentClicked(int position, View view) {
                    studentHelper.open();
                    int id = (int) studentList.get(position).getStudentId();
                    String firstName = studentList.get(position).getFirstName();
                    studentHelper.deleteStudent((int) studentList.get(position).getStudentId());
                    studentList.remove(position);
                    studentAdapter.notifyItemRemoved(position);
                    Toast.makeText(MainActivity.this, "ID_" + id +" : " + firstName + " Deleted!", Toast.LENGTH_SHORT).show();
                    studentHelper.close();
                }
            });

            studentHelper.close();
        }
    }
}