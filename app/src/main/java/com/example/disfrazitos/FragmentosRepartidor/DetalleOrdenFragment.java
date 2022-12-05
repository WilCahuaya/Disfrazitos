package com.example.disfrazitos.FragmentosRepartidor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.disfrazitos.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class DetalleOrdenFragment extends Fragment {
    Button btn_iniciar_entrega;
    Bundle datosRecuperados;
    TextView txt_id_pedido,txt_nombre_disfraz,txt_cantidaComprar_disfraz,txt_precioTotal_disfraz,txt_cliente_pedido,
            txt_direccion_pedido,txt_referencia_pedido,txt_puntoEntrega_pedido,txt_fecha_pedido;
    ImageView img_imagen_disfraz;

    private DatabaseReference mDatabase;

    //datos recuperados
    int id_pedido,cantidaComprar_disfraz;
    String pid,cliente_pedido, telefono_pedido, direccion_pedido,referencia_pedido, puntoEntrega_pedido,estado_pedido,
            fecha_pedido,imagen_disfraz,nombre_disfraz,descripcion_disfraz, talla_disfraz;
    double latitud_pedido,longitud_pedido;
    float precioTotal_disfraz;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_detalle_orden, container, false);

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        mDatabase=database.getReference();

        btn_iniciar_entrega=vista.findViewById(R.id.btn_iniciar_entrega);

        txt_id_pedido=vista.findViewById(R.id.txt_id_pedido);
        txt_nombre_disfraz=vista.findViewById(R.id.txt_nombre_disfraz);
        txt_cantidaComprar_disfraz=vista.findViewById(R.id.txt_cantidaComprar_disfraz);
        txt_precioTotal_disfraz=vista.findViewById(R.id.txt_precioTotal_disfraz);
        txt_cliente_pedido=vista.findViewById(R.id.txt_cliente_pedido);
        txt_direccion_pedido=vista.findViewById(R.id.txt_direccion_pedido);
        txt_referencia_pedido=vista.findViewById(R.id.txt_referencia_pedido);
        txt_puntoEntrega_pedido=vista.findViewById(R.id.txt_puntoEntrega_pedido);
        txt_fecha_pedido=vista.findViewById(R.id.txt_fecha_pedido);
        img_imagen_disfraz=vista.findViewById(R.id.img_imagen_disfraz);

        datosRecuperados = getArguments();
        if (datosRecuperados == null) {
            try {

            }catch (Exception e){
                Toast.makeText(getContext(), "No hay conexion"+e, Toast.LENGTH_SHORT).show();
            }
        }
        pid = datosRecuperados.getString("pid");
        id_pedido = datosRecuperados.getInt("id_pedido");
        cliente_pedido = datosRecuperados.getString("cliente_pedido");
        telefono_pedido = datosRecuperados.getString("telefono_pedido");
        direccion_pedido = datosRecuperados.getString("direccion_pedido");
        referencia_pedido = datosRecuperados.getString("referencia_pedido");
        puntoEntrega_pedido = datosRecuperados.getString("puntoEntrega_pedido");
        latitud_pedido = datosRecuperados.getDouble("latitud_pedido");
        longitud_pedido = datosRecuperados.getDouble("longitud_pedido");
        estado_pedido = datosRecuperados.getString("estado_pedido");
        fecha_pedido = datosRecuperados.getString("fecha_pedido");
        imagen_disfraz = datosRecuperados.getString("imagen_disfraz");
        nombre_disfraz = datosRecuperados.getString("nombre_disfraz");
        descripcion_disfraz = datosRecuperados.getString("descripcion_disfraz");
        talla_disfraz = datosRecuperados.getString("talla_disfraz");
        cantidaComprar_disfraz = datosRecuperados.getInt("cantidaComprar_disfraz");
        precioTotal_disfraz = datosRecuperados.getFloat("precioTotal_disfraz");

        txt_id_pedido.setText("Orden #"+id_pedido);
        try{
            Picasso.get().load(imagen_disfraz).placeholder(R.drawable.categoria).into(img_imagen_disfraz);
        }catch (Exception e){
            Picasso.get().load(R.drawable.categoria).into(img_imagen_disfraz);
        }

        txt_nombre_disfraz.setText(nombre_disfraz);
        txt_cantidaComprar_disfraz.setText(cantidaComprar_disfraz+" Unidades");
        txt_precioTotal_disfraz.setText("Total: S/. "+precioTotal_disfraz);
        txt_cliente_pedido.setText(cliente_pedido);
        txt_direccion_pedido.setText(direccion_pedido);
        txt_referencia_pedido.setText(referencia_pedido);
        txt_puntoEntrega_pedido.setText(puntoEntrega_pedido);
        txt_fecha_pedido.setText(fecha_pedido);


        btn_iniciar_entrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String estado_pedido_actual="ENCAMINO";
                Map<String,Object>map= new HashMap<>();
                map.put("estado_pedido",estado_pedido_actual);

                mDatabase.child("Pedidos").child(pid).updateChildren(map);

                FragmentTransaction transaction= getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_repartidor, new EnCaminoRepartidorFragment());
                transaction.commit();
            }
        });
        return vista;
    }
}