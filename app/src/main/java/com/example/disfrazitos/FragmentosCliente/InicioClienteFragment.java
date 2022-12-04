package com.example.disfrazitos.FragmentosCliente;

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

import com.example.disfrazitos.Adaptadores.CategoriaAdapter;
import com.example.disfrazitos.Adaptadores.DisfrazAdapter;
import com.example.disfrazitos.Entidades.Categorias;
import com.example.disfrazitos.Entidades.Disfraz;
import com.example.disfrazitos.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class InicioClienteFragment extends Fragment implements CategoriaAdapter.EventListener,DisfrazAdapter.EventListener{
    //LinearLayout accessibility_action_clickable_span;
    RecyclerView categoriaRecycler,disfrazRecycler;
    CategoriaAdapter categoriaAdapter;
    DisfrazAdapter disfrazAdapter;
    String BD_CATEGORIA_FIREBASE;
    TextView txt_nombre_disfraz_selecionado;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_inicio_cliente, container, false);
        txt_nombre_disfraz_selecionado=vista.findViewById(R.id.txt_nombre_disfraz_selecionado);
        //accessibility_action_clickable_span = vista.findViewById(R.id.accessibility_action_clickable_span);

        disfrazRecycler=vista.findViewById(R.id.recycler_disfraz);
        categoriaRecycler=vista.findViewById(R.id.recycler_categoria);

        categoriaRecycler.setHasFixedSize(true);
        categoriaRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        FirebaseRecyclerOptions<Categorias> opcionesCategoria=
                new FirebaseRecyclerOptions.Builder<Categorias>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("CATEGORIAS"),Categorias.class)
                .build();

        categoriaAdapter=new CategoriaAdapter(opcionesCategoria,this);
        categoriaRecycler.setAdapter(categoriaAdapter);



//        accessibility_action_clickable_span.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentTransaction transaction= getParentFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragment_container_cliente, new ShowDisfrazFragment());
//                transaction.commit();
//            }
//        });


        return vista;
    }

    @Override
    public void onStart() {
        super.onStart();
        categoriaAdapter.startListening();
        if(BD_CATEGORIA_FIREBASE!=null) {
            disfrazAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        categoriaAdapter.stopListening();
        if(BD_CATEGORIA_FIREBASE!=null){
            disfrazAdapter.stopListening();
        }

    }

    @Override
    public void onEventName(String nombre) {
        //NOMBRE DE LA CATEGORIA
        BD_CATEGORIA_FIREBASE=nombre;
        txt_nombre_disfraz_selecionado.setText(nombre);

        disfrazRecycler.setHasFixedSize(true);
        disfrazRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        if(BD_CATEGORIA_FIREBASE==null){
            FirebaseRecyclerOptions<Disfraz> opcionesDisfraz=
                    new FirebaseRecyclerOptions.Builder<Disfraz>()
                            .setQuery(FirebaseDatabase.getInstance().getReference("DISFRAZ_CATEGORIA").child("Animales de la Granja"),Disfraz.class)
                            .build();
            disfrazAdapter=new DisfrazAdapter(opcionesDisfraz,this);
            disfrazRecycler.setAdapter(disfrazAdapter);
            disfrazAdapter.startListening();
        }else {
            FirebaseRecyclerOptions<Disfraz> opcionesDisfraz =
                    new FirebaseRecyclerOptions.Builder<Disfraz>()
                            .setQuery(FirebaseDatabase.getInstance().getReference("DISFRAZ_CATEGORIA").child(BD_CATEGORIA_FIREBASE), Disfraz.class)
                            .build();
            disfrazAdapter=new DisfrazAdapter(opcionesDisfraz,this);
            disfrazRecycler.setAdapter(disfrazAdapter);
            disfrazAdapter.startListening();
        }
    }

    @Override
    public void onEventDisfrazDetalle(String imagen, String nombre, String descripcion, String talla, int cantidad, float precio) {
        //Crear bundle, que son los datos que pasaremos
        Bundle datosAEnviar = new Bundle();
        // Aquí pon todos los datos que quieras en formato clave, valor
        datosAEnviar.putString("imagen", imagen);
        datosAEnviar.putString("nombre", nombre);
        datosAEnviar.putString("descripcion", descripcion);
        datosAEnviar.putString("talla", talla);
        datosAEnviar.putInt("cantidad", cantidad);
        datosAEnviar.putFloat("precio", precio);

        Fragment fragmento = new ShowDisfrazFragment();
        // ¡Importante! darle argumentos
        fragmento.setArguments(datosAEnviar);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_cliente, fragmento);
        fragmentTransaction.addToBackStack(null);

        // Terminar transición y nos vemos en el fragmento de destino
        fragmentTransaction.commit();
    }
}