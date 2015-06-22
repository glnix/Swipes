package com.example.alekseygoryachev.swipes;

import android.app.Application;

import com.activeandroid.ActiveAndroid;


public class SwipesApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //ORM init
        ActiveAndroid.initialize(this);
    }
}
