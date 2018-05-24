package com.example.test.domain;

import java.util.Date;

public class Time {

    private  String year;
    private  String month;
    private  String date;
    private  Date   dates;

    public Time(String year, String month, String date, Date dates) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.dates = dates;
    }

    public Time(){
        super();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }
}
