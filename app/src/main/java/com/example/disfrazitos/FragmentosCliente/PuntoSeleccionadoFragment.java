package com.example.disfrazitos.FragmentosCliente;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.disfrazitos.Entidades.Pedido;
import com.example.disfrazitos.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import static com.example.disfrazitos.Util.NOMBRE_CLIENTE;
import static com.example.disfrazitos.Util.NUMERO_PEDIDO;

public class PuntoSeleccionadoFragment extends Fragment {
    Button btn_selecionar_punto;
    EditText edt_punto_referencia,edt_referencia,edt_direccion;

    Bundle datosRecuperados;

    //datos recuperados
    String imagen;
    String nombre;
    String descripcion;
    String talla;
    int stock;
    float precio;
    int cantidadComprar;
    float precioTotal;
    String direccion;
    String referencia;
    double latitud;
    double longitud;

    private DatabaseReference mDatabase;
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

        FirebaseApp.initializeApp(getContext());
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        mDatabase=database.getReference();

        datosRecuperados = getArguments();
        if (datosRecuperados == null) {
            try {

            }catch (Exception e){
                Toast.makeText(getContext(), "No hay conexion"+e, Toast.LENGTH_SHORT).show();
            }
        }


        imagen = datosRecuperados.getString("imagen");
        nombre = datosRecuperados.getString("nombre");
        descripcion = datosRecuperados.getString("descripcion");
        talla = datosRecuperados.getString("talla");
        stock = datosRecuperados.getInt("stock");
        precio = datosRecuperados.getFloat("precio");
        cantidadComprar = datosRecuperados.getInt("cantidadComprar");
        precioTotal = datosRecuperados.getFloat("precioTotal");
        direccion = datosRecuperados.getString("direccion");
        referencia = datosRecuperados.getString("referencia");
        latitud = datosRecuperados.getDouble("latitud");
        longitud = datosRecuperados.getDouble("longitud");
        edt_direccion.setText(direccion);
        edt_referencia.setText(referencia);

        //convertir coordenadas en direcciones
        Geocoder geocoder=new Geocoder(getContext(), Locale.getDefault());
        try {
            List<Address> puntoDireccion=geocoder.getFromLocation(latitud,longitud,1);
            edt_punto_referencia.setText(puntoDireccion.get(0).getAddressLine(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //edt_punto_referencia.setText("Av. Los Alisales, Pasto, Nariño #3");

        btn_selecionar_punto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarPedido();
                FragmentTransaction transaction= getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_cliente, new PagoFragment());
                transaction.commit();
            }
        });

        return vista;
    }

    private void registrarPedido() {
        int id_pedido=NUMERO_PEDIDO+1;
        NUMERO_PEDIDO=id_pedido;
        String cliente_pedido=NOMBRE_CLIENTE;
        String direccion_pedido=direccion;
        String referencia_pedido=referencia;
        String puntoEntrega_pedido=edt_punto_referencia.getText().toString();
        double latitud_pedido=latitud;
        double longitud_pedido=longitud;
        String estado_pedido="DESPACHADO";
        String photo_pedido="";

        //OPTENER FECHA
        Calendar cal = new GregorianCalendar();
        Date date = cal.getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String formatteDate = df.format(date);

        String fecha_pedido=formatteDate+" ";
        String imagen_disfraz=imagen;
        String nombre_disfraz=nombre;
        String descripcion_disfraz=descripcion;
        String talla_disfraz=talla;
        int stock_disfraz=stock;
        float precio_disfraz=precio;
        int cantidadComprar_disfraz=cantidadComprar;
        float precioTotal_disfraz=precioTotal;

        Pedido pedido=new Pedido(id_pedido,cliente_pedido,direccion_pedido,referencia_pedido,
                puntoEntrega_pedido,latitud_pedido,longitud_pedido,estado_pedido,photo_pedido,
                fecha_pedido,imagen_disfraz,nombre_disfraz,descripcion_disfraz,talla_disfraz,
                stock_disfraz,precio_disfraz,cantidadComprar_disfraz,precioTotal_disfraz);
        String id=mDatabase.push().getKey();
        mDatabase.child("Pedidos").child(id).setValue(pedido);

        Toast.makeText(getContext(), "Registro Exitoso", Toast.LENGTH_SHORT).show();
    }
}