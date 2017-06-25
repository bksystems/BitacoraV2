package com.apps.movilidadcb.bitacorav2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apps.movilidadcb.bitacorav2.DataBase.DAOS.DAOOficinaColaborador;
import com.apps.movilidadcb.bitacorav2.DataBase.DAOS.DAOUser;
import com.apps.movilidadcb.bitacorav2.DataBase.DatabaseHelper;
import com.apps.movilidadcb.bitacorav2.Entitys.UserGlobalClass;
import com.apps.movilidadcb.bitacorav2.Entitys.UsuarioEntity;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper dbHelper = null;
    private EditText txtUserLogin;
    private EditText txtPasswordLogin;
    private Button btnLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DatabaseHelper(getApplicationContext());

        Toolbar settingsToolbarLogin = (Toolbar) findViewById(R.id.toolbar_settings_login);
        setSupportActionBar(settingsToolbarLogin);

        getSupportActionBar().setTitle("Bitacora Móvil PL y M");
        getSupportActionBar().setSubtitle("Bitacora de acompañamiento");

        txtUserLogin = (EditText) findViewById(R.id.login_txt_user);
        txtPasswordLogin = (EditText) findViewById(R.id.login_txt_password);
        btnLogin = (Button) findViewById(R.id.login_btn_acces);



        if(savedInstanceState != null){
            if(!savedInstanceState.getBoolean("DB")){
                btnLogin.setVisibility(View.INVISIBLE);
            }else{
                btnLogin.setVisibility(View.VISIBLE);
            }
        }else{
            ValidateDataBase();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ValidateDataBase()) {
                    String userLogin = txtUserLogin.getText().toString();
                    String passwordLogin = txtPasswordLogin.getText().toString();
                    if (!userLogin.isEmpty() || !passwordLogin.isEmpty()) {
                        DAOUser daoUser = new DAOUser(getApplicationContext());
                        UsuarioEntity usuarioEntity = daoUser.validateUser(userLogin, passwordLogin);
                        final UserGlobalClass  userGlobalClass = (UserGlobalClass) getApplicationContext();
                        if(usuarioEntity != null) {


                            userGlobalClass.setUserEntity(usuarioEntity);
                            userGlobalClass.setIsLoggin(true);

                            DAOOficinaColaborador daoOficinaColaborador = new DAOOficinaColaborador(getApplicationContext());
                            if(daoOficinaColaborador.getAsignatedOficinas(usuarioEntity.getKey())) {
                                Intent mainActivity = new Intent(LoginActivity.this, MainActivityNavigationDrawer.class);
                                startActivity(mainActivity);
                            }else{
                                userGlobalClass.setUserEntity(null);
                                userGlobalClass.setIsLoggin(false);
                                Toast.makeText(getApplicationContext(), "Usuario sin oficinas asignadas", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }else{
                            userGlobalClass.setUserEntity(null);
                            userGlobalClass.setIsLoggin(false);
                            Toast.makeText(getApplicationContext(), "Usuario y/o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Usuario y/o contraseña obligatorio", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private boolean ValidateDataBase() {
        boolean validateExist = false;
        try {
            if(dbHelper.IsCheckDataBaseExist()){
                validateExist = true;
                btnLogin.setVisibility(View.VISIBLE);
            }else{
                btnLogin.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(),"La aplicación no cuenta con base de datos", Toast.LENGTH_SHORT).show();
                            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return validateExist;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("DB", ValidateDataBase());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings_login, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.login_settings_version:
                Intent settings_login = new Intent(LoginActivity.this, VersionActivity.class);
                startActivity(settings_login);
                break;
            case R.id.login_settings_base_de_datos:
                Intent settings_base_de_datos = new Intent(LoginActivity.this, BaseDeDatosActivity.class);
                startActivity(settings_base_de_datos);
                break;
            case R.id.login_settings_acerca_de:
                Intent settings_acerca_de = new Intent(LoginActivity.this, AcercaDeActivity.class);
                startActivity(settings_acerca_de);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
