package com.apps.movilidadcb.bitacorav2.DataBase.DAOS;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.apps.movilidadcb.bitacorav2.DataBase.DatabaseHelper;
import com.apps.movilidadcb.bitacorav2.Entitys.SincronizacionEntity;

import java.util.ArrayList;

/**
 * Created by jcarl on 13/11/2016.
 */

public class DAOSincronizacion {

    DatabaseHelper dbHelper = null;

    public DAOSincronizacion(Context context){
        dbHelper = new DatabaseHelper(context);
    }




    public ArrayList<String> getTiposUltimaSincronizacion() {
        ArrayList<String> listPresentaIncidencia = new ArrayList<>();
        try {
            String query = "select * from TipoUltimaSincronizacion";
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbHelper.getDbFullPath(), null, SQLiteDatabase.OPEN_READWRITE);

            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                String incidencia = cursor.getString(1);
                listPresentaIncidencia.add(incidencia);
            }
        } catch (Exception ex) {
            Log.d("DB_PlGeneral", ex.getMessage());
        }
        return listPresentaIncidencia;
    }


    public ArrayList<String> getTiposCausaNoSincronziacion() {
        ArrayList<String> listMotivoNoUtiliza = new ArrayList<>();
        try {
            String query = "select * from TipoCausaNoSincronizacion";
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbHelper.getDbFullPath(), null, SQLiteDatabase.OPEN_READWRITE);

            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                String incidencia = cursor.getString(1);
                listMotivoNoUtiliza.add(incidencia);
            }
        } catch (Exception ex) {
            Log.d("DB_PlGeneral", ex.getMessage());
        }
        return listMotivoNoUtiliza;
    }

    public ArrayList<SincronizacionEntity> getElementosSincronizacion(int idVisitaGeneral) {
        ArrayList<SincronizacionEntity> sincronizacionesList = new ArrayList<>();
        try {
            String query = "select * from Sincronizacion where IdVisita = " + idVisitaGeneral;
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbHelper.getDbFullPath(), null, SQLiteDatabase.OPEN_READWRITE);
            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                String OficinaVisitada = cursor.getString(0);
                String FechaVisita = cursor.getString(1);
                int NominaColaboradorPl = cursor.getInt(2);
                String EtiquetaDM = cursor.getString(3);
                int NominaColaborador = cursor.getInt(4);
                String TiempoUltimaSincronizacion = cursor.getString(5);
                String CausaNoSincronziacion = cursor.getString(6);
                int id = cursor.getInt(7);
                int idVisita = cursor.getInt(8);
                SincronizacionEntity sincronizacion = new SincronizacionEntity(id, idVisita, OficinaVisitada, FechaVisita, NominaColaboradorPl, EtiquetaDM, NominaColaborador, TiempoUltimaSincronizacion, CausaNoSincronziacion);
                sincronizacionesList.add(sincronizacion);
            }
        } catch (Exception ex) {
            Log.d("DB_Rutas", ex.getMessage());
        }
        return sincronizacionesList;
    }

    public boolean Insert(SincronizacionEntity sincronizacion) {
        boolean result = false;
        try {
            ContentValues values = new ContentValues();
            sincronizacion.setId(dbHelper.getNextID("Sincronizacion") + 1);
            values.put("Id", sincronizacion.getId());
            values.put("IdVisita", sincronizacion.getIdVisita());
            values.put("OficinaVisitada", sincronizacion.getOficinaVisitada());
            values.put("FechaVisita", sincronizacion.getFechaVisita().toString());
            values.put("NominaColaboradorPL", sincronizacion.getNominaColaboradorPL());
            values.put("EtiquetaDM", sincronizacion.getEtiquetaDM());
            values.put("NominaColaborador", sincronizacion.getNominaColaboradorVisita());
            values.put("TiempoUltSincronizacion", sincronizacion.getTiempoUltSincronizacion());
            values.put("CausaNoSincronizacion", sincronizacion.getCausaNoSincronizacion());

            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbHelper.getDbFullPath(), null, SQLiteDatabase.OPEN_READWRITE);
            if (db.insert("Sincronizacion", null, values) > 0) {
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
}
