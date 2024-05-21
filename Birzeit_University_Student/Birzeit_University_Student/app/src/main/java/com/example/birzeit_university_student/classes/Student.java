package com.example.birzeit_university_student.classes;

public class Student {

    private int stud_id;
    private String stud_name;
    private String stud_email;
    private String stud_password;
    private String stud_major;


    public Student(int stud_id, String stud_name, String stud_email, String stud_password, String stud_major) {
        this.stud_id = stud_id;
        this.stud_name = stud_name;
        this.stud_email = stud_email;
        this.stud_password = stud_password;
        this.stud_major = stud_major;
    }

    public int getStud_id() {
        return stud_id;
    }

    public void setStud_id(int stud_id) {
        this.stud_id = stud_id;
    }

    public String getStud_name() {
        return stud_name;
    }

    public void setStud_name(String stud_name) {
        this.stud_name = stud_name;
    }

    public String getStud_email() {
        return stud_email;
    }

    public void setStud_email(String stud_email) {
        this.stud_email = stud_email;
    }

    public String getStud_password() {
        return stud_password;
    }

    public void setStud_password(String stud_password) {
        this.stud_password = stud_password;
    }

    public String getStud_major() {
        return stud_major;
    }

    public void setStud_major(String stud_major) {
        this.stud_major = stud_major;
    }


    @Override
    public String toString() {
        return "Student{" +
                "stud_id=" + stud_id +
                ", stud_name='" + stud_name + '\'' +
                ", stud_email='" + stud_email + '\'' +
                ", stud_password='" + stud_password + '\'' +
                ", stud_major='" + stud_major + '\'' +
                '}';
    }
}
