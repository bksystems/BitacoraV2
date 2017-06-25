package com.apps.movilidadcb.bitacorav2.DataBase.DAOS;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.apps.movilidadcb.bitacorav2.DataBase.DatabaseHelper;
import com.apps.movilidadcb.bitacorav2.Entitys.RutaEntity;

import java.util.ArrayList;

/**
 * Created by jcarl on 05/11/2016.
 */

public class DAORuta {

    DatabaseHelper dbHelper = null;

    public DAORuta(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public ArrayList<RutaEntity> getRutaByCCErp(int cc_erp_os) {

        ArrayList<RutaEntity> rutas = new ArrayList<>();
        try {
            String query = "select * from LimitesRutas where CC_ERP = " + cc_erp_os;
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbHelper.getDbFullPath(), null, SQLiteDatabase.OPEN_READWRITE);
            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                String CC_ERP = cursor.getString(0);
                String OS = cursor.getString(1);
                String Nombre = cursor.getString(2);
                String Nomina = cursor.getString(3);
                String Ruta = cursor.getString(4);
                String CC = cursor.getString(5);
                RutaEntity ruta = new RutaEntity(CC_ERP, OS, Nombre, Nomina, Ruta, CC);
                rutas.add(ruta);
            }
        } catch (Exception ex) {
            Log.d("DB_Rutas", ex.getMessage());
        }
        return rutas;
    }

    public ArrayList<RutaEntity> getRutaByCCErpOnlyCI(int cc_erp) {
        ArrayList<RutaEntity> rutas = new ArrayList<>();
        try {
            String query = "select * from LimitesRutas where Id like '%CI%' and CC_ERP = " + cc_erp;
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbHelper.getDbFullPath(), null, SQLiteDatabase.OPEN_READWRITE);
            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                String CC_ERP = cursor.getString(0);
                String OS = cursor.getString(1);
                String Nombre = cursor.getString(2);
                String Nomina = cursor.getString(3);
                String Ruta = cursor.getString(4);
                String CC = cursor.getString(5);
                RutaEntity ruta = new RutaEntity(CC_ERP, OS, Nombre, Nomina, Ruta, CC);
                rutas.add(ruta);
            }
        } catch (Exception ex) {
            Log.d("DB_Rutas", ex.getMessage());
        }
        return rutas;
    }
}
