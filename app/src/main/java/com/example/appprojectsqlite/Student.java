package com.example.appprojectsqlite;

public class Student {
    private int studentId;
    private String firstName;
    private String lastName;
    private String address;
    private int avg;

    public Student(int studentId, String firstName, String lastName, String address, int avg) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.avg = avg;
    }

    public Student(String firstName, String lastName, String address, int avg) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.avg = avg;
        this.studentId = 0;
    }

    public long getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public int getAvg() { return avg; }
    public void setAvg(int avg) { this.avg = avg; }


}
