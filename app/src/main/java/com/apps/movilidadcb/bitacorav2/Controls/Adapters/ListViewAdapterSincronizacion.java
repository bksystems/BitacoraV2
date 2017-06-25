package com.apps.movilidadcb.bitacorav2.Controls.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.apps.movilidadcb.bitacorav2.Entitys.SincronizacionEntity;
import com.apps.movilidadcb.bitacorav2.R;

import java.util.List;

/**
 * Created by jurizo on 16/11/16.
 */

public class ListViewAdapterSincronizacion extends ArrayAdapter<SincronizacionEntity>{
    int groupid;
    LayoutInflater inflater;
    List<SincronizacionEntity> lista;

    public ListViewAdapterSincronizacion(Activity context, int groupid, int id, List<SincronizacionEntity> lista) {
        super(context, id, lista);
        this.lista = lista;
        this.groupid = groupid;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View itemView = inflater.inflate(groupid, parent, false);
        TextView txtIdVisita = (TextView) itemView.findViewById(R.id.listview_custom_sincronziacion_txt_id);
        TextView txtOficina = (TextView) itemView.findViewById(R.id.listview_custom_sincronziacion_txt_oficina);
        TextView txtEtiquetaDM = (TextView) itemView.findViewById(R.id.listview_custom_sincronziacion_txt_etiqueta_dm);
        TextView txtFecha = (TextView) itemView.findViewById(R.id.listview_custom_sincronziacion_txt_fecha);
        TextView txtNominaColaborador = (TextView) itemView.findViewById(R.id.listview_custom_sincronziacion_txt_nomina);

        int id = lista.get(position).getId();
        txtIdVisita.setText(String.valueOf(id));
        txtOficina.setText(lista.get(position).getOficinaVisitada());
        txtEtiquetaDM.setText(lista.get(position).getEtiquetaDM());
        txtFecha.setText(lista.get(position).getFechaVisita());
        txtNominaColaborador.setText(lista.get(position).getNominaColaboradorVisita());

        return itemView;
    }
}
