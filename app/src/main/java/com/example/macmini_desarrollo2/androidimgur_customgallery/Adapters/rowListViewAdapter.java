package com.example.macmini_desarrollo2.androidimgur_customgallery.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macmini_desarrollo2.androidimgur_customgallery.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Oswaldo Morales H on 11/05/16.
 *
 *
 *
 *
 */
public class rowListViewAdapter extends ArrayAdapter {

    private Context _context;
    ArrayList <String> _title;
    ArrayList <String> _description;
    ArrayList <Integer> _views;
    ArrayList <String> _links;

    public  rowListViewAdapter (Context context, int resource, ArrayList <String> title, ArrayList <String> description, ArrayList <Integer> views, ArrayList <String> links){
        super(context, resource, title);

        this._context = context;
        this._title = title;
        this._description = description;
        this._views = views;
        this._links = links;


    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        view = inflater.inflate(R.layout.row_listview, parent, false);


        //Load Layout
        TextView titleText = (TextView) view.findViewById(R.id.titleRow);
        TextView descripText = (TextView) view.findViewById(R.id.descriptText);
        TextView viewsText = (TextView) view.findViewById(R.id.viewsNmb);
        ImageView imageRow = (ImageView) view.findViewById(R.id.tablerow_Pic);

        //Set Images with Picasso Library
        //Set Animation Placeholder
        Picasso.with(_context).load(_links.get(position)).resize(100, 100).placeholder(R.drawable.progress_animation).into(imageRow);


        //Add Description
        // If Description is null empty Text View
        if (_description.get(position).equals("null")){
            descripText.setText(R.string.Description);
        }else {
            descripText.setText(_description.get(position));
        }

        titleText.setText(_title.get(position));
        viewsText.setText(String.valueOf(_views.get(position)));

        return view;
    }
}
