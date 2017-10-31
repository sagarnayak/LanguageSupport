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
 * this is a
 */
public class LanguageChangeInDevice extends AppCompatActivity {

    private Button buttonChangeLanguage;
    private TextView textViewInstruction;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_change_in_device);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonChangeLanguage = findViewById(R.id.button_change_language);
        textViewInstruction = findViewById(R.id.textview_instruction);
        editText = findViewById(R.id.edittext);

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

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        startActivity(new Intent(this, LanguageChangeInDevice.class));
        finish();
    }
}
