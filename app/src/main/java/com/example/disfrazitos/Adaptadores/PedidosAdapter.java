package com.example.disfrazitos.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.disfrazitos.Entidades.Pedido;
import com.example.disfrazitos.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.jetbrains.annotations.NotNull;

public class PedidosAdapter extends FirebaseRecyclerAdapter<Pedido,PedidosAdapter.PedidosHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PedidosAdapter(@NonNull @NotNull FirebaseRecyclerOptions<Pedido> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull PedidosHolder pedidosHolder, int position, @NonNull @NotNull Pedido pedido) {
        int numeroOrden=pedido.getId_pedido();
        String clienteOrden= pedido.getCliente_pedido();
        String destinoOrden= pedido.getPuntoEntrega_pedido();
        String fechaOrden= pedido.getFecha_pedido();

        pedidosHolder.txt_numero_pedido.setText("Orden #"+numeroOrden);
        pedidosHolder.txt_nombre_cliente_pedido.setText(clienteOrden);
        pedidosHolder.txt_destino_pedido.setText(destinoOrden);
        pedidosHolder.txt_fecha_pedido.setText(fechaOrden);
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
