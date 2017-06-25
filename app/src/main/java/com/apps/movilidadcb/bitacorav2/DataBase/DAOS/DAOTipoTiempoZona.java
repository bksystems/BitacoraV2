package com.apps.movilidadcb.bitacorav2.DataBase.DAOS;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.apps.movilidadcb.bitacorav2.DataBase.DatabaseHelper;
import com.apps.movilidadcb.bitacorav2.Entitys.RutaEntity;
import com.apps.movilidadcb.bitacorav2.Entitys.TipoTiempoZonaEntity;

import java.util.ArrayList;

/**
 * Created by jcarl on 06/11/2016.
 */

public class DAOTipoTiempoZona {
    DatabaseHelper dbHelper = null;
    public DAOTipoTiempoZona(Context context){
        dbHelper = new DatabaseHelper(context);
    }

    public ArrayList<TipoTiempoZonaEntity> getTipoTiempoZona() {
        ArrayList<TipoTiempoZonaEntity> tiempoZona = new ArrayList<>();
        try {
            String query = "select * from TipoTiempoZona";
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbHelper.getDbFullPath(), null, SQLiteDatabase.OPEN_READWRITE);
            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String tiempo = cursor.getString(1);
                String descripcion = cursor.getString(2);
                TipoTiempoZonaEntity tipoTiempoZonaEntity = new TipoTiempoZonaEntity(id, tiempo, descripcion);
                tiempoZona.add(tipoTiempoZonaEntity);
            }
        } catch (Exception ex) {
            Log.d("DB_TiempoZona", ex.getMessage());
        }
        return tiempoZona;
            }
}
