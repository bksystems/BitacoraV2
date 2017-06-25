package com.apps.movilidadcb.bitacorav2.DataBase.DAOS;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.apps.movilidadcb.bitacorav2.DataBase.DatabaseHelper;
import com.apps.movilidadcb.bitacorav2.Entitys.OficinaEntity;

import java.util.ArrayList;

/**
 * Created by jcarl on 05/11/2016.
 */

public class DAOOficina {

    DatabaseHelper dbHelper = null;

    public DAOOficina(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public ArrayList<OficinaEntity> getAllOficinas() {
        ArrayList<OficinaEntity> oficinas = new ArrayList<>();
        try {
            String query = "SELECT * FROM Oficinas";
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbHelper.getDbFullPath(), null, SQLiteDatabase.OPEN_READWRITE);
            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                int Id = Integer.parseInt(cursor.getString(0));
                int IdOS = Integer.parseInt(cursor.getString(1));
                int CC = Integer.parseInt(cursor.getString(2));
                String CC_ERP = cursor.getString(3);
                String Nombre = cursor.getString(4);
                String Region = cursor.getString(5);
                String Subdireccion = cursor.getString(6);
                String Direccion = cursor.getString(7);

                OficinaEntity ofi = new OficinaEntity(Id, IdOS, CC, CC_ERP, Nombre, Region, Subdireccion, Direccion);

                oficinas.add(ofi);
            }
        } catch (Exception ex) {
            Log.d("DB_Oficina", ex.getMessage());
        }
        return oficinas;
    }

    public ArrayList<OficinaEntity> getOficinasByUserNomina(String nomina) {
        ArrayList<OficinaEntity> oficinas = new ArrayList<>();
        try {
            String query = "select *  from Oficinas where CC_ERP in (select CC_ERP from OficinasColaborador where Nomina = " + nomina + ")";
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbHelper.getDbFullPath(), null, SQLiteDatabase.OPEN_READWRITE);
            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                int Id = Integer.parseInt(cursor.getString(0));
                int IdOS = Integer.parseInt(cursor.getString(1));
                int CC = Integer.parseInt(cursor.getString(2));
                String CC_ERP = cursor.getString(3);
                String Nombre = cursor.getString(4);
                String Region = cursor.getString(5);
                String Subdireccion = cursor.getString(6);
                String Direccion = cursor.getString(7);

                OficinaEntity ofi = new OficinaEntity(Id, IdOS, CC, CC_ERP, Nombre, Region, Subdireccion, Direccion);

                oficinas.add(ofi);
            }
        } catch (Exception ex) {
            Log.d("DB_Oficina", ex.getMessage());
        }
        return oficinas;
    }
}
