package com.example.disfrazitos.FragmentosRepartidor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.disfrazitos.Adaptadores.PedidosAdapter;
import com.example.disfrazitos.Entidades.Pedido;
import com.example.disfrazitos.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class InicioRepartidorFragment extends Fragment {
//LinearLayout lyt_tomar_orden;
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

        pedidosAdapter=new PedidosAdapter(opcionesPedido);
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

//        lyt_tomar_orden.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentTransaction transaction= getParentFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragment_container_repartidor, new DetalleOrdenFragment());
//                transaction.commit();
//            }
//        });
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
}