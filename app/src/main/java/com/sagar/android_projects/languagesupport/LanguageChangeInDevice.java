package com.sagar.android_projects.languagesupport;

import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * created by SAGAR KUMAR NAYAK on 31 OCT 2017.
 * this is an activity to show language selection when we want to change the language of the entire
 * device.
 * for this purpose we have to start the intent to start the settings activity.
 */
public class LanguageChangeInDevice extends AppCompatActivity {

    @SuppressWarnings("FieldCanBeLocal")
    private Button buttonChangeLanguage;
    @SuppressWarnings("FieldCanBeLocal,unused")
    private TextView textViewInstruction;
    @SuppressWarnings("FieldCanBeLocal,unused")
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_change_in_device);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ////////////////////////////////////////////////////////////////////////////////////////////
        //view binding
        buttonChangeLanguage = findViewById(R.id.button_change_language);
        textViewInstruction = findViewById(R.id.textview_instruction);
        editText = findViewById(R.id.edittext);
        ////////////////////////////////////////////////////////////////////////////////////////////

        /*
        on click for button to start settings activity to change the device default language.
         */
        buttonChangeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.android.settings",
                        "com.android.settings.Settings$InputMethodAndLanguageSettingsActivity"));
                startActivity(intent);
            }
        });
    }

    /**
     * method to be fired when there is changes in configuration. if the configuration is changed with
     * the language this method will be fired.
     * here we have to start the activity again so that the changes may take place.
     * @param newConfig new configuration
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        startActivity(new Intent(this, LanguageChangeInDevice.class));
        finish();
    }
}
