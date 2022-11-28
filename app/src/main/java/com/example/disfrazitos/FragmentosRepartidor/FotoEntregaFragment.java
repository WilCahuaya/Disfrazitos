package com.example.disfrazitos.FragmentosRepartidor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.disfrazitos.R;

public class FotoEntregaFragment extends Fragment {
Button btn_entregado;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viste= inflater.inflate(R.layout.fragment_foto_entrega, container, false);
        btn_entregado=viste.findViewById(R.id.btn_entregado);
        btn_entregado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction= getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_repartidor, new EntregadosRepartidorFragment());
                transaction.commit();
            }
        });

        return viste;
    }
}