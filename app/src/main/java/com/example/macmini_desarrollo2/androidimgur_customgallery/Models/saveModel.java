package com.example.macmini_desarrollo2.androidimgur_customgallery.Models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Oswaldo   on 12/05/16.
 *
 *
 */
public class saveModel {

// Save value Int for URL 0 Hot 1 User 2 Top
    public  void  savePreferences (Context context, Integer type){
        SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = shared.edit();
        editor.putInt("value", type);// Asign Value
        editor.apply();

    }

    // Save sort parameters to the User Defaults
    public  void  sortParamenters (Context context, String sort, Boolean showViral){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("sort", sort);
        editor.putBoolean("showViral", showViral);
        editor.apply();

    }

}
