package com.apps.movilidadcb.bitacorav2.FragmentsNavs;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.movilidadcb.bitacorav2.Controls.Adapters.ListViewAdapterConocimiento;
import com.apps.movilidadcb.bitacorav2.Controls.Adapters.ListViewAdapterHerramienta;
import com.apps.movilidadcb.bitacorav2.Controls.Adapters.SpinnerAdapterColaborador;
import com.apps.movilidadcb.bitacorav2.Controls.Adapters.SpinnerAdapterOficina;
import com.apps.movilidadcb.bitacorav2.Controls.Adapters.SpinnerAdapterRuta;
import com.apps.movilidadcb.bitacorav2.Controls.Adapters.SpinnerAdapterTipoTiempoZona;
import com.apps.movilidadcb.bitacorav2.DataBase.DAOS.DAOColaborador;
import com.apps.movilidadcb.bitacorav2.DataBase.DAOS.DAOConocimiento;
import com.apps.movilidadcb.bitacorav2.DataBase.DAOS.DAOHerramienta;
import com.apps.movilidadcb.bitacorav2.DataBase.DAOS.DAOOficina;
import com.apps.movilidadcb.bitacorav2.DataBase.DAOS.DAORuta;
import com.apps.movilidadcb.bitacorav2.DataBase.DAOS.DAOTipoTiempoZona;
import com.apps.movilidadcb.bitacorav2.DataBase.DAOS.DAOVisitaPlGeneral;
import com.apps.movilidadcb.bitacorav2.DetalleVisitaActivity;
import com.apps.movilidadcb.bitacorav2.Entitys.ColaboradorEntity;
import com.apps.movilidadcb.bitacorav2.Entitys.ConocimientoEntity;
import com.apps.movilidadcb.bitacorav2.Entitys.HerramientaEntity;
import com.apps.movilidadcb.bitacorav2.Entitys.OficinaEntity;
import com.apps.movilidadcb.bitacorav2.Entitys.RutaEntity;
import com.apps.movilidadcb.bitacorav2.Entitys.TipoTiempoZonaEntity;
import com.apps.movilidadcb.bitacorav2.Entitys.UserGlobalClass;
import com.apps.movilidadcb.bitacorav2.Entitys.UsuarioEntity;
import com.apps.movilidadcb.bitacorav2.Entitys.VisitaPlGeneralEntity;
import com.apps.movilidadcb.bitacorav2.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class VisitaFragment extends Fragment {

    private View rootView;
    private CalendarView calendarView;
    private EditText txtDateSelected;
    private UsuarioEntity usuarioEntity;
    private ArrayList<ConocimientoEntity> listArrayConocimientos;
    private ArrayList<HerramientaEntity> listArrayHerramientas;

    public VisitaFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        final UserGlobalClass userGlobalClass = (UserGlobalClass) getActivity().getApplicationContext();
        rootView = inflater.inflate(R.layout.fragment_nav_visita, container, false);
        usuarioEntity = userGlobalClass.getUserEntity();
        if(usuarioEntity != null){
            loadAllComponents(userGlobalClass.getUserEntity());
        }else{

        }
        return rootView;
    }

    private void loadAllComponents(UsuarioEntity user) {
        loadCalendarTextview();
        loadSpinnerOficinas(user);
        loadSpinnerTiempoEnZona();
        loadListViewHerramientas();
        loadListViewConocimientos();
    }

    private void loadListViewConocimientos() {
        ListView listViewConocimientoControl = (ListView) rootView.findViewById(R.id.visita_listview_conocimientos);
        DAOConocimiento daoConocimiento = new DAOConocimiento(getActivity());
        listArrayConocimientos = daoConocimiento.getConocimientos();
        ListViewAdapterConocimiento listViewAdapterConocimiento = new ListViewAdapterConocimiento(getActivity(), R.layout.listview_rows_conocimientos_layout, R.id.listview_rows_conocimientos_checkBox, listArrayConocimientos);
        listViewConocimientoControl.setAdapter(listViewAdapterConocimiento);
    }

    private void loadListViewHerramientas() {
        ListView listViewHerramientaControl = (ListView) rootView.findViewById(R.id.visita_listview_herramientas);
        DAOHerramienta daoHerramienta= new DAOHerramienta(getActivity());
        listArrayHerramientas = daoHerramienta.getHerramientas();
        ListViewAdapterHerramienta listViewAdapterHerramienta = new ListViewAdapterHerramienta(getActivity(), R.layout.listview_rows_herramientas_layout, R.id.listview_rows_herramientas_checkBox, listArrayHerramientas);
        listViewHerramientaControl.setAdapter(listViewAdapterHerramienta);

    }

    private void loadSpinnerTiempoEnZona() {
        Spinner spinnerTiempoZonaControl =  (Spinner) rootView.findViewById(R.id.visita_spinner_timpo_zona);
        DAOTipoTiempoZona daoTipoTiempoZona = new DAOTipoTiempoZona(getActivity());
        ArrayList<TipoTiempoZonaEntity> arrayTipoTiempoZona = daoTipoTiempoZona.getTipoTiempoZona();
        SpinnerAdapterTipoTiempoZona spinnerAdapterTipoTiempoZona = new SpinnerAdapterTipoTiempoZona(getActivity(), R.layout.spinner_custom_tiempo_zona_layout, R.id.spinner_custom_tiempo_zona_tiempo, arrayTipoTiempoZona);
        spinnerTiempoZonaControl.setAdapter(spinnerAdapterTipoTiempoZona);
        spinnerTiempoZonaControl.setPrompt("Selecciona tiempo en zona");
    }

    private void loadSpinnerOficinas(UsuarioEntity user) {
        Spinner spinnerOficinaControl =  (Spinner) rootView.findViewById(R.id.visita_spinner_oficina);
        DAOOficina daoOficina = new DAOOficina(getActivity());
        ArrayList<OficinaEntity> arrayOficinas = daoOficina.getOficinasByUserNomina(user.getKey());
        SpinnerAdapterOficina spinnerAdapterOficina = new SpinnerAdapterOficina(getActivity(), R.layout.spinner_custom_oficinas_layout, R.id.spinner_custom_oficina_ccerpOficina, arrayOficinas);
        spinnerOficinaControl.setAdapter(spinnerAdapterOficina);
        spinnerOficinaControl.setPrompt("Selecciona oficina");

        spinnerOficinaControl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String cc_erp_os = ((TextView) view.findViewById(R.id.spinner_custom_oficina_ccerpOficina)).getText().toString();
                loadSpinnerRutas(Integer.parseInt(cc_erp_os));
                loadSpinnerColaboradores(Integer.parseInt(cc_erp_os));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void loadSpinnerRutas(int cc_erp_os){
        Spinner spinnerRutaControl =  (Spinner) rootView.findViewById(R.id.visita_spinner_ruta);
        DAORuta daoRuta = new DAORuta(getActivity());
        ArrayList<RutaEntity> arrayRutas = daoRuta.getRutaByCCErp(cc_erp_os);
        SpinnerAdapterRuta spinnerAdapterRuta = new SpinnerAdapterRuta(getActivity(), R.layout.spinner_custom_rutas_layout, R.id.spinner_custom_rutas_id, arrayRutas);
        spinnerRutaControl.setAdapter(spinnerAdapterRuta);
        spinnerRutaControl.setPrompt("Selecciona ruta");

    }
    private void loadSpinnerColaboradores(int cc_erp_os){
        Spinner spinnerColaboradorControl =  (Spinner) rootView.findViewById(R.id.visita_spinner_colaborador);
        DAOColaborador daoColaborador = new DAOColaborador(getActivity());
        ArrayList<ColaboradorEntity> arrayColaborador = daoColaborador.getColaboradorByCCErp(cc_erp_os);
        SpinnerAdapterColaborador spinnerAdapterColaborador = new SpinnerAdapterColaborador(getActivity(), R.layout.spinner_custom_colaboradores_layout, R.id.spinner_custom_colaboradores_nomina, arrayColaborador);
        spinnerColaboradorControl.setAdapter(spinnerAdapterColaborador);
        spinnerColaboradorControl.setPrompt("Selecciona ruta");

    }
    private void loadCalendarTextview() {

        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        txtDateSelected = (EditText) rootView.findViewById(R.id.visita_txt_date_selected);
        txtDateSelected.setInputType(InputType.TYPE_NULL);
        txtDateSelected.requestFocus();
        txtDateSelected.setOnClickListener(new View.OnClickListener() {
            public DatePickerDialog picker_selected_date;

            @Override
            public void onClick(View v) {

                Calendar newCalendar = Calendar.getInstance();
                picker_selected_date = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        txtDateSelected.setText(dateFormat.format(newDate.getTime()));
                    }
                }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

                if (v == txtDateSelected) {
                    picker_selected_date.show();
                }
            }

        });

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_option_bar_visitas_save, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_bar_visita_save:
                if(validateDataView() && usuarioEntity != null){
                    try {
                        VisitaPlGeneralEntity visitaPlGeneralEntity = SaveVisita(usuarioEntity);
                        if(visitaPlGeneralEntity != null) {
                            Intent detalleVisitaActivity = new Intent(getActivity(), DetalleVisitaActivity.class);
                            detalleVisitaActivity.putExtra("VisitaGeneral", visitaPlGeneralEntity);
                            startActivity(detalleVisitaActivity);
                        }else{
                            Toast.makeText(getActivity(), "No se pudo guardar la visita, por favor intente nuevamente.", Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    }catch (Exception ex){
                        Toast.makeText(getActivity(), "No se pudo guardar la visita, por favor intente nuevamente.", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }else{
                    Toast.makeText(getActivity(), "Debe ingresar la fecha de visita", Toast.LENGTH_SHORT).show();
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private VisitaPlGeneralEntity SaveVisita(UsuarioEntity user) {
        VisitaPlGeneralEntity visitaPlGeneralEntity = null;

        int id = 0;
        //Date fechaIngresada = Utilitis.ParseableDate(txtDateSelected.getText().toString());
        String fechaIngresada = txtDateSelected.getText().toString();
        String cc_erp_os = ((TextView) rootView.findViewById(R.id.spinner_custom_oficina_ccerpOficina)).getText().toString();
        String oficina = ((TextView) rootView.findViewById(R.id.spinner_custom_oficina_nombreOficina)).getText().toString();
        String nomina = user.getKey().toString();
        String colaborador = user.getValue().toString();
        String os_col_nomina = ((TextView) rootView.findViewById(R.id.spinner_custom_colaboradores_nomina)).getText().toString();
        String os_col_nombre = ((TextView) rootView.findViewById(R.id.spinner_custom_colaboradores_nombre)).getText().toString();
        String rutaVisitada = ((TextView) rootView.findViewById(R.id.spinner_custom_rutas_id)).getText().toString();
        String tiempoZona = ((TextView) rootView.findViewById(R.id.spinner_custom_tiempo_zona_tiempo)).getText().toString();

        boolean IdentificaZona = false;
        boolean ConeciIdRuta = false;
        boolean ExistenZonasDeRiesgo = false;
        boolean RegistranZonasDeRiesgo = false;
        boolean VisualizaGeocercas = false;
        boolean ObservaGrupos = false;




        int count = listArrayConocimientos.size();

        for (int i = 0; i < count; i++) {
            ConocimientoEntity conocimiento = (ConocimientoEntity) listArrayConocimientos.get(i);
            switch (i) {
                case 0:
                    IdentificaZona = conocimiento.isSelected;
                    break;
                case 1:
                    ConeciIdRuta = conocimiento.isSelected;
                    break;
                case 2:
                    ExistenZonasDeRiesgo = conocimiento.isSelected;
                    break;
                case 3:
                    RegistranZonasDeRiesgo = conocimiento.isSelected;
                    break;
                case 4:
                    VisualizaGeocercas = conocimiento.isSelected;
                    break;
                case 5:
                    ObservaGrupos = conocimiento.isSelected;
                    break;
                default:

            }
        }

        boolean sincronizado = false;

        //visitaPlGeneralEntity = new VisitaPlGeneralEntity(id,Integer.parseInt(cc_erp_os), oficina, fechaIngresada, Integer.parseInt(nomina), colaborador, rutaVisitada, Integer.parseInt(os_col_nomina), os_col_nombre, IdentificaZona, tiempoZona, ExistenZonasDeRiesgo, RegistranZonasDeRiesgo, ConeciIdRuta, VisualizaGeocercas, ObservaGrupos, sincronizado);

        visitaPlGeneralEntity = new VisitaPlGeneralEntity(id,Integer.parseInt(cc_erp_os), oficina, fechaIngresada, Integer.parseInt(nomina), colaborador, rutaVisitada, Integer.parseInt(os_col_nomina), os_col_nombre, IdentificaZona, tiempoZona, ExistenZonasDeRiesgo, RegistranZonasDeRiesgo, ConeciIdRuta, VisualizaGeocercas, ObservaGrupos, sincronizado);

        DAOVisitaPlGeneral daoVisitaPlGeneral = new DAOVisitaPlGeneral(getActivity());
        if(daoVisitaPlGeneral.insert(visitaPlGeneralEntity)){
            int countHerramientas = listArrayHerramientas.size();
            for (int j = 0; j < countHerramientas; j++) {
                HerramientaEntity herramienta = (HerramientaEntity) listArrayHerramientas.get(j);
                if(herramienta.isSelected) {
                    daoVisitaPlGeneral.insertHerramientas(oficina, fechaIngresada, nomina, rutaVisitada, herramienta.getTipo(), visitaPlGeneralEntity.getId());
                }

            }
        }else{
            visitaPlGeneralEntity = null;
        }

        return visitaPlGeneralEntity;
    }

    private boolean validateDataView() {
        String getDateText = txtDateSelected.getText().toString();
        if(getDateText.isEmpty()){
            return false;
        }else{
            return true;
        }

    }
}
