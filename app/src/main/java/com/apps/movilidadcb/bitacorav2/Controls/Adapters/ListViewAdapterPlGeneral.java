package com.apps.movilidadcb.bitacorav2.Controls.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.apps.movilidadcb.bitacorav2.Entitys.VisitaPlGeneralEntity;
import com.apps.movilidadcb.bitacorav2.R;

import java.util.List;

/**
 * Created by jcarl on 12/11/2016.
 */

public class ListViewAdapterPlGeneral extends ArrayAdapter<VisitaPlGeneralEntity> {

    int groupid;
    LayoutInflater inflater;
    List<VisitaPlGeneralEntity> lista;

    public ListViewAdapterPlGeneral(Activity context, int groupid, int id, List<VisitaPlGeneralEntity> lista) {
        super(context, id, lista);
        this.lista = lista;
        this.groupid = groupid;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View itemView = inflater.inflate(groupid, parent, false);
        TextView txtIdVisita = (TextView) itemView.findViewById(R.id.listview_actividades_pl_general_id);
        TextView txtOficina = (TextView) itemView.findViewById(R.id.listview_actividades_pl_general_oficina);
        TextView txtRutaId = (TextView) itemView.findViewById(R.id.listview_actividades_pl_general_rutaId);
        TextView txtFecha = (TextView) itemView.findViewById(R.id.listview_actividades_pl_general_fecha);
        TextView txtEstatus = (TextView) itemView.findViewById(R.id.listview_actividades_pl_general_estatus);

        int id = lista.get(position).getId();
        txtIdVisita.setText(String.valueOf(id));
        txtOficina.setText(lista.get(position).getOficinaVisitada());
        txtRutaId.setText(lista.get(position).getIdRutaVisitada());
        txtFecha.setText(lista.get(position).getFechaVisita());

        String status;

        if (lista.get(position).isSincronizado()) {
            status = "Sincronizada";
            txtEstatus.setTextColor(getContext().getResources().getColor(R.color.prioridadBaja));
        } else {
            status = "No Sincronizada";
            txtEstatus.setTextColor(getContext().getResources().getColor(R.color.prioridadAlta));
        }
        txtEstatus.setText(status);

        return itemView;
    }
}
