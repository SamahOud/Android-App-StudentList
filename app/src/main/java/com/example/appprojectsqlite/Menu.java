package com.example.appprojectsqlite;

import android.content.Intent;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity  {
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menus, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.it_studentList:
                Toast.makeText(this, "מסך Login", Toast.LENGTH_LONG).show();
                intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;

            case R.id.it_addStudent:
                Toast.makeText(this, "מסך Permission", Toast.LENGTH_LONG).show();
                intent = new Intent(this,AddStudent.class);
                startActivity(intent);
                break;

            case R.id.it_searchByName:
                Toast.makeText(this, " ", Toast.LENGTH_LONG).show();
                intent = new Intent(this,SearchByName.class);
                startActivity(intent);
                break;

            case R.id.it_searchByAvg:
                Toast.makeText(this, " ", Toast.LENGTH_LONG).show();
                intent = new Intent(this,SearchByAvg.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}
