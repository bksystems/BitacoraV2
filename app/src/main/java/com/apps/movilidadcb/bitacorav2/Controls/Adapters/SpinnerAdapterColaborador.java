package com.apps.movilidadcb.bitacorav2.Controls.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.apps.movilidadcb.bitacorav2.Entitys.ColaboradorEntity;
import com.apps.movilidadcb.bitacorav2.R;

import java.util.ArrayList;

/**
 * Created by jcarl on 05/11/2016.
 */

public class SpinnerAdapterColaborador extends ArrayAdapter<ColaboradorEntity> {

    int groupid;
    Activity context;
    ArrayList<ColaboradorEntity> listColaborador;
    LayoutInflater inflater;

    public SpinnerAdapterColaborador(Activity context, int grupoid, int id, ArrayList<ColaboradorEntity> listColaborador){
        super(context, id, listColaborador);
        this.listColaborador = listColaborador;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.groupid = grupoid;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View itemView = inflater.inflate(groupid, parent, false);

        TextView textViewNombreColaborador = (TextView)itemView.findViewById(R.id.spinner_custom_colaboradores_nombre);
        textViewNombreColaborador.setText(listColaborador.get(position).getColaborador());

        TextView textViewPuesto =  (TextView)itemView.findViewById(R.id.spinner_custom_colaboradores_puesto);
        textViewPuesto.setText(listColaborador.get(position).getPuesto());

        TextView tetxViewNominaColaborador =  (TextView)itemView.findViewById(R.id.spinner_custom_colaboradores_nomina);
        tetxViewNominaColaborador.setText(listColaborador.get(position).getNomina());

        return itemView;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent){
        return getView(position, convertView, parent);
    }

}
