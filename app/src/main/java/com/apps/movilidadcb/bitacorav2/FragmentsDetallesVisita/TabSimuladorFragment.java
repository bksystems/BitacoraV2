package com.apps.movilidadcb.bitacorav2.FragmentsDetallesVisita;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.movilidadcb.bitacorav2.Controls.Adapters.ListViewAdapterSimulador;
import com.apps.movilidadcb.bitacorav2.Controls.Adapters.SpinnerAdapterColaborador;
import com.apps.movilidadcb.bitacorav2.DataBase.DAOS.DAOColaborador;
import com.apps.movilidadcb.bitacorav2.DataBase.DAOS.DAOSimulador;
import com.apps.movilidadcb.bitacorav2.Entitys.ColaboradorEntity;
import com.apps.movilidadcb.bitacorav2.Entitys.SimuladorEntity;
import com.apps.movilidadcb.bitacorav2.Entitys.VisitaPlGeneralEntity;
import com.apps.movilidadcb.bitacorav2.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabSimuladorFragment extends Fragment {


    private static VisitaPlGeneralEntity visitaPlGeneralEntity;
    private View rootView;
    private ListView listViewAgregados;
    private Button buttonAgregar;
    private Spinner spinnerColaboradorSimulador;
    private SpinnerAdapterColaborador spinnerAdapterColaborador;
    private ArrayList<ColaboradorEntity> colaboradorListSimulador;
    private ArrayList<String> arrayListIncidencias;
    private Spinner spinnerPresentaIncidencia;
    private ArrayAdapter<String> arrayAdapterPresentaIncidencia;
    private Spinner spinnerMotivoNoUtiliza;
    private ArrayAdapter<String> arrayAdapterMotivoNoUtiliza;
    private ArrayList<String> arrayListMotivoNoUtiliza;
    private ListViewAdapterSimulador listviewAdapterSimulador;
    private ArrayList<SimuladorEntity> arrayListAgregadosSimulador;
    private CheckBox chkUtilizaSimulador;

    public TabSimuladorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_tab_simulador, container, false);
        chkUtilizaSimulador = (CheckBox)rootView.findViewById(R.id.simulador_cb_utiliza_simulador);

        if (visitaPlGeneralEntity != null) {
            CargarComponentes();
        } else {

        }

        return rootView;
    }

    private void CargarComponentes() {

        buttonAgregar = (Button) rootView.findViewById(R.id.simulador_btn_agregar);
        LoadSpinnerColaborador(visitaPlGeneralEntity);
        LoadSpinnerPresentaIncidencia();
        LoadSpinnerMotivoNoUtilizaSimulador();
        LoadElementosSimulador();

        buttonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(visitaPlGeneralEntity != null){
                    if(validateControlsSave()) {
                        SimuladorEntity elementoSimuladot = SaveElementoSimulador(visitaPlGeneralEntity);
                        if (elementoSimuladot != null) {
                            DAOSimulador daoSimulador = new DAOSimulador(getContext());
                            if (daoSimulador.Insert(elementoSimuladot)){
                                Toast.makeText(getContext(), "Se guardo correctamente elemento para Simulador", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getContext(), "No se pudo guardar el elemento para Simulador", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "No se pudo guardar el elemento para Simulador", Toast.LENGTH_SHORT).show();
                        }
                    }else{

                    }
                }
            }
        });

    }

    private boolean validateControlsSave() {
        return true;
    }

    private SimuladorEntity SaveElementoSimulador(VisitaPlGeneralEntity visitaGeneral) {
        SimuladorEntity elemnt = null;
        try{
            int NominaColaborador = Integer.parseInt(((TextView) rootView.findViewById(R.id.spinner_custom_colaboradores_nomina)).getText().toString());
            boolean utilizaSimulador = chkUtilizaSimulador.isChecked();
            String motivo = ((Spinner) rootView.findViewById(R.id.simulador_spinner_motivo_no_utiliza_simulador)).getSelectedItem().toString();
            String incidencia = ((Spinner) rootView.findViewById(R.id.simulador_spinner_presenta_incidencia)).getSelectedItem().toString();
            String comentarios = ((EditText)rootView.findViewById(R.id.simulador_txt_comentarios)).getText().toString();

            elemnt = new SimuladorEntity(0, visitaGeneral.getId(),
                    visitaGeneral.getOficinaVisitada(), visitaGeneral.getFechaVisita(),
                    visitaGeneral.getNominaColaboradorPL(), visitaGeneral.getOficinaVisitada(),
                    NominaColaborador,utilizaSimulador, motivo, incidencia, comentarios);

        }catch (Exception ex){
            elemnt = null;
        }
        return elemnt;
    }

    private void LoadSpinnerMotivoNoUtilizaSimulador() {
        DAOSimulador daoSimulador = new DAOSimulador(getContext());
        arrayListMotivoNoUtiliza = daoSimulador.getMotivoNoUtiliza();
        spinnerMotivoNoUtiliza = (Spinner) rootView.findViewById(R.id.simulador_spinner_motivo_no_utiliza_simulador);
        arrayAdapterMotivoNoUtiliza = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayListMotivoNoUtiliza);
        spinnerMotivoNoUtiliza.setAdapter(arrayAdapterMotivoNoUtiliza);
        spinnerMotivoNoUtiliza.setPrompt("¿Motivo no utiliza simulador?");
    }

    private void LoadSpinnerPresentaIncidencia() {
        DAOSimulador daoSimulador = new DAOSimulador(getContext());
        arrayListIncidencias = daoSimulador.getPresentaIncidecia();
        spinnerPresentaIncidencia = (Spinner) rootView.findViewById(R.id.simulador_spinner_presenta_incidencia);
        arrayAdapterPresentaIncidencia = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayListIncidencias);
        spinnerPresentaIncidencia.setAdapter(arrayAdapterPresentaIncidencia);
        spinnerPresentaIncidencia.setPrompt("¿Presenta incidencia el simulador?");
    }

    private void LoadSpinnerColaborador(VisitaPlGeneralEntity visita) {
        DAOColaborador daoColaborador = new DAOColaborador(getContext());
        colaboradorListSimulador = daoColaborador.getColaboradorByCCErp(visita.getCC_ERP());
        spinnerColaboradorSimulador = (Spinner) rootView.findViewById(R.id.simulador_spinner_colaborador);
        spinnerAdapterColaborador = new SpinnerAdapterColaborador(getActivity(), R.layout.spinner_custom_colaboradores_layout, R.id.spinner_custom_colaboradores_nomina, colaboradorListSimulador);
        spinnerColaboradorSimulador.setAdapter(spinnerAdapterColaborador);
        spinnerColaboradorSimulador.setPrompt("Selecciona colaborador");
    }

    private void LoadElementosSimulador() {

        listViewAgregados = (ListView) rootView.findViewById(R.id.simulador_listview_agregados);
        DAOSimulador daoSimulador = new DAOSimulador(getContext());
        arrayListAgregadosSimulador = daoSimulador.getAgregadosByIdGeneral(visitaPlGeneralEntity.getId());
        listviewAdapterSimulador = new ListViewAdapterSimulador(getActivity(), R.layout.listview_rows_simulador_layout, R.id.listview_custom_simulador_id, arrayListAgregadosSimulador);
        listViewAgregados.setAdapter(listviewAdapterSimulador);

    }

    public static Fragment newInstance(VisitaPlGeneralEntity visita) {
        visitaPlGeneralEntity = visita;
        Fragment fragmet = new TabSimuladorFragment();
        return fragmet;
    }
}
