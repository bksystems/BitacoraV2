package com.apps.movilidadcb.bitacorav2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class AcercaDeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);

        Toolbar settingsToolBarAcercaDe = (Toolbar) findViewById(R.id.toolbar_settings_acerca_de);
        setSupportActionBar(settingsToolBarAcercaDe);

        getSupportActionBar().setTitle("Acerca de:");
        getSupportActionBar().setSubtitle("Detalle de la aplicación");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        settingsToolBarAcercaDe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
