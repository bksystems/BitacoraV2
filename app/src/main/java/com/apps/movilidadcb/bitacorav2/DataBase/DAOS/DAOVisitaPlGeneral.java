package com.apps.movilidadcb.bitacorav2.DataBase.DAOS;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.apps.movilidadcb.bitacorav2.DataBase.DatabaseHelper;
import com.apps.movilidadcb.bitacorav2.Entitys.VisitaPlGeneralEntity;

import java.util.ArrayList;

/**
 * Created by jcarl on 06/11/2016.
 */

public class DAOVisitaPlGeneral {

    private final DatabaseHelper dbHelper;
    private final Context context;

    public  DAOVisitaPlGeneral(Context context){
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public boolean insert(VisitaPlGeneralEntity visitaPlGeneralEntity) {

        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbHelper.getDbFullPath(), null, SQLiteDatabase.OPEN_READWRITE);

        try {
            ContentValues values = new ContentValues();
            visitaPlGeneralEntity.setId(dbHelper.getNextID("PLGeneral") + 1);
            values.put("Id",visitaPlGeneralEntity.getId());
            values.put("CC_ERP",visitaPlGeneralEntity.getCC_ERP());
            values.put("OficinaVisitada",visitaPlGeneralEntity.getOficinaVisitada());
            values.put("FechaVisita",visitaPlGeneralEntity.getFechaVisita().toString());
            values.put("NominaColaboradorPL",visitaPlGeneralEntity.getNominaColaboradorPL());
            values.put("NombreColaboradorPL",visitaPlGeneralEntity.getNombreColaboradorPL());
            values.put("IdRutaVisitada",visitaPlGeneralEntity.getIdRutaVisitada());
            values.put("NominaColaboradorVisita",visitaPlGeneralEntity.getNominaColaboradorVisita());
            values.put("NombreColaboradorVisita",visitaPlGeneralEntity.getNombreColaboradorVisita());
            values.put("IdentificaZonaTrabajo",visitaPlGeneralEntity.isIdentificaZonaTrabajo());
            values.put("TiempoZona",visitaPlGeneralEntity.getTiempoZona());
            values.put("ExistenZonaRiesgo",visitaPlGeneralEntity.isExistenZonaRiesgo());
            values.put("RegistranZonaRiesgo",visitaPlGeneralEntity.isRegistranZonaRiesgo());
            values.put("ConoceIdRuta",visitaPlGeneralEntity.isConoceIdRuta());
            values.put("VisualizaGeocercaGrupos",visitaPlGeneralEntity.isVisualizaGeocercaGrupos());
            values.put("ObservaGruposEnSmartPhone",visitaPlGeneralEntity.isObservaGruposEnSmartPhone());
            values.put("sincronizado",visitaPlGeneralEntity.isSincronizado());

            if (db.insert("PLGeneral", null, values) > 0) {
                db.close();
                return true;
            } else {
                db.close();
                return false;
            }
        } catch (SQLException ex) {
            Log.v("ErrorDB", ex.toString());
            db.close();
            return false;
        }
    }


    public boolean insertHerramientas(String oficina, String fechaIngresada, String nomina, String rutaVisitada, String tipo, int idVisita) {

        ContentValues values = new ContentValues();
        try {
            values.put("OficinaVisitada", oficina);
            values.put("FechaVisita", fechaIngresada.toString());
            values.put("NominaColaboradorPL", nomina);
            values.put("IdRutaVisitada", rutaVisitada);
            values.put("Herramienta", tipo);
            values.put("VisitaId", idVisita);

            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbHelper.getDbFullPath(), null, SQLiteDatabase.OPEN_READWRITE);

            if(db.insert("PLGeneral_Herramientas", null, values) > 0){
                db.close();
                return true;
            }else{
                db.close();
                return false;
            }

        } catch (SQLException ex) {
            Log.v("ErrorDB", "Error al guardar las herramientas");
            return false;
        }

    }


    public ArrayList<VisitaPlGeneralEntity> getVisitasPlGeneral() {
        ArrayList<VisitaPlGeneralEntity> visitas = new ArrayList<>();
        try {
            String query = "select * from PLGeneral";
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbHelper.getDbFullPath(), null, SQLiteDatabase.OPEN_READWRITE);

            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                int Id = cursor.getInt(0);
                int CC_ERP = cursor.getInt(1);
                String OficinaVisitada = cursor.getString(2);
                String FechaVisita = cursor.getString(3);
                int NominaColaboradorPL = cursor.getInt(4);
                String NombreColaboradorPL = cursor.getString(5);
                String IdRutaVisitada = cursor.getString(6);
                int NominaColaboradorVisita = cursor.getInt(7);
                String NombreColaboradorVisita = cursor.getString(8);
                boolean IdentificaZonaTrabajo = cursor.getInt(9) > 0;
                String TiempoZonaV = cursor.getString(10);
                boolean ExistenZonaRiesgo = cursor.getInt(11) > 0;
                boolean RegistranZonaRiesgo = cursor.getInt(12) > 0;
                boolean ConoceIdRuta = cursor.getInt(13) > 0;
                boolean VisualizaGeocercaGrupos = cursor.getInt(14) > 0;
                boolean ObservaGruposEnSmartPhone = cursor.getInt(15) > 0;
                boolean sincronizado = cursor.getInt(16) > 0;
                VisitaPlGeneralEntity visita = new VisitaPlGeneralEntity(Id, CC_ERP, OficinaVisitada, FechaVisita, NominaColaboradorPL, NombreColaboradorPL, IdRutaVisitada, NominaColaboradorVisita, NombreColaboradorVisita, IdentificaZonaTrabajo, TiempoZonaV, ExistenZonaRiesgo, RegistranZonaRiesgo, ConoceIdRuta, VisualizaGeocercaGrupos, ObservaGruposEnSmartPhone, sincronizado);
                visitas.add(visita);
            }
        } catch (Exception ex) {
            Log.d("DB_PlGeneral", ex.getMessage());
        }
        return visitas;
    }

    public boolean DeleteById(int id) {
        boolean result;

        boolean deleteGeneralCi = false;
        boolean deleteCamposPl = false;

        DAOVisitaGeneralCI daoVisitaGeneralCI = new DAOVisitaGeneralCI(context);

        deleteCamposPl = daoVisitaGeneralCI.DeleteByGeneralId(id);

        if(deleteGeneralCi == true) {
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbHelper.getDbFullPath(), null, SQLiteDatabase.OPEN_READWRITE);

            if (db.delete("PLGeneral", "Id = " + String.valueOf(id), null) > 0) {
                result = true;
            } else {
                result = false;
            }
        }else{
            result = false;
        }
        return result;
    }
}
