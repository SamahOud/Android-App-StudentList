package com.example.appprojectsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditDetails extends AppCompatActivity implements View.OnClickListener {
    private EditText etFirstName, etLastName, etAddress, etAvg;
    private Button btnSave;
    private StudentHelper studentHelper;
    int id, avg;
    String firstName, lastName, address;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);

        etFirstName = findViewById(R.id.editFirstName);
        etLastName = findViewById(R.id.editLastName);
        etAddress = findViewById(R.id.editAddress);
        etAvg = findViewById(R.id.editAvg);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        studentHelper = new StudentHelper(this);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        firstName = intent.getStringExtra("firstName");
        lastName = intent.getStringExtra("lastName");
        address = intent.getStringExtra("address");
        avg = intent.getIntExtra("avg",0);

        studentHelper.open();
        student = studentHelper.getStudentById(id);
        if (student != null){
            etFirstName.setText(firstName);
            etLastName.setText(lastName);
            etAddress.setText(address);
            etAvg.setText(avg + "");
        }
        studentHelper.close();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(EditDetails.this,MainActivity.class);
        firstName = etFirstName.getText().toString();
        lastName = etLastName.getText().toString();
        address = etAddress.getText().toString();
        avg = Integer.parseInt(etAvg.getText().toString());

        studentHelper.open();
        student = new Student(id, firstName,lastName,address,avg);
        studentHelper.updateStudent(student);
        if (student.getStudentId() > 0){
//            etFirstName.setText("");
//            etLastName.setText("");
//            etAddress.setText("");
//            etAvg.setText("");
            Toast.makeText(this, "Student: " + firstName +" >>> updated!", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        studentHelper.close();
    }
}