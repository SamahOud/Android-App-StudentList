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

public class SearchByName extends Menu implements View.OnClickListener {
    private EditText etNameInput;
    private Button searchNameBtn;
    private RecyclerView recyclerView;
    List<Student> studentList = new ArrayList<>();
    StudentAdapter adapter;
    StudentHelper studentHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_name);

        etNameInput = findViewById(R.id.nameInputEt);
        searchNameBtn = findViewById(R.id.searchNameBtn);
        searchNameBtn.setOnClickListener(this);
        recyclerView = findViewById(R.id.recyclerViewByName);
        RecyclerView.LayoutManager recycler = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(recycler);
        studentHelper = new StudentHelper(this);
    }

    @Override
    public void onClick(View v) {
        studentHelper.open();
        String txt = etNameInput.getText().toString();
        studentList = studentHelper.searchByName(txt);
        adapter = new StudentAdapter(SearchByName.this,studentList);
        recyclerView.setAdapter(adapter);
        studentHelper.close();
    }
}