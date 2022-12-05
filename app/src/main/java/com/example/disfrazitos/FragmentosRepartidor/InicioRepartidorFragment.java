package com.example.disfrazitos.FragmentosRepartidor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.disfrazitos.Adaptadores.PedidosAdapter;
import com.example.disfrazitos.Entidades.Pedido;
import com.example.disfrazitos.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class InicioRepartidorFragment extends Fragment implements PedidosAdapter.EventListener {

TextView txt_despachado_a_en_camino,txt_despachado_a_entregados;
RecyclerView recycler_pedido_despachado;
PedidosAdapter pedidosAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista=inflater.inflate(R.layout.fragment_inicio_repartidor, container, false);
        //lyt_tomar_orden=vista.findViewById(R.id.lyt_tomar_orden);
        txt_despachado_a_en_camino=vista.findViewById(R.id.txt_despachado_a_en_camino);
        txt_despachado_a_entregados=vista.findViewById(R.id.txt_despachado_a_entregados);
        recycler_pedido_despachado=vista.findViewById(R.id.recycler_pedido_despachado);

        recycler_pedido_despachado.setHasFixedSize(true);
        recycler_pedido_despachado.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));


        FirebaseRecyclerOptions<Pedido> opcionesPedido=
                new FirebaseRecyclerOptions.Builder<Pedido>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Pedidos").orderByChild("estado_pedido").equalTo("DESPACHADO"),Pedido.class)
                        .build();

        pedidosAdapter=new PedidosAdapter(opcionesPedido, this);
        recycler_pedido_despachado.setAdapter(pedidosAdapter);

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
        return vista;
    }

    @Override
    public void onStart() {
        super.onStart();
        pedidosAdapter.startListening();
    }

    @Override
    public void onPause() {
        super.onPause();
        pedidosAdapter.stopListening();
    }

    @Override
    public void onEventPedidoDetalle(String pid,int id_pedido, String cliente_pedido, String telefono_pedido, String direccion_pedido, String referencia_pedido, String puntoEntrega_pedido, double latitud_pedido, double longitud_pedido, String estado_pedido, String fecha_pedido, String imagen_disfraz, String nombre_disfraz, String descripcion_disfraz, String talla_disfraz,int cantidaComprar_disfraz, float precioTotal_disfraz) {

        //Crear bundle, que son los datos que pasaremos
        Bundle datosAEnviar = new Bundle();
        // Aquí pon todos los datos que quieras en formato clave, valor
        datosAEnviar.putString("pid",pid);
        datosAEnviar.putInt("id_pedido",id_pedido);
        datosAEnviar.putString("cliente_pedido", cliente_pedido);
        datosAEnviar.putString("telefono_pedido", telefono_pedido);
        datosAEnviar.putString("direccion_pedido", direccion_pedido);
        datosAEnviar.putString("referencia_pedido", referencia_pedido);
        datosAEnviar.putString("puntoEntrega_pedido", puntoEntrega_pedido);
        datosAEnviar.putDouble("latitud_pedido", latitud_pedido);
        datosAEnviar.putDouble("longitud_pedido", longitud_pedido);
        datosAEnviar.putString("estado_pedido", estado_pedido);
        datosAEnviar.putString("fecha_pedido", fecha_pedido);
        datosAEnviar.putString("imagen_disfraz", imagen_disfraz);
        datosAEnviar.putString("nombre_disfraz", nombre_disfraz);
        datosAEnviar.putString("descripcion_disfraz", descripcion_disfraz);
        datosAEnviar.putString("talla_disfraz", talla_disfraz);
        datosAEnviar.putInt("cantidaComprar_disfraz", cantidaComprar_disfraz);
        datosAEnviar.putFloat("precioTotal_disfraz", precioTotal_disfraz);

        Fragment fragmento = new DetalleOrdenFragment();
        // ¡Importante! darle argumentos
        fragmento.setArguments(datosAEnviar);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_repartidor, fragmento);
        fragmentTransaction.addToBackStack(null);

        // Terminar transición y nos vemos en el fragmento de destino
        fragmentTransaction.commit();
    }
}