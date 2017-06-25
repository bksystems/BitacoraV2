package com.apps.movilidadcb.bitacorav2.Controls.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.apps.movilidadcb.bitacorav2.Entitys.RutaEntity;
import com.apps.movilidadcb.bitacorav2.R;

import java.util.ArrayList;

/**
 * Created by jcarl on 05/11/2016.
 */

public class SpinnerAdapterRuta extends ArrayAdapter<RutaEntity> {
    int groupid;
    Activity context;
    ArrayList<RutaEntity> listRutas;
    LayoutInflater inflater;

    public SpinnerAdapterRuta(Activity context, int grupoid, int id, ArrayList<RutaEntity> listRutas){
        super(context, id, listRutas);
        this.listRutas = listRutas;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.groupid = grupoid;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        View itemView = inflater.inflate(groupid, parent, false);

        TextView textViewRutaId = (TextView)itemView.findViewById(R.id.spinner_custom_rutas_id);
        textViewRutaId.setText(listRutas.get(position).getId_Ruta());

        TextView textViewOficina =  (TextView)itemView.findViewById(R.id.spinner_custom_rutas_oficina);
        textViewOficina.setText(listRutas.get(position).getOficina());

        TextView textViewCC_ERP =  (TextView)itemView.findViewById(R.id.spinner_custom_rutas_ccerp);
        textViewCC_ERP.setText(listRutas.get(position).getCC_ERP());

        return itemView;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent){
        return getView(position, convertView, parent);
    }

}
