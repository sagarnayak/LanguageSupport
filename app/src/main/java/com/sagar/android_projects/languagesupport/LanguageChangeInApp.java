package com.sagar.android_projects.languagesupport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Locale;

/**
 * created by SAGAR KUMAR NAYAK on 31 OCT 2017.
 * this is the activity to show the language change for our own app only. this will not change the
 * language of the device. lets look at the steps for doing it.
 * 1. get the preferred language code to change.
 * 2. set the new locale to the configuration.
 * 3. update the ui.
 * 4. also keep the preferred lang in your application preferences. as android will not remember this
 *    the next time you some to this activity.
 *    also you have to do this to every activity you start. save the preferred language and update the
 *    ui at every activity you start according to that.
 */
public class LanguageChangeInApp extends AppCompatActivity {

    @SuppressWarnings("FieldCanBeLocal")
    private RadioButton radioButtonEnglish;
    @SuppressWarnings("FieldCanBeLocal")
    private RadioButton radioButtonHindi;
    private TextView textViewInstruction;

    @SuppressWarnings("FieldCanBeLocal")
    private Locale myLocale;

    /*
    enum to have the supported language by this app with the language code.
     */
    private enum Languages {
        ENGLISH("en"),
        HINDI("hi");

        private String langiageCode;

        Languages(String langiageCode) {
            this.langiageCode = langiageCode;
        }

        public String getLanguageCode() {
            return langiageCode;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_change_in_app);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ////////////////////////////////////////////////////////////////////////////////////////////
        //view binding
        radioButtonEnglish = findViewById(R.id.radio_button_english);
        radioButtonHindi = findViewById(R.id.radio_button_hindi);
        textViewInstruction = findViewById(R.id.textview_instruction);
        ////////////////////////////////////////////////////////////////////////////////////////////

        /*
        radio button to set the preferred language as english.
         */
        radioButtonEnglish.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked)
                    return;
                changeLang(Languages.ENGLISH.getLanguageCode());
            }
        });

        /*
        radio button to set the preferred language as hindi.
         */
        radioButtonHindi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked)
                    return;
                changeLang(Languages.HINDI.getLanguageCode());
            }
        });

        /*
        when the activity start check the preferred language. if the shared preference has any of them
        then update the UI with that language.
         */
        checkForLangPref();
    }

    /**
     * method to change the language. this will create a new locale with the selected language and
     * set that to the configuration.
     * also this will fire the method to save the selected language for future user.
     * @param lang language code
     */
    public void changeLang(String lang) {
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);
        saveLocale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        updateTexts();
    }

    /**
     * method to save the selected language code for future use.
     * @param lang language code
     */
    public void saveLocale(String lang) {
        String langPref = "Language";
        getSharedPreferences("Database", MODE_PRIVATE).edit().putString(langPref, lang).apply();
    }

    /**
     * method to update the UI according to the selected language. as the configuration has the new
     * language locale. it will automatically get the required strings from the strings.xml that is
     * defined for the specific language.
     */
    private void updateTexts() {
        textViewInstruction.setText(getResources().getString(R.string.enter_your_name));
    }

    /**
     * method to check if shared preference has any previously selected language. if yes then first
     * update the ui with the language.
     */
    private void checkForLangPref() {
        try {
            String langPref = getSharedPreferences("Database", MODE_PRIVATE).getString("Language", "");
            if (langPref.length() == 0)
                return;
            if (langPref.equals(Languages.ENGLISH.getLanguageCode())) {
                radioButtonEnglish.setChecked(true);
                changeLang(Languages.ENGLISH.getLanguageCode());
            } else if (langPref.equals(Languages.HINDI.getLanguageCode())) {
                radioButtonHindi.setChecked(true);
                changeLang(Languages.HINDI.getLanguageCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
