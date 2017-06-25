package com.apps.movilidadcb.bitacorav2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.movilidadcb.bitacorav2.Controls.FileChooser;
import com.apps.movilidadcb.bitacorav2.DataBase.DatabaseHelper;

import java.io.File;
import java.io.IOException;

public class BaseDeDatosActivity extends AppCompatActivity  {

    private static final int READ_REQUEST_CODE = 42;

    private Activity activity;

    private TextView txtFechaVersion;
    private TextView txtVersion;
    private TextView txtDescripcion;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_de_datos);

        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());

        txtVersion = (TextView) findViewById(R.id.load_db_textView_version);
        txtDescripcion = (TextView) findViewById(R.id.load_db_textView_descripcion);
        txtFechaVersion = (TextView) findViewById(R.id.load_db_textView_fecha);

        Toolbar settingsToolBar = (Toolbar) findViewById(R.id.toolbar_settings_base_de_datos);
        setSupportActionBar(settingsToolBar);

        getSupportActionBar().setTitle("Base de datos");
        getSupportActionBar().setSubtitle("Detalle de la base de datos");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        settingsToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        try {
            if (dbHelper.IsCheckDataBaseExist()) {
                LoadDetails();
            } else {
                Toast.makeText(getApplicationContext(), "La aplicación no cuenta con base de datos", Toast.LENGTH_SHORT);
                txtDescripcion.setText("No existe base de datos");
                txtFechaVersion.setText("00-00-00");
                txtVersion.setText("0");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }

    private void LoadDetails() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings_options_database, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       /*if(requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK){
           Uri uri = null;
           if(data != null){
               uri = data.getData();
               Log.i("File", "Uri: " + uri.toString());
           }
           File f = new File(uri.get());

           if(f.equals("")){

           }
       }*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_settings_option_database_load:

                FileChooser fileChoser = new FileChooser(activity).setFileListener(new FileChooser.FileSelectedListener() {
                    @Override
                    public void fileSelected(final File file) throws IOException {
                        String fileName = file.getName();
                        if (fileName.equals("BitacoraPL.s3db")) {
                            AlertDialog.Builder alertConfirmDelete = new AlertDialog.Builder(activity);
                            alertConfirmDelete.setTitle("Remplazar la base de datos");

                            alertConfirmDelete.setMessage("¿Esta seguro de rempleazar la base de datos? Se perdera la informacion que no se sincronizo")
                                    .setCancelable(false)
                                    .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            DatabaseHelper db = new DatabaseHelper(activity);
                                            try {
                                                db.ReplaceDataBase(file);
                                                //EntityDataBaseVersion version = db.getVersionDB();
                                                //Toast.makeText(thisActivity, "Se remplazo la base de datos por la version " + version.getVersion(), Toast.LENGTH_SHORT).show();
                                                LoadDetails();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                            dialog.cancel();
                                        }
                                    })
                                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {

                                            dialog.cancel();
                                        }
                                    });
                            AlertDialog alertDialog = alertConfirmDelete.create();

                            alertDialog.show();
                        } else {
                            Toast.makeText(activity, "Archivo no compatible!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).showDialog();
                /*Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("");
                startActivityForResult(intent, READ_REQUEST_CODE);*/
                break;
            case R.id.toolbar_settings_option_database_delete:
                AlertDialog.Builder alertConfirmDelete = new AlertDialog.Builder(activity);
                alertConfirmDelete.setTitle("Desea eliminar a base de datos");

                alertConfirmDelete.setMessage("Esta a punto de eliminar la base de datos, se perdera toda la información que no se haya sincronizado ¿Desea continuar?")
                        .setCancelable(false)
                        .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertConfirmDelete.create();

                alertDialog.show();
                break;
            case R.id.toolbar_settings_option_database_extract:
                final Dialog signinDialog = new Dialog(this);
                signinDialog.setContentView(R.layout.dialog_signin);
                signinDialog.setTitle("Only Admin");
                Button btnLogin = (Button) signinDialog.findViewById(R.id.signInbtnLogin);
                Button btnCancel = (Button) signinDialog.findViewById(R.id.signInbtnCancel);
                final EditText txtUsername = (EditText)signinDialog.findViewById(R.id.signIntxtUsername);
                final EditText txtPassword = (EditText)signinDialog.findViewById(R.id.signIntxtPassword);

                btnLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(txtUsername.getText().toString().trim().length() > 0 && txtPassword.getText().toString().trim().length() > 0)
                        {
                            DatabaseHelper dbHelper = new DatabaseHelper(activity);
                            if(dbHelper.backupDatabase()){
                                Toast.makeText(activity, "Se ejecto un resplado de la base de datos", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(activity, "Error al extraer la base de datos", Toast.LENGTH_SHORT).show();
                            }
                            signinDialog.dismiss();
                        }
                        else
                        {
                            Toast.makeText(activity,
                                    "Please enter Username and Password", Toast.LENGTH_LONG).show();

                        }
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        signinDialog.dismiss();
                    }
                });


                signinDialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}
