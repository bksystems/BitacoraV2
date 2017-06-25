package com.apps.movilidadcb.bitacorav2.DataBase.DAOS;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.apps.movilidadcb.bitacorav2.DataBase.DatabaseHelper;
import com.apps.movilidadcb.bitacorav2.Entitys.HerramientaEntity;

import java.util.ArrayList;

/**
 * Created by jcarl on 06/11/2016.
 */

public class DAOHerramienta {
    DatabaseHelper dbHelper = null;
    public DAOHerramienta(Context context){
        dbHelper = new DatabaseHelper(context);
    }

    public ArrayList<HerramientaEntity> getHerramientas() {
        ArrayList<HerramientaEntity> herramientas = new ArrayList<>();
        try {
            String query = "select * from TipoHerramientasZonaTrabajo";
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbHelper.getDbFullPath(), null, SQLiteDatabase.OPEN_READWRITE);
            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                int Id = Integer.parseInt(cursor.getString(0));
                String Tipo = cursor.getString(1);
                String Descripcion = cursor.getString(2);
                HerramientaEntity herramienta = new HerramientaEntity(Id, Tipo, Descripcion);
                herramientas.add(herramienta);
            }
        } catch (Exception ex) {
            Log.d("DB_Herramientas", ex.getMessage());
        }
        return herramientas;
    }
}
