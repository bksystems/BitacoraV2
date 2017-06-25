package com.apps.movilidadcb.bitacorav2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class VersionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);

        Toolbar settingsToolBarVersion = (Toolbar) findViewById(R.id.toolbar_settings_version);
        setSupportActionBar(settingsToolBarVersion);

        getSupportActionBar().setTitle("Versión App");
        getSupportActionBar().setSubtitle("Detalle de versión de aplicación");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        settingsToolBarVersion.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
