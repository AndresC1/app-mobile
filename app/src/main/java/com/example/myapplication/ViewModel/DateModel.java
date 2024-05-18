package com.example.myapplication.ViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateModel {
    private int day;
    private int month;
    private int year;
    private Date DateNow;
    private String[] monthName = {
            "Jan", "Feb", "Mar",
            "Apr", "May", "Jun", "Jul",
            "Aug", "Sep", "Oct",
            "Nov", "Dec"
    };

    public DateModel() {
        this.DateNow = new Date();
        this.day = this.DateNow.getDate();
        this.month = this.DateNow.getMonth();
        this.year = this.DateNow.getYear() + 1900;
    }

    public String getDateNow() {
        return this.day + "-" + this.monthName[month] + "-" + this.year;
    }
}
