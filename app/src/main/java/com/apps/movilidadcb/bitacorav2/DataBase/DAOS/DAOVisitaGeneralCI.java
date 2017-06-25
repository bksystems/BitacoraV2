package com.apps.movilidadcb.bitacorav2.DataBase.DAOS;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.apps.movilidadcb.bitacorav2.DataBase.DatabaseHelper;
import com.apps.movilidadcb.bitacorav2.Entitys.VisitaGeneralCIEntity;

import java.util.ArrayList;

/**
 * Created by jcarl on 13/11/2016.
 */

public class DAOVisitaGeneralCI {

    DatabaseHelper dbHelper = null;

    public DAOVisitaGeneralCI(Context context) {
        dbHelper = new DatabaseHelper(context);
    }


    public boolean Insert(VisitaGeneralCIEntity visitaCI) {
        boolean result = false;
        try {
            ContentValues values = new ContentValues();
            visitaCI.setId(dbHelper.getNextID("PLGeneralCI") + 1);
            values.put("Id", visitaCI.getId());
            values.put("IdVisita", visitaCI.getIdVisita());
            values.put("CC_ERP", visitaCI.getCC_ERP());
            values.put("Oficina", visitaCI.getOficina());
            values.put("RutaId", visitaCI.getRutaId());
            values.put("NominaColaborador", visitaCI.getNominaColaborador());
            values.put("NombreColaborador", visitaCI.getNombreColaborador());
            values.put("UtilizaFormatos", visitaCI.getUtilizaFormatos());
            values.put("NominaColaboradorPL", visitaCI.getNominaColaboradorPL());
            values.put("NombreColaboradorPL", visitaCI.getNombreColaboradorPL());
            values.put("FechaVisita", visitaCI.getFechaVisita().toString());
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbHelper.getDbFullPath(), null, SQLiteDatabase.OPEN_READWRITE);
            if (db.insert("PLGeneralCI", null, values) > 0) {
                db.close();
                result = true;
            } else {
                db.close();
                result = false;
            }
        }catch (Exception ex){
            result = false;
        }
        return result;
    }

    public ArrayList<VisitaGeneralCIEntity> getVisitasGeneralCI(int id) {
        ArrayList<VisitaGeneralCIEntity> generalci = new ArrayList<>();
        try {
            String query = "select * from PLGeneralCI where IdVisita = " + id;
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbHelper.getDbFullPath(), null, SQLiteDatabase.OPEN_READWRITE);

            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                int Id = cursor.getInt(0);
                int IdVisita = cursor.getInt(1);
                int cc_erp = cursor.getInt(2);
                String oficina= cursor.getString(3);
                String rutaVisitada = cursor.getString(4);
                int os_col_nomina = cursor.getInt(5);
                String os_col_nombre = cursor.getString(6);
                String utilizaFormato = cursor.getString(7);
                int nomina_pl = cursor.getInt(8);
                String nombre_pl = cursor.getString(9);
                String fechaIngresada = cursor.getString(10);


                VisitaGeneralCIEntity plgeneralCI = new VisitaGeneralCIEntity(Id,IdVisita,cc_erp,oficina, rutaVisitada, os_col_nomina, os_col_nombre, utilizaFormato, nomina_pl, nombre_pl, fechaIngresada);
                generalci.add(plgeneralCI);

            }
        } catch (Exception ex) {
            Log.d("DB_PlGeneral", ex.getMessage());
        }
        return generalci;
    }

    public boolean DeleteById(int id) {
        boolean result;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbHelper.getDbFullPath(), null, SQLiteDatabase.OPEN_READWRITE);
        if(db.delete("PLGeneralCI", "Id = " + String.valueOf(id), null) > 0){
            result = true;
        }else{
            result = false;
        }
        return result;
    }

    public boolean DeleteByGeneralId(int id){
        boolean result;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbHelper.getDbFullPath(), null, SQLiteDatabase.OPEN_READWRITE);
        if(db.delete("PLGeneralCI", "IdVisita = " + String.valueOf(id), null) > 0){
            result = true;
        }else{
            result = false;
        }
        return result;

    }
}
