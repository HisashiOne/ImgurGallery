package com.example.macmini_desarrollo2.androidimgur_customgallery.Adapters;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.etsy.android.grid.util.DynamicHeightImageView;
import com.example.macmini_desarrollo2.androidimgur_customgallery.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Oswaldo Morales H on 16/05/16.
 *
 *
 *
 */
public class rowStagedAdapter  extends ArrayAdapter <String> {


    ArrayList<String> _title;
    private final LayoutInflater mLayoutInflater;
    private final Random mRandom;
    private static final SparseArray<Double> sPositionHeightRatios = new SparseArray<>();
    private  Context _contex;

    public  rowStagedAdapter(Context context, int textViewResourceId,
                             ArrayList<String> objects, ArrayList <String> title) {
        super(context, textViewResourceId, objects);
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mRandom = new Random();
        this._contex = context;
        this._title = title;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.row_staged,
                    parent, false);
            vh = new ViewHolder();
            vh.imgView = (DynamicHeightImageView) convertView
                    .findViewById(R.id.imgView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        double positionHeight = getPositionRatio(position);
        vh.imgView.setHeightRatio(positionHeight);


        TextView tittle = (TextView) convertView.findViewById(R.id.stage_title);
        //Set image with Picaso Library
        //Set Animation Placeholder

        Picasso.with(_contex).load(getItem(position)).placeholder(R.drawable.progress_animation).into(vh.imgView);
        String text = "   " + _title.get(position);
        tittle.setText(text);

        return convertView;


    }
    static class ViewHolder {
        DynamicHeightImageView imgView;
    }

    private double getPositionRatio(final int position) {
        double ratio = sPositionHeightRatios.get(position, 0.0);
// if not yet done generate and stash the columns height
// in our real world scenario this will be determined by
// some match based on the known height and width of the image
// and maybe a helpful way to get the column height!
        if (ratio == 0) {
            ratio = getRandomHeightRatio();
            sPositionHeightRatios.append(position, ratio);
            //Log.d(TAG, "getPositionRatio:" + position + " ratio:" + ratio);
        }
        return ratio;
    }
    private double getRandomHeightRatio() {
        return (mRandom.nextDouble() / 2.0) + 1.0; // height will be 1.0 - 1.5
// the width
    }


}
