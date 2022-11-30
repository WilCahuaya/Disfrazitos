package com.example.disfrazitos.FragmentosRepartidor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.disfrazitos.R;

public class EnCaminoRepartidorFragment extends Fragment {
    LinearLayout lyt_ir_maps_entrega;
    TextView txt_encamino_a_despachados,txt_encamino_a_entregados;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_en_camino_repartidor, container, false);
        lyt_ir_maps_entrega=vista.findViewById(R.id.lyt_ir_maps_entrega);
        txt_encamino_a_despachados=vista.findViewById(R.id.txt_encamino_a_despachados);
        txt_encamino_a_entregados=vista.findViewById(R.id.txt_encamino_a_entregados);

        txt_encamino_a_entregados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction= getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_repartidor, new EntregadosRepartidorFragment());
                transaction.commit();
            }
        });

        txt_encamino_a_despachados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction= getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_repartidor, new InicioRepartidorFragment());
                transaction.commit();
            }
        });

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