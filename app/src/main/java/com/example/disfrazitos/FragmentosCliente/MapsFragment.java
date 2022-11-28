package com.example.disfrazitos.FragmentosCliente;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.disfrazitos.R;

public class MapsFragment extends Fragment {
    Button btn_selecionar_punto;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista=inflater.inflate(R.layout.fragment_maps, container, false);
        btn_selecionar_punto=vista.findViewById(R.id.btn_selecionar_punto);

        btn_selecionar_punto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction= getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_cliente, new PuntoSeleccionadoFragment());
                transaction.commit();
            }
        });
        return  vista;
    }
}