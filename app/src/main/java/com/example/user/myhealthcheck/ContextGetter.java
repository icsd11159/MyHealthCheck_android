package com.example.user.myhealthcheck;

import android.app.Application;
import android.content.Context;

/**
 * Created by user on 2/1/2018.
 */
public class ContextGetter extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getAppContext(){
        return context;
    }
}