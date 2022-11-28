package com.example.disfrazitos.FragmentosCliente;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.disfrazitos.R;

public class ShowDisfrazFragment extends Fragment {
    Button btn_alquilar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista=inflater.inflate(R.layout.fragment_show_disfraz, container, false);

        btn_alquilar = vista.findViewById(R.id.btn_alquilar);

        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_cliente, new ShowDisfrazFragment()).commit();
        btn_alquilar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction= getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_cliente, new AlquilarDisfrazFragment());
                transaction.commit();
            }
        });

        return vista;
    }
}