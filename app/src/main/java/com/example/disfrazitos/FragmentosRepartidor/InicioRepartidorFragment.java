package com.example.disfrazitos.FragmentosRepartidor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.VerifiedInputEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.disfrazitos.FragmentosCliente.AlquilarDisfrazFragment;
import com.example.disfrazitos.R;

public class InicioRepartidorFragment extends Fragment {
LinearLayout lyt_tomar_orden;
TextView txt_despachado_a_en_camino,txt_despachado_a_entregados;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista=inflater.inflate(R.layout.fragment_inicio_repartidor, container, false);
        lyt_tomar_orden=vista.findViewById(R.id.lyt_tomar_orden);
        txt_despachado_a_en_camino=vista.findViewById(R.id.txt_despachado_a_en_camino);
        txt_despachado_a_entregados=vista.findViewById(R.id.txt_despachado_a_entregados);

        txt_despachado_a_entregados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction= getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_repartidor, new EntregadosRepartidorFragment());
                transaction.commit();
            }
        });

        txt_despachado_a_en_camino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction= getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_repartidor, new EnCaminoRepartidorFragment());
                transaction.commit();
            }
        });

        lyt_tomar_orden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction= getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_repartidor, new DetalleOrdenFragment());
                transaction.commit();
            }
        });
        return vista;
    }
}