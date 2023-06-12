package com.example.peertutor;
public class User {
    String name,dob,email;
    int semester;

    public User(String name, String dob,String email, int semester) {
        this.name = name;
        this.dob = dob;
        this.semester = semester;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }





    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
