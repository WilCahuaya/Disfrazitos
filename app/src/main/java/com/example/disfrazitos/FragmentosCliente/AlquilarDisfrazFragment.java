package com.example.disfrazitos.FragmentosCliente;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.disfrazitos.R;


public class AlquilarDisfrazFragment extends Fragment {
EditText edt_punto_referencia;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista=inflater.inflate(R.layout.fragment_alquilar_disfraz, container, false);

        edt_punto_referencia=vista.findViewById(R.id.edt_punto_referencia);

        edt_punto_referencia.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                FragmentTransaction transaction= getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_cliente, new MapsFragmentPedidoCliente());
                transaction.commit();
                return false;
            }
        });

        return vista;
    }
}