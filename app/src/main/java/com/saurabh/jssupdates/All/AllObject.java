package com.saurabh.jssupdates.All;

public class AllObject {
    private String message;
    private  String sender;
    private  String department;
    private String dateAndTime;
    public AllObject(String message, String sender, String department, String dateAndTime)
    {
        this.message = message;
        this.sender = sender;
        this.department = department;
        this.dateAndTime = dateAndTime;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
}
