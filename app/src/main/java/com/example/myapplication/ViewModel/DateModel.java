package com.example.myapplication.ViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateModel {
    private int day;
    private int month;
    private int year;
    private Date DateNow;

    public DateModel() {
        this.DateNow = new Date();
        this.day = this.DateNow.getDate();
        this.month = this.DateNow.getMonth() + 1;
        this.year = this.DateNow.getYear() + 1900;
    }

    public String getDateNow() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(this.DateNow);
    }
}
