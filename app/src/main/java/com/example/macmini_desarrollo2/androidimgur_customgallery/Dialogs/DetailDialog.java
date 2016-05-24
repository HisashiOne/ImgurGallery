package com.example.macmini_desarrollo2.androidimgur_customgallery.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macmini_desarrollo2.androidimgur_customgallery.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Oswaldo Morales H on 16/05/16.
 *
 *
 */
public class DetailDialog {

    public void showDialog(Activity activity, String link, String title, String descript, String views, String score, String upVotes, String downVotes){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.detail_alert);

        //Set Dialog Layout
        ImageView detailImage = (ImageView) dialog.findViewById(R.id.imageDetail);
        TextView titleText = (TextView) dialog.findViewById(R.id.titleDialog);
        TextView descripText = (TextView) dialog.findViewById(R.id.descripDialg);
        TextView viewsDialog = (TextView) dialog.findViewById(R.id.viewsDialog);
        TextView scoreDialog = (TextView) dialog.findViewById(R.id.scoreDialog);
        TextView upDialog = (TextView) dialog.findViewById(R.id.upVotesDialog);
        TextView downDialog = (TextView) dialog.findViewById(R.id.downVotesDialog);

        //Asign Image URL with Picaso Library and Set Placeholder
        Picasso.with(activity).load(link).placeholder(R.drawable.progress_animation).into(detailImage);
        //Asign Variables to the Layaout Alert
        titleText.setText(title);
        descripText.setText(descript);
        viewsDialog.setText(views);
        scoreDialog.setText(score);
        upDialog.setText(upVotes);
        downDialog.setText(downVotes);

        //Hide Description if is Null
        if (descript.equals("null")){
            descripText.setText("");
        }
        else {
            descripText.setText(descript);
        }

        Button dialogButton = (Button) dialog.findViewById(R.id.dismissAlert);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }


}
