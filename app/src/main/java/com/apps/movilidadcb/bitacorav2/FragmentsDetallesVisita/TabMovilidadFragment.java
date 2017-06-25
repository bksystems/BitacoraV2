package com.apps.movilidadcb.bitacorav2.FragmentsDetallesVisita;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.apps.movilidadcb.bitacorav2.DataBase.DAOS.DAOColaborador;
import com.apps.movilidadcb.bitacorav2.Entitys.VisitaPlGeneralEntity;
import com.apps.movilidadcb.bitacorav2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabMovilidadFragment extends Fragment {


    private static VisitaPlGeneralEntity visitaPlGeneralEntity;
    private View rootView;

    public TabMovilidadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_tab_movilidad, container, false);

        return rootView;
    }

    public static Fragment newInstance(VisitaPlGeneralEntity visita) {
        visitaPlGeneralEntity = visita;
        Fragment fragmet = new TabMovilidadFragment();
        return fragmet;
    }
}
