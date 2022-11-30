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

public class EntregadosRepartidorFragment extends Fragment {
    TextView txt_entregados_a_despachados, txt_entregados_a_encamino;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista =inflater.inflate(R.layout.fragment_rol_repartidor, container, false);

        txt_entregados_a_despachados=vista.findViewById(R.id.txt_entregados_a_despachados);
        txt_entregados_a_encamino=vista.findViewById(R.id.txt_entregados_a_encamino);

        txt_entregados_a_encamino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction= getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_repartidor, new EnCaminoRepartidorFragment());
                transaction.commit();
            }
        });

        txt_entregados_a_despachados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction= getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_repartidor, new InicioRepartidorFragment());
                transaction.commit();
            }
        });
        return vista;
    }
}