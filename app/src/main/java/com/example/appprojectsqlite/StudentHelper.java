package com.example.appprojectsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class StudentHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "student.db";
    public static final String TABLE_STUDENTS = "tableStudents";
    public static final int DATABASE_VERSION = 1;

    public static final String STUDENT_ID = "studentId";
    public static final String STUDENT_FIRST_NAME = "firstName";
    public static final String STUDENT_LAST_NAME = "lastName";
    public static final String STUDENT_ADDRESS = "address";
    public static final String STUDENT_AVG = "avg";

    // ******************** CREATE TABLE ********************
    private static final String CREATE_TABLE_STUDENT = "CREATE TABLE IF NOT EXISTS " +
            TABLE_STUDENTS + "(" + STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            STUDENT_FIRST_NAME + " VARCHAR, " + STUDENT_LAST_NAME + " VARCHAR, " +
            STUDENT_ADDRESS + " VARCHAR, " + STUDENT_AVG + " INTEGER " + ");";

    private final String[] allColumns = { StudentHelper.STUDENT_ID, StudentHelper.STUDENT_FIRST_NAME
            , StudentHelper.STUDENT_LAST_NAME, StudentHelper.STUDENT_ADDRESS
            , StudentHelper.STUDENT_AVG};

    public StudentHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
        context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE,null);
    }

    SQLiteDatabase database;

    public StudentHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENT);
        Log.i("data1","Table Student Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        onCreate(db);
    }

    public void open(){
        //if (database == null || !database.isOpen()){
        database = this.getWritableDatabase();
        Log.i("data1","Database connection Open");
    }

    // *** SELECT ALL *** //
    public ArrayList<Student> getAllStudents(){
        ArrayList<Student> list = new ArrayList<>();
        String query = String.format("SELECT * FROM %s", TABLE_STUDENTS);
        Cursor cursor = database.rawQuery(query,null);
        if (cursor.getCount() > 0){
            while(cursor.moveToNext()){
                long id = cursor.getLong(cursor.getColumnIndex(StudentHelper.STUDENT_ID));
                String firstName = cursor.getString(cursor.getColumnIndex(StudentHelper.STUDENT_FIRST_NAME));
                String lastName = cursor.getString(cursor.getColumnIndex(StudentHelper.STUDENT_LAST_NAME));
                String address = cursor.getString(cursor.getColumnIndex(StudentHelper.STUDENT_ADDRESS));
                int avg = cursor.getInt(cursor.getColumnIndex(StudentHelper.STUDENT_AVG));
                Student student = new Student((int) id,firstName,lastName,address,avg);
                list.add(student);
            }
        }
        return list;
    }

    // Add Student >>>
    public Student InsertStudent(Student student){
        ContentValues values = new ContentValues();
        values.put(StudentHelper.STUDENT_FIRST_NAME,student.getFirstName());
        values.put(StudentHelper.STUDENT_LAST_NAME,student.getLastName());
        values.put(StudentHelper.STUDENT_ADDRESS,student.getAddress());
        values.put(StudentHelper.STUDENT_AVG,student.getAvg());
        int last_id = (int) database.insert(StudentHelper.TABLE_STUDENTS,null,values);
        student.setStudentId(last_id);
        return student;
    }

    public void updateStudent(Student student){
        ContentValues values = new ContentValues();
        values.put(StudentHelper.STUDENT_ID,student.getStudentId());
        values.put(StudentHelper.STUDENT_FIRST_NAME,student.getFirstName());
        values.put(StudentHelper.STUDENT_LAST_NAME,student.getLastName());
        values.put(StudentHelper.STUDENT_ADDRESS,student.getAddress());
        values.put(StudentHelper.STUDENT_AVG,student.getAvg());

        String where = "id=?";
        String[] whereArgs = {String.valueOf(student.getStudentId())};
        database.update(TABLE_STUDENTS, values, where, whereArgs);
    }

    // Delete Student >>>
    public void deleteStudent(int id){
        String query = String.format("DELETE FROM %s WHERE %s = %d", TABLE_STUDENTS, StudentHelper.STUDENT_ID, id);
        database.execSQL(query);
    }

    public Student getStudentById(long rowId){
        Cursor cursor = database.query(StudentHelper.TABLE_STUDENTS, allColumns,
                StudentHelper.STUDENT_ID +" = "+rowId,null,null,null,null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            long id = cursor.getLong(cursor.getColumnIndex(StudentHelper.STUDENT_ID));
            String firsName = cursor.getString(cursor.getColumnIndex(StudentHelper.STUDENT_FIRST_NAME));
            String lastName = cursor.getString(cursor.getColumnIndex(StudentHelper.STUDENT_LAST_NAME));
            String address = cursor.getString(cursor.getColumnIndex(StudentHelper.STUDENT_ADDRESS));
            int avg = cursor.getInt(cursor.getColumnIndex(StudentHelper.STUDENT_AVG));
            Student student = new Student((int) id,firsName,lastName,address,avg);
            return student;
        }
        return null;
    }

    public ArrayList<Student> searchByName(String text){
        ArrayList<Student> list = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_STUDENTS + " WHERE " + StudentHelper.STUDENT_FIRST_NAME + " LIKE '" + text+"%'";
        Cursor cursor = database.rawQuery(query,null);
        if (cursor.getCount() > 0){
            while(cursor.moveToNext()){
                long id = cursor.getLong(cursor.getColumnIndex(StudentHelper.STUDENT_ID));
                String firstName = cursor.getString(cursor.getColumnIndex(StudentHelper.STUDENT_FIRST_NAME));
                String lastName = cursor.getString(cursor.getColumnIndex(StudentHelper.STUDENT_LAST_NAME));
                String address = cursor.getString(cursor.getColumnIndex(StudentHelper.STUDENT_ADDRESS));
                int avg = cursor.getInt(cursor.getColumnIndex(StudentHelper.STUDENT_AVG));
                Student student = new Student((int) id,firstName,lastName,address,avg);
                list.add(student);
            }
        }
        return list;
    }

    public ArrayList<Student> searchByAverage(int num){
        ArrayList<Student> list = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_STUDENTS + " WHERE " + StudentHelper.STUDENT_AVG + " > " + num;
        Cursor cursor = database.rawQuery(query,null);
        if (cursor.getCount() > 0){
            while(cursor.moveToNext()){
                long id = cursor.getLong(cursor.getColumnIndex(StudentHelper.STUDENT_ID));
                String firstName = cursor.getString(cursor.getColumnIndex(StudentHelper.STUDENT_FIRST_NAME));
                String lastName = cursor.getString(cursor.getColumnIndex(StudentHelper.STUDENT_LAST_NAME));
                String address = cursor.getString(cursor.getColumnIndex(StudentHelper.STUDENT_ADDRESS));
                int avg = cursor.getInt(cursor.getColumnIndex(StudentHelper.STUDENT_AVG));
                Student student = new Student((int) id,firstName,lastName,address,avg);
                list.add(student);
            }
        }
        return list;
    }
}
