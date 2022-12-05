package com.example.disfrazitos.FragmentosRepartidor;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.disfrazitos.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

public class MapsEnCaminoFragment extends Fragment {
    Button btn_entregar_disfraz;
    TextView txt_id_pedido_maps,txt_precioTotal_pedido_maps,txt_cliente_pedido_maps,txt_direccion_pedido_maps,txt_referencia_pedido_maps;

    Bundle datosRecuperados;

    Boolean actualPosition=true;
    JSONObject jso;
    Double longitudOrigen,latitudOrigen;

    //datos recuperados
    int id_pedido, cantidaComprar_disfraz;
    String pid, cliente_pedido, telefono_pedido, direccion_pedido, referencia_pedido, puntoEntrega_pedido, estado_pedido,
            fecha_pedido, imagen_disfraz, nombre_disfraz, descripcion_disfraz, talla_disfraz;
    double latitud_pedido, longitud_pedido;
    float precioTotal_disfraz;
    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
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

            txt_id_pedido_maps.setText("Orden #"+id_pedido);
            txt_precioTotal_pedido_maps.setText("S/. "+precioTotal_disfraz);
            txt_cliente_pedido_maps.setText(cliente_pedido);
            txt_direccion_pedido_maps.setText(direccion_pedido);
            txt_referencia_pedido_maps.setText(referencia_pedido);

            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

            LatLng pedido = new LatLng(latitud_pedido, longitud_pedido);
            googleMap.addMarker(new MarkerOptions().position(pedido).title("Cliente: " + cliente_pedido));
            CameraPosition cameraPosition = CameraPosition.builder().target(pedido).zoom(15).build();
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.ACCESS_COARSE_LOCATION)){

                }else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},1);
                }

                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.ACCESS_FINE_LOCATION)){

                }else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
                }
                return;
            }
            googleMap.setMyLocationEnabled(true);
            googleMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                @Override
                public void onMyLocationChange(@NonNull @NotNull Location location) {
                    if(actualPosition){
                        latitudOrigen=location.getLatitude();
                        longitudOrigen= location.getLongitude();
                        actualPosition=false;

                        LatLng miUbicacion=new LatLng(latitudOrigen,longitudOrigen);
                    }
                }
            });
        }


    };

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case 1:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                }  else {
                    // Explain to the user that the feature is unavailable because
                    // the feature requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                }
                return;
        }
        // Other 'case' lines to check for other
        // permissions this app might request.
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vista=inflater.inflate(R.layout.fragment_maps_en_camino, container, false);
        btn_entregar_disfraz=vista.findViewById(R.id.btn_entregar_disfraz);
        txt_id_pedido_maps=vista.findViewById(R.id.txt_id_pedido_maps);
        txt_precioTotal_pedido_maps=vista.findViewById(R.id.txt_precioTotal_pedido_maps);
        txt_cliente_pedido_maps=vista.findViewById(R.id.txt_cliente_pedido_maps);
        txt_direccion_pedido_maps=vista.findViewById(R.id.txt_direccion_pedido_maps);
        txt_referencia_pedido_maps=vista.findViewById(R.id.txt_referencia_pedido_maps);



        btn_entregar_disfraz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "hsgdbwuidgbuwegbfd", Toast.LENGTH_SHORT).show();
                //FotoEntregaFragment()
            }
        });
        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}