package com.example.ass1_duhaidi;

public class Task {

    private String name;
    private String content;
    private String status;
    private String datetext;

    public Task(String name, String content, String status,String datetext) {
        this.name = name;
        this.content = content;
        this.status = status;
        this.datetext=datetext;
    }

    public String getDatetext() {
        return datetext;
    }

    public void setDatetext(String datetext) {
        this.datetext = datetext;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task name:"+name+"\n"+
                "Task Content:"+content+"\n"+
                "Task Status:"+status+"\n"+
                "Task Date:"+datetext;
    }
}
