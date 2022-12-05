package com.example.disfrazitos.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.disfrazitos.Entidades.Pedido;
import com.example.disfrazitos.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.jetbrains.annotations.NotNull;

public class PedidosAdapter extends FirebaseRecyclerAdapter<Pedido,PedidosAdapter.PedidosHolder> {
    private PedidosAdapter.EventListener eventListenerDetallePedido;

    public interface EventListener {
        void onEventPedidoDetalle(String pid,int id_pedido,String cliente_pedido,String telefono_pedido,String direccion_pedido,String referencia_pedido,
                                   String puntoEntrega_pedido,double latitud_pedido,double longitud_pedido,String estado_pedido,
                                   String fecha_pedido,String imagen_disfraz,String nombre_disfraz,String descripcion_disfraz,
                                   String talla_disfraz,int cantidaComprar_disfraz,float precioTotal_disfraz);
    }
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PedidosAdapter(@NonNull @NotNull FirebaseRecyclerOptions<Pedido> options, PedidosAdapter.EventListener eventListener) {
        super(options);
        this.eventListenerDetallePedido = (PedidosAdapter.EventListener) eventListener;
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull PedidosHolder pedidosHolder, int position, @NonNull @NotNull Pedido pedido) {
        String pid= pedido.getPid();
        int id_pedido= pedido.getId_pedido();
        String cliente_pedido= pedido.getCliente_pedido();
        String telefono_pedido=pedido.getTelefono_pedido();
        String direccion_pedido=pedido.getDireccion_pedido();
        String referencia_pedido=pedido.getReferencia_pedido();
        String puntoEntrega_pedido=pedido.getPuntoEntrega_pedido();
        double latitud_pedido=pedido.getLatitud_pedido();
        double longitud_pedido=pedido.getLongitud_pedido();
        String estado_pedido=pedido.getEstado_pedido();
        String fecha_pedido=pedido.getFecha_pedido();
        String imagen_disfraz=pedido.getImagen_disfraz();
        String nombre_disfraz=pedido.getNombre_disfraz();
        String descripcion_disfraz=pedido.getDescripcion_disfraz();
        String talla_disfraz=pedido.getTalla_disfraz();
        int cantidaComprar_disfraz=pedido.getCantidadComprar_disfraz();
        float precioTotal_disfraz=pedido.getPrecioTotal_disfraz();
        pedidosHolder.txt_numero_pedido.setText("Orden #"+id_pedido);
        pedidosHolder.txt_nombre_cliente_pedido.setText(cliente_pedido);
        pedidosHolder.txt_destino_pedido.setText(puntoEntrega_pedido);
        pedidosHolder.txt_fecha_pedido.setText(fecha_pedido);

        pedidosHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), ""+pid, Toast.LENGTH_SHORT).show();
                eventListenerDetallePedido.onEventPedidoDetalle(pid,id_pedido,cliente_pedido,telefono_pedido,direccion_pedido,referencia_pedido,puntoEntrega_pedido, latitud_pedido,longitud_pedido,estado_pedido,fecha_pedido,imagen_disfraz,nombre_disfraz,descripcion_disfraz,talla_disfraz,cantidaComprar_disfraz, precioTotal_disfraz);
            }
        });
    }

    @NonNull
    @NotNull
    @Override
    public PedidosHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pedido,parent,false);
        return new PedidosHolder(vista);
    }

    public class PedidosHolder extends RecyclerView.ViewHolder {
        TextView txt_numero_pedido,txt_nombre_cliente_pedido,txt_destino_pedido,txt_fecha_pedido;
        public PedidosHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txt_numero_pedido=itemView.findViewById(R.id.txt_numero_pedido);
            txt_nombre_cliente_pedido=itemView.findViewById(R.id.txt_nombre_cliente_pedido);
            txt_destino_pedido=itemView.findViewById(R.id.txt_destino_pedido);
            txt_fecha_pedido=itemView.findViewById(R.id.txt_fecha_pedido);
        }
    }
}
