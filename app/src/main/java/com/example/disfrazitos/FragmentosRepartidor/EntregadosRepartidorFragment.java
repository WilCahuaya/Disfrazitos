package com.example.disfrazitos.FragmentosRepartidor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.disfrazitos.Adaptadores.PedidosAdapter;
import com.example.disfrazitos.Entidades.Pedido;
import com.example.disfrazitos.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class EntregadosRepartidorFragment extends Fragment implements PedidosAdapter.EventListener {
    TextView txt_entregados_a_despachados, txt_entregados_a_encamino;
    RecyclerView recycler_pedido_entregado;
    PedidosAdapter pedidosAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista =inflater.inflate(R.layout.fragment_rol_repartidor, container, false);

        txt_entregados_a_despachados=vista.findViewById(R.id.txt_entregados_a_despachados);
        txt_entregados_a_encamino=vista.findViewById(R.id.txt_entregados_a_encamino);

        recycler_pedido_entregado=vista.findViewById(R.id.recycler_pedido_entregado);

        recycler_pedido_entregado.setHasFixedSize(true);
        recycler_pedido_entregado.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));


        FirebaseRecyclerOptions<Pedido> opcionesPedido=
                new FirebaseRecyclerOptions.Builder<Pedido>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Pedidos").orderByChild("estado_pedido").equalTo("ENTREGADO"),Pedido.class)
                        .build();

        pedidosAdapter=new PedidosAdapter(opcionesPedido, this);
        recycler_pedido_entregado.setAdapter(pedidosAdapter);

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
    public void onEventPedidoDetalle(String pid, int id_pedido, String cliente_pedido, String telefono_pedido, String direccion_pedido, String referencia_pedido, String puntoEntrega_pedido, double latitud_pedido, double longitud_pedido, String estado_pedido, String fecha_pedido, String imagen_disfraz, String nombre_disfraz, String descripcion_disfraz, String talla_disfraz, int cantidaComprar_disfraz, float precioTotal_disfraz) {
        Toast.makeText(getContext(), "Pedidos entregado a "+cliente_pedido, Toast.LENGTH_SHORT).show();
    }
}