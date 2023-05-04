package com.example.appprojectsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudent extends Menu implements View.OnClickListener {
    private EditText etFirstName, etLastName, etAddress, etAvg;
    private Button btnSubmit;
    private StudentHelper studentHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etAddress = findViewById(R.id.etAddress);
        etAvg = findViewById(R.id.etAvg);
        btnSubmit = findViewById(R.id.btnSubmitToAdd);
        btnSubmit.setOnClickListener(this);
        studentHelper = new StudentHelper(this);

    }

    @Override
    public void onClick(View v) {
        studentHelper.open();

        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String address = etAddress.getText().toString();
        int avg = Integer.parseInt(etAvg.getText().toString());

        Student student = new Student(firstName,lastName,address,avg);
        studentHelper.InsertStudent(student);
        if (student.getStudentId() > 0){
            Toast.makeText(this, "Student "+firstName+" ID = " +student.getStudentId()+" added to DB!", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();

        etFirstName.setText("");
        etLastName.setText("");
        etAddress.setText("");
        etAvg.setText("");

        studentHelper.close();
    }
}