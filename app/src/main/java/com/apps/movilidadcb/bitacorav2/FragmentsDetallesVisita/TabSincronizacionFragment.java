package com.apps.movilidadcb.bitacorav2.FragmentsDetallesVisita;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.movilidadcb.bitacorav2.Controls.Adapters.ListViewAdapterSincronizacion;
import com.apps.movilidadcb.bitacorav2.Controls.Adapters.SpinnerAdapterColaborador;
import com.apps.movilidadcb.bitacorav2.DataBase.DAOS.DAOColaborador;
import com.apps.movilidadcb.bitacorav2.DataBase.DAOS.DAOSincronizacion;
import com.apps.movilidadcb.bitacorav2.DataBase.DAOS.DAOVisitaGeneralCI;
import com.apps.movilidadcb.bitacorav2.Entitys.ColaboradorEntity;
import com.apps.movilidadcb.bitacorav2.Entitys.SincronizacionEntity;
import com.apps.movilidadcb.bitacorav2.Entitys.VisitaPlGeneralEntity;
import com.apps.movilidadcb.bitacorav2.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabSincronizacionFragment extends Fragment {


    private static VisitaPlGeneralEntity visitaPlGeneralEntity;
    private View rootView;
    private ArrayList<ColaboradorEntity> arrayColaboradores;
    private Spinner spinnerColaboradores;
    private SpinnerAdapterColaborador arrayAdapterColaboradores;
    private ArrayList<String> arrayCausaNoSincronziacion;
    private Spinner spinnerCausaNoSincronziacion;
    private Spinner spinnerUltimaSincronizacion;
    private ArrayList<String> arrayUltimaSincronziacion;
    private ArrayAdapter<String> adapterCausaNoSincronizacion;
    private ArrayAdapter<String> adapterUltimaSincronizacion;
    private EditText txtEtiquetaDM;
    private Button btnAgregarElemento;
    private ListView listViewElementos;
    private ArrayList<SincronizacionEntity> arrayElementosSincronizacion;
    private ListViewAdapterSincronizacion adapterListViewSincronizacion;

    public TabSincronizacionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_tab_sincronizacion, container, false);
        if(visitaPlGeneralEntity != null){
            LoadComponentsVIew();
        }else{
            Toast.makeText(getContext(), "Ocurrio un error al intentar cargar la información", Toast.LENGTH_SHORT).show();
        }
        return rootView;
    }

    private void LoadComponentsVIew() {
        txtEtiquetaDM = (EditText) rootView.findViewById(R.id.sincroizacion_txt_etiqueta_dispositivo);
        btnAgregarElemento = (Button) rootView.findViewById(R.id.sincroizacion_btn_agregar);
        LoadSpinnerColaborador(visitaPlGeneralEntity.getCC_ERP());
        LoadListViewElementos(visitaPlGeneralEntity.getId());
        LoadSpinnerCausaNoSincronziacion();
        LoadSpinnerUltimaSincronizacion();


        btnAgregarElemento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(visitaPlGeneralEntity != null){
                    SincronizacionEntity elmentSincronizacion =  SaveElementInSincronizacion(visitaPlGeneralEntity);
                    if(elmentSincronizacion != null){
                        arrayElementosSincronizacion.add(elmentSincronizacion);
                        adapterListViewSincronizacion.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Se agrego el registro correctamente", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "Ocurrio un error al intentar guardar la visita", Toast.LENGTH_SHORT).show();
                }
            }


        });

    }

    private SincronizacionEntity SaveElementInSincronizacion(VisitaPlGeneralEntity visita) {
        int id = 0;
        int idVisita = visita.getId();
        String OficinaVisitada = visita.getOficinaVisitada();
        String FechaVisita = visita.getFechaVisita();
        int NominaColaboradorPl = visita.getNominaColaboradorPL();
        String EtiquetaDM = txtEtiquetaDM.getText().toString();
        int NominaColaborador = Integer.parseInt(((TextView) rootView.findViewById(R.id.spinner_custom_colaboradores_nomina)).getText().toString());
        String TiempoUltimaSincronizacion = ((Spinner) rootView.findViewById(R.id.sincroizacion_spinner_ultima_sincronizacion)).getSelectedItem().toString();
        String CausaNoSincronziacion = ((Spinner) rootView.findViewById(R.id.sincroizacion_spinner_ultima_sincronizacion)).getSelectedItem().toString();

        SincronizacionEntity sincronizacion = new SincronizacionEntity(id, idVisita, OficinaVisitada, FechaVisita, NominaColaboradorPl, EtiquetaDM, NominaColaborador, TiempoUltimaSincronizacion, CausaNoSincronziacion);

        DAOSincronizacion daoSincronizacion = new DAOSincronizacion(getContext());

        if(daoSincronizacion.Insert(sincronizacion)){

        }else{
            sincronizacion = null;
        }

        return  sincronizacion;
    }

    private void LoadListViewElementos(int id) {
        listViewElementos = (ListView) rootView.findViewById(R.id.sincronizacion_listview_agregados);
        DAOSincronizacion daoSincronizacion = new DAOSincronizacion(getContext());
        arrayElementosSincronizacion = daoSincronizacion.getElementosSincronizacion(id);
        adapterListViewSincronizacion = new ListViewAdapterSincronizacion(getActivity(), R.layout.listview_rows_sincronizacion_layout, R.id.listview_custom_sincronziacion_txt_id, arrayElementosSincronizacion);
        listViewElementos.setAdapter(adapterListViewSincronizacion);

        listViewElementos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                boolean result = false;
                final int idVisitaSincronizacion = arrayElementosSincronizacion.get(position).getId();
                final int positionSelected = position;
                if(!visitaPlGeneralEntity.isSincronizado()){
                    final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getContext(), R.style.AlertDialogBitacora);
                    alertBuilder.setTitle("Elimar registro")
                            .setMessage("Eliminar el registro con numero de etiqueta: (" + arrayElementosSincronizacion.get(position).getEtiquetaDM() + ")")
                            .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(final DialogInterface dialogo, int i) {
                                    AlertDialog.Builder alertBuilderConfirmDelete = new AlertDialog.Builder(getContext(), R.style.AlertDialogBitacora);
                                    alertBuilderConfirmDelete.setTitle("Confirmar borrado")
                                            .setMessage("¿Esta seguro de eliminar el registro?")
                                            .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    DAOVisitaGeneralCI daoVisitaGeneralCI = new DAOVisitaGeneralCI(getContext());
                                                    if(daoVisitaGeneralCI.DeleteById(idVisitaSincronizacion)) {
                                                        arrayElementosSincronizacion.remove(positionSelected);
                                                        adapterListViewSincronizacion.notifyDataSetChanged();
                                                        Toast.makeText(getContext(), "Se elimino el registro correctamente", Toast.LENGTH_SHORT).show();
                                                    }else{
                                                        Toast.makeText(getContext(), "No se puede eliminar, por que ya se sincronizo", Toast.LENGTH_SHORT).show();
                                                    }
                                                    dialogo.cancel();
                                                }
                                            })
                                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int i) {
                                                    dialog.cancel();
                                                }
                                            });
                                    AlertDialog alertDialogConfirm = alertBuilderConfirmDelete.create();
                                    alertDialogConfirm.show();
                                }
                            })
                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alertDialog = alertBuilder.create();
                    alertDialog.show();
                }else{
                    Toast.makeText(getContext(), "No se puede eliminar, por que ya se sincronizo", Toast.LENGTH_SHORT).show();
                }
                return result;
            }
        });

    }

    private void LoadSpinnerUltimaSincronizacion() {
        DAOSincronizacion daoSincronizacion = new DAOSincronizacion(getContext());
        arrayUltimaSincronziacion = daoSincronizacion.getTiposUltimaSincronizacion();
        spinnerUltimaSincronizacion = (Spinner) rootView.findViewById(R.id.sincroizacion_spinner_ultima_sincronizacion);
        adapterUltimaSincronizacion = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayUltimaSincronziacion);
        spinnerUltimaSincronizacion.setAdapter(adapterCausaNoSincronizacion);
        spinnerUltimaSincronizacion.setPrompt("Tiempo ultima sincronización");
    }

    private void LoadSpinnerCausaNoSincronziacion() {
        DAOSincronizacion daoSincronizacion = new DAOSincronizacion(getContext());
        arrayCausaNoSincronziacion = daoSincronizacion.getTiposCausaNoSincronziacion();
        spinnerCausaNoSincronziacion = (Spinner) rootView.findViewById(R.id.sincroizacion_spinner_causa_no_sincronizacion);
        adapterCausaNoSincronizacion = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayCausaNoSincronziacion);
        spinnerCausaNoSincronziacion.setAdapter(adapterCausaNoSincronizacion);
        spinnerCausaNoSincronziacion.setPrompt("Causa por la que no realizo sincronizcion");
    }

    private void LoadSpinnerColaborador(int cc_erp) {
        DAOColaborador daoColaborador = new DAOColaborador(getContext());
        arrayColaboradores = daoColaborador.getColaboradorByCCErp(cc_erp);
        spinnerColaboradores = (Spinner) rootView.findViewById(R.id.sincroizacion_spinner_colaborador);
        arrayAdapterColaboradores = new SpinnerAdapterColaborador(getActivity(), R.layout.spinner_custom_colaboradores_layout, R.id.spinner_custom_colaboradores_nomina, arrayColaboradores);
        spinnerColaboradores.setAdapter(arrayAdapterColaboradores);
        spinnerColaboradores.setPrompt("Selecciona el colaborador");
    }

    public static Fragment newInstance(VisitaPlGeneralEntity visita) {
            visitaPlGeneralEntity = visita;
            Fragment fragmet = new TabSincronizacionFragment();
            return fragmet;

    }
}
