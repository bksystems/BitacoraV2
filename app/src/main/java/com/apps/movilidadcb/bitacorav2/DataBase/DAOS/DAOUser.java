package com.apps.movilidadcb.bitacorav2.DataBase.DAOS;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.apps.movilidadcb.bitacorav2.DataBase.DatabaseHelper;
import com.apps.movilidadcb.bitacorav2.Entitys.UsuarioEntity;

/**
 * Created by jcarl on 05/11/2016.
 */

public class DAOUser {
    DatabaseHelper dbHelper = null;

    public DAOUser(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public UsuarioEntity validateUser(String userLogin, String passwordLogin) {

        UsuarioEntity usuario = null;

            try {

                SQLiteDatabase db = SQLiteDatabase.openDatabase(dbHelper.getDbFullPath(), null, SQLiteDatabase.OPEN_READWRITE);

                String query = "SELECT * FROM USUARIOS WHERE KEY = " + userLogin + " AND Question = '" + passwordLogin + "'";

                if (db.isOpen()) {
                    Cursor cursor = db.rawQuery(query, null);
                    if (cursor != null) {
                        while (cursor.moveToNext()) {
                            usuario = new UsuarioEntity(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                        }
                    } else {

                    }
                }
            } catch (Exception ex) {
                Log.d("DB", ex.getMessage());
            }
            return usuario;

    }
}
