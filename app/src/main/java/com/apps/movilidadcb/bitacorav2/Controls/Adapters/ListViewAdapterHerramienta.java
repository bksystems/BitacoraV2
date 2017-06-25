package com.apps.movilidadcb.bitacorav2.Controls.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.apps.movilidadcb.bitacorav2.Entitys.ConocimientoEntity;
import com.apps.movilidadcb.bitacorav2.Entitys.HerramientaEntity;
import com.apps.movilidadcb.bitacorav2.R;

import java.util.List;

/**
 * Created by jcarl on 06/11/2016.
 */

public class ListViewAdapterHerramienta extends ArrayAdapter<HerramientaEntity> {

    int groupid;
    Activity context;
    List<HerramientaEntity> listHerraientas;
    LayoutInflater inflater;

    public ListViewAdapterHerramienta(Context context, int groupid, int id, List<HerramientaEntity> listHerraientas) {
        super(context, id, listHerraientas);
        this.listHerraientas = listHerraientas;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.groupid = groupid;
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        View itemView = inflater.inflate(groupid, parent, false);
        CheckBox checkSelected = (CheckBox)itemView.findViewById(R.id.listview_rows_herramientas_checkBox);
        checkSelected.setText(listHerraientas.get(position).getTipo());
        checkSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                HerramientaEntity herramientaSeleccionado = (HerramientaEntity)listHerraientas.get(position);
                herramientaSeleccionado.setIsSelected(compoundButton.isChecked());
            }
        });

        return itemView;
    }
}
