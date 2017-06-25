package com.apps.movilidadcb.bitacorav2.Controls.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.apps.movilidadcb.bitacorav2.Entitys.TipoTiempoZonaEntity;
import com.apps.movilidadcb.bitacorav2.R;

import java.util.ArrayList;

/**
 * Created by jcarl on 06/11/2016.
 */

public class SpinnerAdapterTipoTiempoZona extends ArrayAdapter<TipoTiempoZonaEntity> {
    int groupid;
    Activity context;
    ArrayList<TipoTiempoZonaEntity> listTiempoZona;
    LayoutInflater inflater;

    public SpinnerAdapterTipoTiempoZona(Activity context, int grupoid, int id, ArrayList<TipoTiempoZonaEntity> listTiempoZona){
        super(context, id, listTiempoZona);
        this.listTiempoZona = listTiempoZona;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.groupid = grupoid;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View itemView = inflater.inflate(groupid, parent, false);

        TextView textViewTiempoZonaTiempo = (TextView)itemView.findViewById(R.id.spinner_custom_tiempo_zona_tiempo);
        textViewTiempoZonaTiempo.setText(listTiempoZona.get(position).getTiempo());

        TextView textViewTiempoZonaDescripcion = (TextView)itemView.findViewById(R.id.spinner_custom_tiempo_zona_descripcion);
        textViewTiempoZonaDescripcion.setText(listTiempoZona.get(position).getTiempo());

        return itemView;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent){
        return getView(position, convertView, parent);
    }
}
