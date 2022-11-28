package com.example.disfrazitos.FragmentosRepartidor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.disfrazitos.R;

public class MapsRepatidorFragment extends Fragment {
Button btn_entregar_disfraz;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_maps_repatidor, container, false);
        btn_entregar_disfraz=vista.findViewById(R.id.btn_entregar_disfraz);

        btn_entregar_disfraz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction= getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_repartidor, new FotoEntregaFragment());
                transaction.commit();
            }
        });

        return vista;
    }
}