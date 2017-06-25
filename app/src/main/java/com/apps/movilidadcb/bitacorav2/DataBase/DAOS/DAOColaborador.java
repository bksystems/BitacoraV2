package com.apps.movilidadcb.bitacorav2.DataBase.DAOS;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.apps.movilidadcb.bitacorav2.DataBase.DatabaseHelper;
import com.apps.movilidadcb.bitacorav2.Entitys.ColaboradorEntity;

import java.util.ArrayList;

/**
 * Created by jcarl on 06/11/2016.
 */

public class DAOColaborador {

    DatabaseHelper dbHelper = null;

    public DAOColaborador(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public ArrayList<ColaboradorEntity> getColaboradorByCCErp(int cc_erp_os) {
        ArrayList<ColaboradorEntity> colaboradores = new ArrayList<>();
        try {
            String query = "select * from Colaboradores where CC_ERP = " + cc_erp_os;
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbHelper.getDbFullPath(), null, SQLiteDatabase.OPEN_READWRITE);
            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                int Id = Integer.parseInt(cursor.getString(0));
                String CC_ERP = cursor.getString(1);
                String Nomina = cursor.getString(2);
                String Colaborador = cursor.getString(3);
                String Puesto = cursor.getString(4);
                ColaboradorEntity colaborador = new ColaboradorEntity(Id, CC_ERP, Nomina, Colaborador, Puesto);
                colaboradores.add(colaborador);
            }
        } catch (Exception ex) {
            Log.d("DB_Colaboradores", ex.getMessage());
        }
        return colaboradores;
    }

    public ArrayList<ColaboradorEntity> getColaboradorByCCErpOnlyCI(int cc_erp) {
        ArrayList<ColaboradorEntity> colaboradores = new ArrayList<>();
        try {
            String query = "select * from Colaboradores where (Puesto like '%CI%' or Puesto like '%MI%' or Puesto like '%INDIVIDUAL%' or Puesto like '%ASESOR%') and CC_ERP = " + cc_erp;
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbHelper.getDbFullPath(), null, SQLiteDatabase.OPEN_READWRITE);
            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                int Id = Integer.parseInt(cursor.getString(0));
                String CC_ERP = cursor.getString(1);
                String Nomina = cursor.getString(2);
                String Colaborador = cursor.getString(3);
                String Puesto = cursor.getString(4);
                ColaboradorEntity colaborador = new ColaboradorEntity(Id, CC_ERP, Nomina, Colaborador, Puesto);
                colaboradores.add(colaborador);
            }
        } catch (Exception ex) {
            Log.d("DB_Colaboradores", ex.getMessage());
        }
        return colaboradores;
    }


}
