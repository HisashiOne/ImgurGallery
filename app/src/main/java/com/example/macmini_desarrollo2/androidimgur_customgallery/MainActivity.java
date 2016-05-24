package com.example.macmini_desarrollo2.androidimgur_customgallery;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.example.macmini_desarrollo2.androidimgur_customgallery.Adapters.rowGridViewListAdapter;
import com.example.macmini_desarrollo2.androidimgur_customgallery.Adapters.rowListViewAdapter;
import com.example.macmini_desarrollo2.androidimgur_customgallery.Dialogs.ViewDialog;
import com.example.macmini_desarrollo2.androidimgur_customgallery.Fragments.GridFragment;
import com.example.macmini_desarrollo2.androidimgur_customgallery.Fragments.ListFragment;
import com.example.macmini_desarrollo2.androidimgur_customgallery.Fragments.StagedFragment;
import com.example.macmini_desarrollo2.androidimgur_customgallery.Models.saveModel;

/**
 * Created by Oswaldo Morales on 09/05/16.
 *
 *
 */

public class MainActivity extends AppCompatActivity {


    Button hotBTN, topBTN, userBTN, about, sortBTN;
    saveModel save;
    LinearLayout mainLayout;

    rowGridViewListAdapter _grid;
    rowListViewAdapter _row;

    //Fragment Strings
    String  TAG_LIST = "mainList001";
    String  TAG_GRID = "mainGrid001";
    String TAG_STAGED = "mainStaged001";

    int selectView;
    String sortParameter;
    Boolean showViral;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Asign Fragmnets to Layout
        hotBTN = (Button) findViewById(R.id.hotBTN);
        topBTN = (Button) findViewById(R.id.topBTN);
        userBTN = (Button) findViewById(R.id.userBTN);
        about = (Button) findViewById(R.id.aboutUS);
        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        sortBTN = (Button) findViewById(R.id.sortDialog_1);

        selectView = 0; // GridView
        ReloadData(0);  // Reload Data On Create


        // Load Save Model
        save = new saveModel();

        SharedPreferences shared_1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final int saveDefaults =  shared_1.getInt("value", 0);


        //Compare model to the view
        switch (saveDefaults){
            case 0:
                hideBTNS(0);
                break;
            case 1:
                hideBTNS(1);
                break;
            case 2:
                hideBTNS(2);
                break;
        }

        hotBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save.savePreferences(getApplicationContext(), 0); //Reload Data at index 0
                hideBTNS(0);
                ReloadData(selectView);
            }
        });

        topBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save.savePreferences(getApplicationContext(), 1); //Reload Data at index 1
                hideBTNS(1);
                ReloadData(selectView);

            }
        });
        userBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save.savePreferences(getApplicationContext(), 2); //Reload Data at index 2
                hideBTNS(2);
                ReloadData(selectView);
            }
        });

        //Show Dialog with basic Info
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewDialog alert2 = new ViewDialog();
                try {
                    PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                    String version = packageInfo.versionName; // Load Version of the Manifest
                    alert2.showDialog(MainActivity.this, "Version:  " + version);

                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                    alert2.showDialog(MainActivity.this, "Version:  1.00");// Asign a Default Version
                }


            }
        });


        //Show Dialog with sort options
        sortBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.sort_alert);
                final Button viralButton = (Button) dialog.findViewById(R.id.sortViral);
                final Button topButton = (Button) dialog.findViewById(R.id.sortTop);
                final Button userButton = (Button) dialog.findViewById(R.id.sortUser);
                final Switch switchParameter = (Switch) dialog.findViewById(R.id.sortSwitch);
                sortParameter = "viral"; //Asign a Default Parameter
                showViral  = false; // Asign a Default Parameter


                switchParameter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        // Asign the switch to global bolean
                       showViral = isChecked;

                    }
                });

                //Change and asign the string to the button
                viralButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viralButton.setTextColor(Color.parseColor("#00d4ff"));
                        topButton.setTextColor(Color.BLACK);
                        userButton.setTextColor(Color.BLACK);
                        sortParameter = "viral";


                    }
                });
                topButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viralButton.setTextColor(Color.BLACK);
                        topButton.setTextColor(Color.parseColor("#00d4ff"));
                        userButton.setTextColor(Color.BLACK);
                        sortParameter = "top";

                    }
                });
                userButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viralButton.setTextColor(Color.BLACK);
                        topButton.setTextColor(Color.BLACK);
                        userButton.setTextColor(Color.parseColor("#00d4ff"));
                        sortParameter = "user";

                    }
                });


                Button aceptBtn = (Button) dialog.findViewById(R.id.aceptSort);
                aceptBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();

                        saveModel _saveModel = new saveModel();
                        _saveModel.sortParamenters(getApplicationContext(), sortParameter, showViral);


                        ReloadData(selectView);

                    }
                });
                Button dialogButton = (Button) dialog.findViewById(R.id.cancelSort);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Find Fragbments By Tag
        Fragment fragment2 = getSupportFragmentManager().findFragmentByTag(TAG_LIST);
        Fragment fragment4 = getSupportFragmentManager().findFragmentByTag(TAG_GRID);
        Fragment fragment6 = getSupportFragmentManager().findFragmentByTag(TAG_STAGED);
        int i = item.getItemId();
        if (i == R.id.list_view){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(fragment4);
            transaction.hide(fragment6);
            transaction.show(fragment2);
            transaction.commit();
            selectView = 1; //Reload View by number

        } else if (i == R.id.grid_view){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(fragment2);
            transaction.hide(fragment6);
            transaction.show(fragment4);
            transaction.commit();
            selectView = 0;  //Reload View by number


        }else if (i == R.id.grid_stage){

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(fragment2);
            transaction.hide(fragment4);
            transaction.show(fragment6);
            transaction.commit();
            selectView = 2;  //Reload View by number



        }


        return super.onOptionsItemSelected(item);

    }

    public  void  ReloadData (int _selected){



        //Load Grid Fragment
        Fragment fragment3 = new GridFragment();
        Fragment fragment4 = getSupportFragmentManager().findFragmentByTag(TAG_GRID);
        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
        if (!(fragment4 == null)){
            transaction2.remove(fragment4);
        }
        transaction2.add(mainLayout.getId(), fragment3, TAG_GRID);
        if (_selected == 0){ // Selected view
            transaction2.show(fragment3);
        }
        else {
            transaction2.hide(fragment3);
        }

        transaction2.commit();



        //Load List Fragment
        Fragment fragment1 = new ListFragment();
        Fragment fragment2 = getSupportFragmentManager().findFragmentByTag(TAG_LIST);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!(fragment2 == null)){
        transaction.remove(fragment2);
        }
        transaction.add(mainLayout.getId(), fragment1, TAG_LIST);
        if (_selected == 1){ // Selected view

            transaction.show(fragment1);
        }
        else {
            transaction.hide(fragment1);
        }
        transaction.commit();

        //Load Staged Grid Fragment
        Fragment fragment5 = new StagedFragment();
        Fragment fragment6 = getSupportFragmentManager().findFragmentByTag(TAG_STAGED);
        FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
        if (!(fragment6 == null)){
            transaction3.remove(fragment6);
        }
        transaction3.add(mainLayout.getId(), fragment5, TAG_STAGED);
        if (_selected == 2){

            transaction3.show(fragment5);
        }
        else {
            transaction3.hide(fragment5);
        }
        transaction3.commit();

    }


    //Hide Other Buttons in Navivigation Bar
    public  void  hideBTNS (int BTN_A){

        final Drawable back_BTN = ResourcesCompat.getDrawable(getResources(), R.drawable.nav_btn, null);
        final Drawable back_BTN2 = ResourcesCompat.getDrawable(getResources(), R.drawable.nav_btn2, null);


        if (BTN_A == 0){
            hotBTN.setBackground(back_BTN);
            topBTN.setBackground(back_BTN2);
            userBTN.setBackground(back_BTN2);
        }
        else if (BTN_A == 1){
            topBTN.setBackground(back_BTN);
            userBTN.setBackground(back_BTN2);
            hotBTN.setBackground(back_BTN2);


        }else  if (BTN_A == 2){
            topBTN.setBackground(back_BTN2);
            userBTN.setBackground(back_BTN);
            hotBTN.setBackground(back_BTN2);
        }
    }



}
