package com.example.birzeit_university_student.classes;

public class Major {
    private String major_name;
    private double major_cost_per_hour;
    private String major_image;

    public Major(String major_name, double major_cost_per_hour, String major_image) {
        this.major_name = major_name;
        this.major_cost_per_hour = major_cost_per_hour;
        this.major_image = major_image;
    }

    public String getMajor_name() {
        return major_name;
    }

    public void setMajor_name(String major_name) {
        this.major_name = major_name;
    }

    public double getMajor_cost_per_hour() {
        return major_cost_per_hour;
    }

    public void setMajor_cost_per_hour(double major_cost_per_hour) {
        this.major_cost_per_hour = major_cost_per_hour;
    }

    public String getMajor_image() {
        return major_image;
    }

    public void setMajor_image(String major_image) {
        this.major_image = major_image;
    }
}
