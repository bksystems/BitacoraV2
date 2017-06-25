package com.apps.movilidadcb.bitacorav2.FragmentsDetallesVisita;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apps.movilidadcb.bitacorav2.Entitys.VisitaPlGeneralEntity;
import com.apps.movilidadcb.bitacorav2.PlCamposActivity;
import com.apps.movilidadcb.bitacorav2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabCamposPLFragment extends Fragment {


    private static VisitaPlGeneralEntity visitaPlGeneralEntity;
    private View rootView;

    public TabCamposPLFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_tab_campos_pl, container, false);

        FloatingActionButton fabAdd = (FloatingActionButton) rootView.findViewById(R.id.plCampos_button_floatingActionButton);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCamposPl = new Intent(getContext(), PlCamposActivity.class);
                startActivity(intentCamposPl);
            }
        });

        return rootView;
    }

    public static Fragment newInstance(VisitaPlGeneralEntity visita) {
        visitaPlGeneralEntity = visita;
        Fragment fragmet = new TabCamposPLFragment();
        return fragmet;
    }
}
