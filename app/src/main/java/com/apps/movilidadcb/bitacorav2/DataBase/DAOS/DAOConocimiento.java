package com.apps.movilidadcb.bitacorav2.DataBase.DAOS;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.apps.movilidadcb.bitacorav2.DataBase.DatabaseHelper;
import com.apps.movilidadcb.bitacorav2.Entitys.ConocimientoEntity;

import java.util.ArrayList;

/**
 * Created by jcarl on 06/11/2016.
 */

public class DAOConocimiento {

    DatabaseHelper dbHelper = null;

    public DAOConocimiento(Context context){
        dbHelper = new DatabaseHelper(context);
    }

    public ArrayList<ConocimientoEntity> getConocimientos() {
        ArrayList<ConocimientoEntity> conocimientos = new ArrayList<>();
        try {
            String query = "select * from TipoConocimiento";
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbHelper.getDbFullPath(), null, SQLiteDatabase.OPEN_READWRITE);
            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                int Id = Integer.parseInt(cursor.getString(0));
                String Tipo = cursor.getString(1);
                String Descripcion = cursor.getString(2);
                ConocimientoEntity conocimiento = new ConocimientoEntity(Id, Tipo, Descripcion, false);
                conocimientos.add(conocimiento);
            }
        } catch (Exception ex) {
            Log.d("DB_Conocimientos", ex.getMessage());
        }
        return conocimientos;
    }
}
