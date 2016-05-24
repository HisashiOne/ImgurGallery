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
 * Created by Oswaldo Morales H on 16/05/16.
 *
 *
 */
public class rowGridViewListAdapter   extends ArrayAdapter {

    private Context _context;
    ArrayList<String> _title;
    ArrayList <String> _description;
    ArrayList <String> _links;


    public  rowGridViewListAdapter (Context context, int resource, ArrayList <String> title, ArrayList <String> description,  ArrayList <String> links){

        super(context, resource, title);

        this._context = context;
        this._title = title;
        this._description = description;
        this._links = links;


    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(_context);
        view = inflater.inflate(R.layout.row_gridview, parent, false);

        //Asign Layout Variables
        TextView titleText = (TextView) view.findViewById(R.id.grid_tittle);
        TextView descipText = (TextView) view.findViewById(R.id.grid_Descript) ;
        ImageView gridView = (ImageView) view.findViewById(R.id.grid_Image);

        //Set Images with picasso library.
        //Set Placeholder Animation
        Picasso.with(_context).load(_links.get(position)).resize(400, 400).placeholder(R.drawable.progress_animation).into(gridView);
        ;


        //Set Description Text
        //if Description is null empty Textview
        String titleTXT = "   " + _title.get(position);
        titleText.setText(titleTXT);
        if (_description.get(position).equals("null")){
            descipText.setText(R.string.Description);
        }else {
            descipText.setText(_description.get(position));
        }

        return view;


    }
}
