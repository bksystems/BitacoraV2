package com.apps.movilidadcb.bitacorav2.Controls.Utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jcarl on 06/11/2016.
 */

public class Utilitis {
    public static Date ParseableDate(String s) {
        Date fechaIngresada = null;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        try{
            fechaIngresada = format.parse(s);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return fechaIngresada;
    }


}
