package com.example.macmini_desarrollo2.androidimgur_customgallery.Clients;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by Oswaldo Morales on 09/05/16.
 *
 *
 */
public class RestClient {
    private static final String BASE_URL = "https://api.imgur.com/3/gallery/";
    static String _extURL;
    private static AsyncHttpClient client = new AsyncHttpClient();

    static {
        client.addHeader("Authorization", "Client-ID " + "*********"); // Set Your User Client ID to pass autentification
    }

    //Asgin the Handler and URL
    public static void get_1 (String URL, RequestParams params, AsyncHttpResponseHandler handler){
        _extURL = BASE_URL + URL;
        client.get(_extURL, params, handler);

        Log.d("Tag_2", "URL" +_extURL);


    }




}
