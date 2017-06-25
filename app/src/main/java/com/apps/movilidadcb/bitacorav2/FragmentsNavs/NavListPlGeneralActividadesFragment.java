package com.apps.movilidadcb.bitacorav2.FragmentsNavs;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.apps.movilidadcb.bitacorav2.Controls.Adapters.ListViewAdapterPlGeneral;
import com.apps.movilidadcb.bitacorav2.DataBase.DAOS.DAOVisitaPlGeneral;
import com.apps.movilidadcb.bitacorav2.Entitys.VisitaPlGeneralEntity;
import com.apps.movilidadcb.bitacorav2.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavListPlGeneralActividadesFragment extends Fragment {


    private View rootView;
    private ArrayList<VisitaPlGeneralEntity> arrayListPlGeneralActividades;
    private ListView listviewControl;
    private ListViewAdapterPlGeneral adapterPlGeneralActividades;

    public NavListPlGeneralActividadesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_nav_list_pl_general_actividades, container, false);
        listviewControl = (ListView) rootView.findViewById(R.id.listview_nav_pl_general_actividades);
        final DAOVisitaPlGeneral daoVisitaPlGeneral = new DAOVisitaPlGeneral(getActivity());
        arrayListPlGeneralActividades = daoVisitaPlGeneral.getVisitasPlGeneral();
        adapterPlGeneralActividades = new ListViewAdapterPlGeneral(getActivity(), R.layout.listview_rows_plgeneral_layout, R.id.listview_actividades_pl_general_id, arrayListPlGeneralActividades);
        listviewControl.setAdapter(adapterPlGeneralActividades);


        listviewControl.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                final int idSelected = arrayListPlGeneralActividades.get(position).getId();
                final int positionSelected = position;
                if(!arrayListPlGeneralActividades.get(position).isSincronizado()){
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext(), R.style.AlertDialogBitacora);
                    alertDialogBuilder.setTitle("Opciones de visita");
                    alertDialogBuilder
                            .setMessage("Selecciono la visita de " + arrayListPlGeneralActividades.get(position).getOficinaVisitada() + " con id ruta " + arrayListPlGeneralActividades.get(position).getIdRutaVisitada() + " con fecha " + arrayListPlGeneralActividades.get(position).getFechaVisita())
                            .setCancelable(true)
                            .setPositiveButton("Editar visita", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    dialog.cancel();
                                }
                            })
                            .setNegativeButton("Eliminar visita", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    AlertDialog.Builder alertConfirmDelete = new AlertDialog.Builder(getContext(), R.style.AlertDialogBitacora);
                                    alertConfirmDelete
                                            .setTitle("Confirmar borrado")
                                            .setMessage("¿Esta seguro de eliminar la visita?")
                                            .setCancelable(true)
                                            .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int i) {
                                                    DAOVisitaPlGeneral daoVisitaPlGeneralDelete = new DAOVisitaPlGeneral(getContext());
                                                    if(daoVisitaPlGeneralDelete.DeleteById(idSelected)){
                                                        arrayListPlGeneralActividades.remove(position);
                                                        adapterPlGeneralActividades.notifyDataSetChanged();
                                                        Toast.makeText(getContext(), "La visita se elimino correctamente", Toast.LENGTH_SHORT).show();
                                                    }else{
                                                        Toast.makeText(getContext(), "No se pudo eliminar la visita", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            })
                                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int i) {
                                                    dialog.cancel();
                                                }
                                            });
                                    AlertDialog alertDialogD = alertConfirmDelete.create();

                                    alertDialogD.show();
                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                    return true;
                }else{
                    Toast.makeText(getActivity(), "La visita ya fue sincronizada, ya no se permite editar ni eliminar", Toast.LENGTH_SHORT).show();
                    return false;
                }

            }
        });

        return  rootView;
    }

}
