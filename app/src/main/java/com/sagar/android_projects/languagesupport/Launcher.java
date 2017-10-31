package com.sagar.android_projects.languagesupport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

/**
 * created by SAGAR KUMAR NAYAK on 31 OCT 2017.
 * this is a project to show how to change language in android application.
 * we can do it in two ways.
 * 1. either for out own application only.
 * 2. or for the whole device.
 * this activity is the launcher and from this you can go to the both activity that shown the two
 * ways we have just discussed above.
 */
public class Launcher extends AppCompatActivity {

    //views
    @SuppressWarnings("FieldCanBeLocal")
    private Button buttonChangeLangInsideApp;
    @SuppressWarnings("FieldCanBeLocal")
    private Button buttonChangeLanguageInDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ////////////////////////////////////////////////////////////////////////////////////////////
        //view binding
        buttonChangeLangInsideApp = findViewById(R.id.button_language_change_inside_app);
        buttonChangeLanguageInDevice = findViewById(R.id.button_language_change_in_device);
        ////////////////////////////////////////////////////////////////////////////////////////////

        /*
        this is the on click for going to activity that will show the language change for our own application
        only.
         */
        buttonChangeLangInsideApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Launcher.this, LanguageChangeInApp.class));
            }
        });

        /*
        this is the on click for going to activity that will show the language change for the device
        itself.
         */
        buttonChangeLanguageInDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Launcher.this, LanguageChangeInDevice.class));
            }
        });
    }

}
