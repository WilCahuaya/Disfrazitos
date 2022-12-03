package com.example.disfrazitos.FragmentosCliente;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.disfrazitos.Adaptadores.CategoriaAdapter;
import com.example.disfrazitos.Entidades.Categorias;
import com.example.disfrazitos.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class InicioClienteFragment extends Fragment {
    LinearLayout accessibility_action_clickable_span;
    RecyclerView categoriaRecycler;
    CategoriaAdapter categoriaAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_inicio_cliente, container, false);
        accessibility_action_clickable_span = vista.findViewById(R.id.accessibility_action_clickable_span);

        categoriaRecycler=vista.findViewById(R.id.recycler_categoria);
        categoriaRecycler.setHasFixedSize(true);
        categoriaRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        FirebaseRecyclerOptions<Categorias> opcionesCategoria=
                new FirebaseRecyclerOptions.Builder<Categorias>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("CATEGORIAS"),Categorias.class)
                .build();

        categoriaAdapter=new CategoriaAdapter(opcionesCategoria);
        categoriaRecycler.setAdapter(categoriaAdapter);

        accessibility_action_clickable_span.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction= getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_cliente, new ShowDisfrazFragment());
                transaction.commit();
            }
        });
        return vista;
    }

    @Override
    public void onStart() {
        super.onStart();
        categoriaAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        categoriaAdapter.stopListening();
    }
}