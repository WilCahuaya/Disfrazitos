package com.example.disfrazitos.FragmentosCliente;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.disfrazitos.R;

public class PuntoSeleccionadoFragment extends Fragment {
    Button btn_selecionar_punto;
    EditText edt_punto_referencia,edt_referencia,edt_direccion;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista=inflater.inflate(R.layout.fragment_punto_seleccionado, container, false);

        btn_selecionar_punto=vista.findViewById(R.id.btn_selecionar_punto);
        edt_punto_referencia=vista.findViewById(R.id.edt_punto_referencia);
        edt_referencia=vista.findViewById(R.id.edt_referencia);
        edt_direccion=vista.findViewById(R.id.edt_direccion);

        edt_punto_referencia.setEnabled(false);
        edt_referencia.setEnabled(false);
        edt_direccion.setEnabled(false);

        edt_punto_referencia.setText("Av. Los Alisales, Pasto, Nariño #3");
        edt_referencia.setText("Barrio San Martin - Paradero 17");
        edt_direccion.setText("Av. Los Alisales");

        btn_selecionar_punto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction= getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_cliente, new PagoFragment());
                transaction.commit();
            }
        });

        return vista;
    }
}