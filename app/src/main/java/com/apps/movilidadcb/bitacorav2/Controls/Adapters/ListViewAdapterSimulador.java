package com.apps.movilidadcb.bitacorav2.Controls.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.apps.movilidadcb.bitacorav2.Entitys.SimuladorEntity;
import com.apps.movilidadcb.bitacorav2.R;

import java.util.List;

/**
 * Created by jurizo on 16/11/16.
 */

public class ListViewAdapterSimulador extends ArrayAdapter<SimuladorEntity> {
    int groupid;
    LayoutInflater inflater;
    List<SimuladorEntity> lista;

    public ListViewAdapterSimulador(Activity context, int groupid, int id, List<SimuladorEntity> lista) {
        super(context, id, lista);
        this.lista = lista;
        this.groupid = groupid;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View itemView = inflater.inflate(groupid, parent, false);
        TextView txtIdVisita = (TextView) itemView.findViewById(R.id.listview_custom_simulador_id);
        TextView txtOficina = (TextView) itemView.findViewById(R.id.listview_custom_simulador_oficina);
        TextView txtNominaColaborador = (TextView) itemView.findViewById(R.id.listview_custom_simulador_nomina_colaborador);
        TextView txtFecha = (TextView) itemView.findViewById(R.id.listview_custom_sincronziacion_txt_fecha);


        int id = lista.get(position).getId();
        txtIdVisita.setText(String.valueOf(id));
        txtOficina.setText(lista.get(position).getOficinaVisitada());
        txtNominaColaborador.setText(lista.get(position).getNominaColaborador());
        txtFecha.setText(lista.get(position).getFechaVisita());

        return itemView;
    }
}