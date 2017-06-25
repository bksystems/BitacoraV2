package com.apps.movilidadcb.bitacorav2.DataBase.DAOS;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.apps.movilidadcb.bitacorav2.DataBase.DatabaseHelper;
import com.apps.movilidadcb.bitacorav2.Entitys.SimuladorEntity;

import java.util.ArrayList;

/**
 * Created by jurizo on 15/11/16.
 */

public class DAOSimulador {

    private final DatabaseHelper dbHelper;

    public DAOSimulador(Context context){
        dbHelper = new DatabaseHelper(context);
    }

     public ArrayList<String> getPresentaIncidecia() {
        ArrayList<String> listPresentaIncidencia = new ArrayList<>();
        try {
            String query = "select * from TipoSimuladorPresentaInsidencia";
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

    public ArrayList<String> getMotivoNoUtiliza() {
        ArrayList<String> listMotivoNoUtiliza = new ArrayList<>();
        try {
            String query = "select * from TipoMotivoNoUsaSimulador";
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

    public ArrayList<SimuladorEntity> getAgregadosByIdGeneral(int idVisitaGeneral) {
        ArrayList<SimuladorEntity> simuladorList = new ArrayList<>();
        try {
            String query = "select * from Simulador where IdVisita = " + idVisitaGeneral;
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbHelper.getDbFullPath(), null, SQLiteDatabase.OPEN_READWRITE);
            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {


               String OficinaVisitada = cursor.getString(0);
               String FechaVisita = cursor.getString(1);
               int NominaColaboradorPL = cursor.getInt(2);
               String NombreOficina = cursor.getString(3);
               int NominaColaborador = cursor.getInt(4);
               boolean ColaboradorUtilizaSimulador = cursor.getInt(5) > 0;
               String Motivo = cursor.getString(6);
               String Incidencia = cursor.getString(7);
               String Comentarios = cursor.getString(8);
               int Id = cursor.getInt(9);
               int idVisita  = cursor.getInt(10);

                SimuladorEntity simulador = new SimuladorEntity(Id, idVisita, OficinaVisitada, FechaVisita, NominaColaboradorPL, NombreOficina, NominaColaborador, ColaboradorUtilizaSimulador, Motivo, Incidencia, Comentarios);
                simuladorList.add(simulador);
            }
        } catch (Exception ex) {
            Log.d("DB_Rutas", ex.getMessage());
        }
        return simuladorList;
    }

    public boolean Insert(SimuladorEntity simulador) {
        boolean result = false;
        try {

            ContentValues values = new ContentValues();
            simulador.setId(dbHelper.getNextID("Simulador") + 1);
            values.put("Id", simulador.getId());
            values.put("IdVisita", simulador.getIdVisita());
            values.put("OficinaVisitada", simulador.getOficinaVisitada());
            values.put("FechaVisita", simulador.getFechaVisita().toString());
            values.put("NominaColaboradorPL", simulador.getNominaColaboradorPL());
            values.put("NombreOficina", simulador.getNombreOficina());
            values.put("NominaColaborador", simulador.getNominaColaborador());
            values.put("ColaboradorUtilizaSimulador", simulador.getColaboradorUtilizaSimulador());
            values.put("Motivo", simulador.getMotivo());
            values.put("Incidencia", simulador.getIncidencia());
            values.put("Comentarios", simulador.getComentarios());

            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbHelper.getDbFullPath(), null, SQLiteDatabase.OPEN_READWRITE);
            if (db.insert("Simulador", null, values) > 0) {
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
