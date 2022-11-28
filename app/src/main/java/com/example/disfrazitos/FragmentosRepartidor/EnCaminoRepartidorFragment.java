package com.example.disfrazitos.FragmentosRepartidor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.disfrazitos.R;

public class EnCaminoRepartidorFragment extends Fragment {
    LinearLayout lyt_ir_maps_entrega;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_en_camino_repartidor, container, false);
        lyt_ir_maps_entrega=vista.findViewById(R.id.lyt_ir_maps_entrega);
        lyt_ir_maps_entrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction= getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_repartidor, new MapsRepatidorFragment());
                transaction.commit();
            }
        });
        return vista;
    }
}