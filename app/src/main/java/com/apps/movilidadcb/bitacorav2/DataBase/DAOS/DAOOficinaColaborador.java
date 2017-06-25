package com.apps.movilidadcb.bitacorav2.DataBase.DAOS;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.apps.movilidadcb.bitacorav2.DataBase.DatabaseHelper;

/**
 * Created by jcarl on 05/11/2016.
 */

public class DAOOficinaColaborador {

    DatabaseHelper dbHelper = null;

    public DAOOficinaColaborador(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public boolean getAsignatedOficinas(String nomina) {
        boolean result = false;
        try {
            String query = "select * from OficinasColaborador where nomina = " + nomina;
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbHelper.getDbFullPath(), null, SQLiteDatabase.OPEN_READWRITE);

            Cursor cursor = db.rawQuery(query, null);
            int cnt = cursor.getCount();
            cursor.close();
            if(cnt > 0){
                result = true;
            }

        } catch (Exception ex) {
            Log.d("DB_OficinasColaborador", ex.getMessage());
        }
        return result;
    }
}
