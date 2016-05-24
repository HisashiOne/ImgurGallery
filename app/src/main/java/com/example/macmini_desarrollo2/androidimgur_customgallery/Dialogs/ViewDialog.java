package com.example.macmini_desarrollo2.androidimgur_customgallery.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.macmini_desarrollo2.androidimgur_customgallery.R;

/**
 * Created by Oswaldo Morales on 16/05/16.
 *
 *
 */
public class ViewDialog {

    public void showDialog(Activity activity, String msg){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.about_alert);

        //Set Version APP
        TextView text = (TextView) dialog.findViewById(R.id.versionName);
        text.setText(msg);

        //Hide Dialog
        Button dialogButton = (Button) dialog.findViewById(R.id.dismissAbout);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}
