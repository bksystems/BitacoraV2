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
import com.apps.movilidadcb.bitacorav2.R;

import java.util.List;

/**
 * Created by jcarl on 06/11/2016.
 */

public class ListViewAdapterConocimiento extends ArrayAdapter<ConocimientoEntity>{

    int groupid;
    Activity context;
    List<ConocimientoEntity> listConocimientos;
    LayoutInflater inflater;

    public ListViewAdapterConocimiento(Context context, int groupid, int id, List<ConocimientoEntity> listConocimientos) {
        super(context, id, listConocimientos);
        this.listConocimientos = listConocimientos;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.groupid = groupid;
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        View itemView = inflater.inflate(groupid, parent, false);
        CheckBox checkSelected = (CheckBox)itemView.findViewById(R.id.listview_rows_conocimientos_checkBox);
        checkSelected.setText(listConocimientos.get(position).getConocimiento());
        checkSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ConocimientoEntity conocimientoSeleccionado = (ConocimientoEntity)listConocimientos.get(position);
                conocimientoSeleccionado.setIsSelected(compoundButton.isChecked());
            }
        });

        return itemView;
    }
}
