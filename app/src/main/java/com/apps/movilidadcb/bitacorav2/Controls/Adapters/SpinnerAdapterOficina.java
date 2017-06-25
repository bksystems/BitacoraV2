package com.apps.movilidadcb.bitacorav2.Controls.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.apps.movilidadcb.bitacorav2.Entitys.OficinaEntity;
import com.apps.movilidadcb.bitacorav2.R;

import java.util.ArrayList;

/**
 * Created by jcarl on 05/11/2016.
 */

public class SpinnerAdapterOficina extends ArrayAdapter<OficinaEntity> {
    int groupid;
    Activity context;
    ArrayList<OficinaEntity> listOficinas;
    LayoutInflater inflater;

    public SpinnerAdapterOficina(Activity context, int grupoid, int id, ArrayList<OficinaEntity> listOficinas){
        super(context, id, listOficinas);
        this.listOficinas = listOficinas;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.groupid = grupoid;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View itemView = inflater.inflate(groupid, parent, false);

        TextView textViewOficina = (TextView)itemView.findViewById(R.id.spinner_custom_oficina_nombreOficina);
        textViewOficina.setText(listOficinas.get(position).getNombre());

        TextView textViewEstructura =  (TextView)itemView.findViewById(R.id.spinner_custom_oficina_estructuraOficina);
        textViewEstructura.setText(listOficinas.get(position).getDireccion() + " > " + listOficinas.get(position).getSubdireccion() + " > " + listOficinas.get(position).getRegion());

        TextView txtDetalle =  (TextView)itemView.findViewById(R.id.spinner_custom_oficina_ccerpOficina);
        txtDetalle.setText(listOficinas.get(position).getCC_ERP());

        return itemView;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent){
        return getView(position, convertView, parent);
    }
}
