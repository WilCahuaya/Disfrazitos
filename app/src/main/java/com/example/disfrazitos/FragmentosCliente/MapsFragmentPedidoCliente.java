package com.example.disfrazitos.FragmentosCliente;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.disfrazitos.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jetbrains.annotations.NotNull;

public class MapsFragmentPedidoCliente extends Fragment {
    private GoogleMap mMap;

    Bundle datosRecuperados;
    //private ActivityFragmentMapsBinding binding;

    //Varibles de latitud y longitud
    double mLat,mlong;

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
            mMap = googleMap;

            datosRecuperados = getArguments();
            if (datosRecuperados == null) {
                try {

                }catch (Exception e){
                    Toast.makeText(getContext(), "No hay conexion"+e, Toast.LENGTH_SHORT).show();
                }
            }


            String imagen = datosRecuperados.getString("imagen");
            String nombre = datosRecuperados.getString("nombre");
            String descripcion = datosRecuperados.getString("descripcion");
            String talla = datosRecuperados.getString("talla");
            int stock = datosRecuperados.getInt("stock");
            float precio = datosRecuperados.getFloat("precio");
            int cantidadComprar = datosRecuperados.getInt("cantidadComprar");
            float precioTotal = datosRecuperados.getFloat("precioTotal");
            String direccion = datosRecuperados.getString("direccion");
            String referencia = datosRecuperados.getString("referencia");

            // Add a marker in Sydney and move the camera
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            LatLng hyo = new LatLng(-12.068148340882951, -75.21002132643184);
            mMap.addMarker(new MarkerOptions().position(hyo).title("Huancayo - Perù"));
            CameraPosition cameraPosition=CameraPosition.builder().target(hyo).zoom(15).build();
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            //Capturar latitud y longitud de un punto en especifico
            mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(@NonNull @NotNull LatLng latLng) {
                    mMap.addMarker(new MarkerOptions()
                            //.icon(BitmapDescriptorFactory.fromResource(R.drawable.repartidor))
                            .title("Punto")
                            .snippet("Punto de entrega")
                            .position(latLng)
                            .anchor(0.5F,0.5f));
                    mLat=latLng.latitude;
                    mlong=latLng.longitude;

                    //Crear bundle, que son los datos que pasaremos
                    Bundle datosAEnviar = new Bundle();
                    // Aquí pon todos los datos que quieras en formato clave, valor
                    datosAEnviar.putString("imagen", imagen);
                    datosAEnviar.putString("nombre", nombre);
                    datosAEnviar.putString("descripcion", descripcion);
                    datosAEnviar.putString("talla", talla);
                    datosAEnviar.putInt("stock", stock);
                    datosAEnviar.putFloat("precio", precio);
                    datosAEnviar.putInt("cantidadComprar", cantidadComprar);
                    datosAEnviar.putFloat("precioTotal", precioTotal);
                    datosAEnviar.putString("direccion", direccion);
                    datosAEnviar.putString("referencia", referencia);
                    datosAEnviar.putDouble("latitud", mLat);
                    datosAEnviar.putDouble("longitud", mlong);

                    Fragment fragmento = new PuntoSeleccionadoFragment();
                    // ¡Importante! darle argumentos
                    fragmento.setArguments(datosAEnviar);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container_cliente, fragmento);
                    fragmentTransaction.addToBackStack(null);

                    // Terminar transición y nos vemos en el fragmento de destino
                    fragmentTransaction.commit();
                }
            });

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vista=inflater.inflate(R.layout.fragment_maps_pedido_cliente, container, false);

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