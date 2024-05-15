package com.example.myapplication;

import android.app.Application;

import com.example.myapplication.ViewModel.UserModel;

public class MyApp extends Application {
    private UserModel user;

    @Override
    public void onCreate() {
        super.onCreate();
        user = null;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
