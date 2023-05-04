package com.example.appprojectsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class SearchByAvg extends Menu implements View.OnClickListener {
    private EditText etAvgInput;
    private Button searchAvgBtn;
    private RecyclerView recyclerView;
    List<Student> studentList = new ArrayList<>();
    StudentAdapter adapter;
    StudentHelper studentHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_avg);

        etAvgInput = findViewById(R.id.avgInputEt);
        searchAvgBtn = findViewById(R.id.searchAvgBtn);
        searchAvgBtn.setOnClickListener(this);
        recyclerView = findViewById(R.id.recyclerViewByAvg);
        RecyclerView.LayoutManager recycler = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(recycler);
        studentHelper = new StudentHelper(this);
    }

    @Override
    public void onClick(View v) {
        studentHelper.open();
        int avg = Integer.valueOf(etAvgInput.getText().toString());
        studentList = studentHelper.searchByAverage(avg);
        adapter = new StudentAdapter(SearchByAvg.this,studentList);
        recyclerView.setAdapter(adapter);
        studentHelper.close();
    }
}