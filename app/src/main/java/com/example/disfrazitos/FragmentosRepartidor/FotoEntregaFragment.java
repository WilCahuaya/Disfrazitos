package com.example.disfrazitos.FragmentosRepartidor;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.disfrazitos.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

public class FotoEntregaFragment extends Fragment {
Button btn_entregado, btn_tomar_foto_entrega;
ImageView img_foto_tomada;

    Bundle datosRecuperados;

    //datos recuperados
    int id_pedido, cantidaComprar_disfraz;
    String pid, cliente_pedido, telefono_pedido, direccion_pedido, referencia_pedido, puntoEntrega_pedido, estado_pedido,
            fecha_pedido, imagen_disfraz, nombre_disfraz, descripcion_disfraz, talla_disfraz;
    double latitud_pedido, longitud_pedido;
    float precioTotal_disfraz;

    private DatabaseReference mDatabase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viste= inflater.inflate(R.layout.fragment_foto_entrega, container, false);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        mDatabase=database.getReference();

        btn_entregado=viste.findViewById(R.id.btn_entregado);
        btn_tomar_foto_entrega=viste.findViewById(R.id.btn_tomar_foto_entrega);
        img_foto_tomada=viste.findViewById(R.id.img_foto_tomada);

        datosRecuperados = getArguments();
        if (datosRecuperados == null) {
            try {

            } catch (Exception e) {
                Toast.makeText(getContext(), "No hay conexion" + e, Toast.LENGTH_SHORT).show();
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

        btn_tomar_foto_entrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCamara();

            }
        });

        btn_entregado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String estado_pedido_actual="ENTREGADO";
                Map<String,Object> map= new HashMap<>();
                map.put("estado_pedido",estado_pedido_actual);

                mDatabase.child("Pedidos").child(pid).updateChildren(map);
                FragmentTransaction transaction= getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_repartidor, new EntregadosRepartidorFragment());
                transaction.commit();
            }
        });

        return viste;
    }

    private void abrirCamara() {

        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK){
            Bundle extras=data.getExtras();
            Bitmap imgBitmap= (Bitmap) extras.get("data");
            img_foto_tomada.setImageBitmap(imgBitmap);
        }
    }
}