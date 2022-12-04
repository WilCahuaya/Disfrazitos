package com.example.disfrazitos.FragmentosCliente;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.disfrazitos.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ShowDisfrazFragment extends Fragment {
    Button btn_alquilar;
    Bundle datosRecuperados;
    ImageView img_disfraz_detalle;
    TextView txt_nombre_disfraz_detalle,txt_talla_disfraz_detalle,txt_precio_disfraz_detalle,txt_descripcion_disfraz_detalle,txt_preciototal_disfraz_detalle,txt_cantidad_selecionada_disfraz;
    LinearLayout linLay_mas_uno_detalle,linLay_menos_uno_detalle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista=inflater.inflate(R.layout.fragment_show_disfraz, container, false);

        btn_alquilar = vista.findViewById(R.id.btn_alquilar);

        img_disfraz_detalle=vista.findViewById(R.id.img_disfraz_detalle);
        txt_nombre_disfraz_detalle=vista.findViewById(R.id.txt_nombre_disfraz_detalle);
        txt_talla_disfraz_detalle=vista.findViewById(R.id.txt_talla_disfraz_detalle);
        txt_precio_disfraz_detalle=vista.findViewById(R.id.txt_precio_disfraz_detalle);
        txt_descripcion_disfraz_detalle=vista.findViewById(R.id.txt_descripcion_disfraz_detalle);
        txt_preciototal_disfraz_detalle=vista.findViewById(R.id.txt_preciototal_disfraz_detalle);

        txt_cantidad_selecionada_disfraz=vista.findViewById(R.id.txt_cantidad_selecionada_disfraz);

        linLay_mas_uno_detalle=vista.findViewById(R.id.linLay_mas_uno_detalle);
        linLay_menos_uno_detalle=vista.findViewById(R.id.linLay_menos_uno_detalle);


        datosRecuperados = getArguments();
        if (datosRecuperados == null) {
            try {

            }catch (Exception e){
                Toast.makeText(getContext(), "No ha conexion"+e, Toast.LENGTH_SHORT).show();
            }
        }


        String imagen = datosRecuperados.getString("imagen");
        String nombre = datosRecuperados.getString("nombre");
        String descripcion = datosRecuperados.getString("descripcion");
        String talla = datosRecuperados.getString("talla");
        int cantidad = datosRecuperados.getInt("cantidad");
        float precio = datosRecuperados.getFloat("precio");

        try{
            Picasso.get().load(imagen).placeholder(R.drawable.categoria).into(img_disfraz_detalle);
        }catch (Exception e){
            Picasso.get().load(R.drawable.categoria).into(img_disfraz_detalle);
        }
        txt_nombre_disfraz_detalle.setText(nombre);
        txt_talla_disfraz_detalle.setText(talla);
        txt_precio_disfraz_detalle.setText("S/. "+precio);
        txt_descripcion_disfraz_detalle.setText(descripcion);
        float precioTotal=1*precio;
        txt_preciototal_disfraz_detalle.setText("Total a pagar: "+precioTotal);

        linLay_mas_uno_detalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cantidadComprar=Integer.parseInt((String) txt_cantidad_selecionada_disfraz.getText());
                cantidadComprar=cantidadComprar+1;
                txt_cantidad_selecionada_disfraz.setText(String.valueOf(cantidadComprar));
                DecimalFormat df = new DecimalFormat("###.##");
                String precioTotal= df.format(cantidadComprar*precio);
                txt_preciototal_disfraz_detalle.setText("Total a pagar: "+precioTotal);
            }
        });
        linLay_mas_uno_detalle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int cantidadComprar=Integer.parseInt((String) txt_cantidad_selecionada_disfraz.getText());
                cantidadComprar=cantidadComprar+5;
                txt_cantidad_selecionada_disfraz.setText(String.valueOf(cantidadComprar));
                DecimalFormat df = new DecimalFormat("###.##");
                String precioTotal= df.format(cantidadComprar*precio);
                txt_preciototal_disfraz_detalle.setText("Total a pagar: "+precioTotal+" soles");
                return true;
            }
        });
        linLay_menos_uno_detalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cantidadComprar=Integer.parseInt((String) txt_cantidad_selecionada_disfraz.getText());
                if (cantidadComprar<=1){
                    Toast.makeText(getContext(), "La cantidad no puede ser menor que uno", Toast.LENGTH_SHORT).show();
                    cantidadComprar=1;
                }else {
                    cantidadComprar=cantidadComprar-1;
                }
                txt_cantidad_selecionada_disfraz.setText(String.valueOf(cantidadComprar));
                DecimalFormat df = new DecimalFormat("###.##");
                String precioTotal= df.format(cantidadComprar*precio);
                txt_preciototal_disfraz_detalle.setText("Total a pagar: "+precioTotal);
            }
        });
        linLay_menos_uno_detalle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int cantidadComprar=Integer.parseInt((String) txt_cantidad_selecionada_disfraz.getText());
                if (cantidadComprar<=5){
                    Toast.makeText(getContext(), "La cantidad no puede ser menor que uno", Toast.LENGTH_SHORT).show();
                    cantidadComprar=1;
                }else {
                    cantidadComprar=cantidadComprar-5;
                }
                txt_cantidad_selecionada_disfraz.setText(String.valueOf(cantidadComprar));
                DecimalFormat df = new DecimalFormat("###.##");
                String precioTotal= df.format(cantidadComprar*precio);
                txt_preciototal_disfraz_detalle.setText("Total a pagar: "+precioTotal);
                return true;
            }
        });

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