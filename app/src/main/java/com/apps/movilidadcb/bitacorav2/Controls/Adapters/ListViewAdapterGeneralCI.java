package com.apps.movilidadcb.bitacorav2.Controls.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.apps.movilidadcb.bitacorav2.Entitys.VisitaGeneralCIEntity;
import com.apps.movilidadcb.bitacorav2.R;

import java.util.List;

/**
 * Created by jcarl on 13/11/2016.
 */

public class ListViewAdapterGeneralCI extends ArrayAdapter<VisitaGeneralCIEntity> {

    private final List<VisitaGeneralCIEntity> lista;
    private final int groupid;
    private final LayoutInflater inflater;

    public ListViewAdapterGeneralCI(Activity context, int groupid, int id, List<VisitaGeneralCIEntity> lista){
        super(context, id, lista);
        this.lista = lista;
        this.groupid = groupid;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View itemView = inflater.inflate(groupid, parent, false);
        TextView txtId = (TextView) itemView.findViewById(R.id.listview_general_ci_id);
        TextView txtRuta = (TextView) itemView.findViewById(R.id.listview_general_ci_ruta);
        TextView txtNominaColaborador = (TextView) itemView.findViewById(R.id.listview_general_ci_nomina_colaborador);
        TextView txtFechaVisita = (TextView) itemView.findViewById(R.id.listview_general_ci_fecha);
        TextView txtUtilizaLista = (TextView) itemView.findViewById(R.id.listview_general_ci_utiliza_lista);

        txtId.setText(String.valueOf(lista.get(position).getId()));
        txtRuta.setText(lista.get(position).getRutaId());
        txtNominaColaborador.setText(String.valueOf(lista.get(position).getNominaColaborador()));
        txtFechaVisita.setText(lista.get(position).getFechaVisita());
        txtUtilizaLista.setText(lista.get(position).getUtilizaFormatos());

        return itemView;
    }

}
