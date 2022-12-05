package com.example.disfrazitos.FragmentosCliente;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.disfrazitos.R;


public class AlquilarDisfrazFragment extends Fragment {
EditText edt_punto_referencia,edt_direccion_cliente,edt_referencia_cliente;

    Bundle datosRecuperados;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista=inflater.inflate(R.layout.fragment_alquilar_disfraz, container, false);

        edt_punto_referencia=vista.findViewById(R.id.edt_punto_referencia);
        edt_direccion_cliente=vista.findViewById(R.id.edt_direccion_cliente);
        edt_referencia_cliente=vista.findViewById(R.id.edt_referencia_cliente);


        datosRecuperados = getArguments();
        if (datosRecuperados == null) {
            try {

            }catch (Exception e){
                Toast.makeText(getContext(), "No hay conexion"+e, Toast.LENGTH_SHORT).show();
            }
        }


        String imagen = datosRecuperados.getString("imagen");
        String nombre = datosRecuperados.getString("nombre");
        String descripcion = datosRecuperados.getString("descripcion");
        String talla = datosRecuperados.getString("talla");
        int stock = datosRecuperados.getInt("stock");
        float precio = datosRecuperados.getFloat("precio");
        int cantidadComprar = datosRecuperados.getInt("cantidadComprar");
        float precioTotal = datosRecuperados.getFloat("precioTotal");



        edt_punto_referencia.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Crear bundle, que son los datos que pasaremos
                Bundle datosAEnviar = new Bundle();
                // Aquí pon todos los datos que quieras en formato clave, valor
                datosAEnviar.putString("imagen", imagen);
                datosAEnviar.putString("nombre", nombre);
                datosAEnviar.putString("descripcion", descripcion);
                datosAEnviar.putString("talla", talla);
                datosAEnviar.putInt("stock", stock);
                datosAEnviar.putFloat("precio", precio);
                datosAEnviar.putInt("cantidadComprar", cantidadComprar);
                datosAEnviar.putFloat("precioTotal", precioTotal);
                String direccion=String.valueOf(edt_direccion_cliente.getText().toString());
                String referencia=String.valueOf(edt_referencia_cliente.getText().toString());
                datosAEnviar.putString("direccion", direccion);
                datosAEnviar.putString("referencia", referencia);
                Fragment fragmento = new MapsFragmentPedidoCliente();
                // ¡Importante! darle argumentos
                fragmento.setArguments(datosAEnviar);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container_cliente, fragmento);
                fragmentTransaction.addToBackStack(null);

                // Terminar transición y nos vemos en el fragmento de destino
                fragmentTransaction.commit();
                return true;
            }
        });

        return vista;
    }
}