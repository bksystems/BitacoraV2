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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.movilidadcb.bitacorav2.Controls.Adapters.ListViewAdapterGeneralCI;
import com.apps.movilidadcb.bitacorav2.Controls.Adapters.ListViewAdapterPlGeneral;
import com.apps.movilidadcb.bitacorav2.Controls.Adapters.SpinnerAdapterColaborador;
import com.apps.movilidadcb.bitacorav2.Controls.Adapters.SpinnerAdapterRuta;
import com.apps.movilidadcb.bitacorav2.DataBase.DAOS.DAOColaborador;
import com.apps.movilidadcb.bitacorav2.DataBase.DAOS.DAORuta;
import com.apps.movilidadcb.bitacorav2.DataBase.DAOS.DAOVisitaGeneralCI;
import com.apps.movilidadcb.bitacorav2.Entitys.ColaboradorEntity;
import com.apps.movilidadcb.bitacorav2.Entitys.RutaEntity;
import com.apps.movilidadcb.bitacorav2.Entitys.VisitaGeneralCIEntity;
import com.apps.movilidadcb.bitacorav2.Entitys.VisitaPlGeneralEntity;
import com.apps.movilidadcb.bitacorav2.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabCreditoIndividualFragment extends Fragment {


    private static VisitaPlGeneralEntity visitaPlGeneralEntity;
    private View rootView;
    private Spinner spinnerColaboradorCI;
    private Spinner spinnerRutaCI;
    private ArrayList<ColaboradorEntity> colaboradorListCI;
    private ArrayList<RutaEntity> rutaListCI;
    private Spinner spinnerListaNegocio;
    private TextView textViewMensaje;
    private ListView listViewAgregados;
    private Button buttonAgregar;
    private SpinnerAdapterColaborador spinnerAdapterColaborador;
    private SpinnerAdapterRuta spinnerAdapterRuta;
    private ArrayAdapter<String> adapterListaNegocio;
    private ArrayList<VisitaGeneralCIEntity> vistaListCI;
    private ListViewAdapterGeneralCI listViewAdapterGeneralCI;
    private TextView textViewTituloLista;

    public TabCreditoIndividualFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_tab_credito_individual, container, false);
        //Obtenemos los controles en variables
        textViewTituloLista = (TextView) rootView.findViewById(R.id.general_ci_txt_titulo_lista);
        textViewMensaje = (TextView) rootView.findViewById(R.id.general_ci_txt_mensaje_no_existe_ci);
        listViewAgregados = (ListView)rootView.findViewById(R.id.general_ci_listview_agregados);
        buttonAgregar = (Button) rootView.findViewById(R.id.general_ci_button_agregar);
        spinnerColaboradorCI = (Spinner) rootView.findViewById(R.id.general_ci_spinner_colaborador);
        spinnerRutaCI = (Spinner) rootView.findViewById(R.id.general_ci_spinner_id_ruta);
        spinnerListaNegocio = (Spinner) rootView.findViewById(R.id.general_ci_spinner_lista_negocios);
        //Validamos que no este vacia la visita general
        if(visitaPlGeneralEntity !=  null){
            //Comprobamos que existan registros de CI para la oficina

            DAOVisitaGeneralCI daoVisitaGeneralCI = new DAOVisitaGeneralCI(getContext());
            DAOColaborador daoColaborador = new DAOColaborador(getContext());
            DAORuta daoRuta = new DAORuta(getContext());

            colaboradorListCI = daoColaborador.getColaboradorByCCErpOnlyCI(visitaPlGeneralEntity.getCC_ERP());
            rutaListCI = daoRuta.getRutaByCCErpOnlyCI(visitaPlGeneralEntity.getCC_ERP());
            vistaListCI = daoVisitaGeneralCI.getVisitasGeneralCI(visitaPlGeneralEntity.getId());

            if(colaboradorListCI.size() >0 && rutaListCI.size() > 0){
                CargarListados(colaboradorListCI, rutaListCI, vistaListCI);
                textViewMensaje.setVisibility(View.INVISIBLE);
                textViewTituloLista.setVisibility(View.VISIBLE);
                listViewAgregados.setVisibility(View.VISIBLE);
                buttonAgregar.setVisibility(View.VISIBLE);
                spinnerColaboradorCI.setVisibility(View.VISIBLE);
                spinnerRutaCI.setVisibility(View.VISIBLE);
                spinnerListaNegocio.setVisibility(View.VISIBLE);

                buttonAgregar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(visitaPlGeneralEntity != null){
                            VisitaGeneralCIEntity visitaCISave =  SaveVisitaGeneralCI(visitaPlGeneralEntity);
                            if(visitaCISave != null){
                                vistaListCI.add(visitaCISave);
                                listViewAdapterGeneralCI.notifyDataSetChanged();
                                Toast.makeText(getContext(), "Se agrego el registro correctamente", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getContext(), "Ocurrio un error al intentar guardar la visita", Toast.LENGTH_SHORT).show();
                        }
                    }


                });

            }else{
                textViewMensaje.setText("LA OFICINA NO CUENTA CON PRODUCTO CRÉDITO INDIVIDUAL");
                textViewMensaje.setVisibility(View.VISIBLE);
                textViewTituloLista.setVisibility(View.INVISIBLE);
                listViewAgregados.setVisibility(View.INVISIBLE);
                buttonAgregar.setVisibility(View.INVISIBLE);
                spinnerColaboradorCI.setVisibility(View.INVISIBLE);
                spinnerRutaCI.setVisibility(View.INVISIBLE);
                spinnerListaNegocio.setVisibility(View.INVISIBLE);
            }
        }else{
            Toast.makeText(getContext(), "Ocurrio un erro al generar la vista", Toast.LENGTH_SHORT).show();
        }
        return  rootView;
    }

    private void CargarListados(ArrayList<ColaboradorEntity> colaboradorListCI, ArrayList<RutaEntity> rutaListCI, final ArrayList<VisitaGeneralCIEntity> vistaListCI) {

        listViewAdapterGeneralCI = new ListViewAdapterGeneralCI(getActivity(), R.layout.listview_rows_general_ci_layout, R.id.listview_general_ci_id, vistaListCI);
        listViewAgregados.setAdapter(listViewAdapterGeneralCI);

        spinnerAdapterColaborador = new SpinnerAdapterColaborador(getActivity(), R.layout.spinner_custom_colaboradores_layout, R.id.spinner_custom_colaboradores_nomina, colaboradorListCI);
        spinnerColaboradorCI.setAdapter(spinnerAdapterColaborador);
        spinnerColaboradorCI.setPrompt("Selecciona colaborador");

        spinnerAdapterRuta = new SpinnerAdapterRuta(getActivity(), R.layout.spinner_custom_rutas_layout, R.id.spinner_custom_rutas_id, rutaListCI);
        spinnerRutaCI.setAdapter(spinnerAdapterRuta);
        spinnerRutaCI.setPrompt("Selecciona ruta");

        ArrayList<String> utilLiztaNeg = new ArrayList<>();
        utilLiztaNeg.add("Si");
        utilLiztaNeg.add("No");
        utilLiztaNeg.add("No identifica");
        adapterListaNegocio = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, utilLiztaNeg);
        spinnerListaNegocio.setAdapter(adapterListaNegocio);
        spinnerListaNegocio.setPrompt("¿Uriliza lista de negocio?");

        listViewAgregados.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                boolean result = false;
                final int idVisitaCi = vistaListCI.get(position).getId();
                final int positionSelected = position;
                if(!visitaPlGeneralEntity.isSincronizado()){
                    final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getContext(), R.style.AlertDialogBitacora);
                    alertBuilder.setTitle("Elimar registro")
                            .setMessage("Eliminar el registro con ruta: (" + vistaListCI.get(position).getRutaId() + ")")
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
                                                    if(daoVisitaGeneralCI.DeleteById(idVisitaCi)) {
                                                        vistaListCI.remove(positionSelected);
                                                        listViewAdapterGeneralCI.notifyDataSetChanged();
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

    public static Fragment newInstance(VisitaPlGeneralEntity visita) {
        visitaPlGeneralEntity = visita;
        Fragment fragmet = new TabCreditoIndividualFragment();
        return fragmet;
    }

    public VisitaGeneralCIEntity SaveVisitaGeneralCI(VisitaPlGeneralEntity visita){
        int id = 0;
        int idVisita = visita.getId();
        int cc_erp = visita.getCC_ERP();
        String oficina = visita.getOficinaVisitada();
        String ruta = ((TextView) rootView.findViewById(R.id.spinner_custom_rutas_id)).getText().toString();
        int colaborador_nomina_os = Integer.parseInt(((TextView) rootView.findViewById(R.id.spinner_custom_colaboradores_nomina)).getText().toString());
        String colaborador_nombre_os = ((TextView) rootView.findViewById(R.id.spinner_custom_colaboradores_nombre)).getText().toString();
        String utilizaFormato = ((Spinner) rootView.findViewById(R.id.general_ci_spinner_lista_negocios)).getSelectedItem().toString();

        VisitaGeneralCIEntity visitaGeneralCIEntity = new VisitaGeneralCIEntity(id, idVisita, cc_erp, oficina, ruta, colaborador_nomina_os, colaborador_nombre_os, utilizaFormato, visita.getNominaColaboradorPL(), visita.getNombreColaboradorPL(), visita.getFechaVisita());

        DAOVisitaGeneralCI daoVisitaGeneralCI = new DAOVisitaGeneralCI(getContext());

        if(daoVisitaGeneralCI.Insert(visitaGeneralCIEntity)){

        }else{
            visitaGeneralCIEntity = null;
        }

        return  visitaGeneralCIEntity;
    }

}
