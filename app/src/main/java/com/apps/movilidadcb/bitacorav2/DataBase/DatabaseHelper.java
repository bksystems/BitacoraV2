package com.apps.movilidadcb.bitacorav2.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by jcarl on 02/11/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "";
    private static final String DB_NAME = "BitacorPL.s3db";
    private SQLiteDatabase sqlite_db;
    private String DB_FULL_PATH = "";
    private final Context context;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        if (Build.VERSION.SDK_INT >= 17){
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        }else{
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        this.context = context;
    }

    /* Metodos de creación y validacion de la base de datos*/

    public void Create() throws IOException{

    }

    public String getDbFullPath() {
        return DB_PATH + DB_NAME;
    }

    public boolean IsCheckDataBaseExist() throws IOException{
        SQLiteDatabase checkDB = null;
        try{
            String pathDB = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(pathDB, null, SQLiteDatabase.OPEN_READONLY);
        }catch (SQLiteException e){
            e.printStackTrace();
        }
        if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    public void ReplaceDataBase(File file) throws IOException{
        boolean dbExist = IsCheckDataBaseExist();
        if(dbExist){
            File dbFile = context.getDatabasePath(getDatabaseName());
            if(!dbFile.delete()){
                Log.w("DB","No existe la base de datos");
            }else{
                Log.w("DB", "Se elimino la base de datos");
            }

            InputStream inpStream = new FileInputStream(file);
            String outFileName = DB_PATH + DB_NAME;
            OutputStream outStream = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inpStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }
            outStream.flush();
            outStream.close();
            inpStream.close();
            Toast.makeText(context, "Se remplazo la base de datos", Toast.LENGTH_SHORT).show();

        }else{
            this.getReadableDatabase();
            try {
                InputStream inpStream = new FileInputStream(file);
                String outFileName = DB_PATH + DB_NAME;
                OutputStream outStream = new FileOutputStream(outFileName);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inpStream.read(buffer)) > 0) {
                    outStream.write(buffer, 0, length);
                }
                outStream.flush();
                outStream.close();
                inpStream.close();
                Toast.makeText(context, "Se remplazo la base de datos", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public static int getNextID(String Table){
        int next_id = 0;
        try{
            String query = "SELECT Max(Id) FROM " + Table;
            SQLiteDatabase db = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);

            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                next_id = cursor.getInt(0);
            }
            if(next_id <= 0){
                next_id += 1;
            }

        }catch(Exception ex){
            Log.d("DB_GetNextID", ex.getMessage());
            next_id = 0;
        }
        return next_id;
    }

    public boolean backupDatabase() {
        boolean result = false;
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String currentDBPath = DB_PATH + DB_NAME;

                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("dd_MM_yy-HH_mm_ss");
                String formattedDate = df.format(c.getTime());

                String backupDBPath = "bitacora_" + formattedDate + ".s3db";
                File currentDB = new File(currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                    result = true;
                }
            }
        }catch (Exception ex){
            Log.e("ErrorDB", ex.getMessage());
            result = false;
        }
        return result;
    }
}
