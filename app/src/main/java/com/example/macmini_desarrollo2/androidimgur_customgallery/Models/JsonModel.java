package com.example.macmini_desarrollo2.androidimgur_customgallery.Models;

import android.graphics.Bitmap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Oswaldo Morales on 09/05/16.
 *
 */
public class JsonModel {



    public  ArrayList<String> titleArray = new ArrayList<>();
    public  ArrayList <String> decriptionArray = new ArrayList<>();
    public  ArrayList <Boolean> nsfwBoolean = new ArrayList<>();
    public  ArrayList <String>  sectionArray = new ArrayList<>();
    public  ArrayList <String> linkArray = new ArrayList<>();
    public  ArrayList <Integer> upsArray = new ArrayList<>();
    public  ArrayList <Integer>  downsArray = new ArrayList<>();
    public  ArrayList <Integer> pointsArray = new ArrayList<>();
    public  ArrayList <Integer> viewsArray = new ArrayList<>();
    public  ArrayList <String> typeArray = new ArrayList<>();
    public  ArrayList <Integer> scoreArray = new ArrayList<>();
    public  ArrayList <Bitmap> picassos = new ArrayList<>();



    public  void  arrayObject (JSONArray array) throws JSONException {

        //Empty All Arrays
        titleArray.clear();
        decriptionArray.clear();
        nsfwBoolean.clear();
        sectionArray.clear();
        linkArray.clear();
        upsArray.clear();
        downsArray.clear();
        pointsArray.clear();
        viewsArray.clear();
        typeArray.clear();
        scoreArray.clear();
        picassos.clear();

        // index Array in a Json Responce
        for (int i = 0; i < array.length(); i++){
            JSONObject object = array.getJSONObject(i);
            String title = object.getString("title");
            String description = object.getString("description");
            Boolean nsfw = object.getBoolean("nsfw");
            String section = object.getString("section");
            String link = object.getString("link");
            Integer ups = object.getInt("ups");
            Integer downs = object.getInt("downs");
            Integer points = object.getInt("points");
            Integer views = object.getInt("views");
            Integer score = object.getInt("score");


            String subString = link.substring(link.length() - 3);
            //if Link have a extencion in JPG or PNG add Objects
            if (subString.equals("jpg") || subString.equals("gif") || subString.equals("png")){
                titleArray.add(title);
                decriptionArray.add(description);
                nsfwBoolean.add(nsfw);
                sectionArray.add(section);
                linkArray.add(link);
                upsArray.add(ups);
                downsArray.add(downs);
                pointsArray.add(points);
                viewsArray.add(views);
                scoreArray.add(score);
            }



        }



    }



}
