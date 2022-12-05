package com.example.disfrazitos.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.disfrazitos.Entidades.Disfraz;
import com.example.disfrazitos.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class DisfrazAdapter extends FirebaseRecyclerAdapter<Disfraz,DisfrazAdapter.DisfrazHolder> {
    private EventListener eventListenerDetalleDisfraz;

    public interface EventListener {
        void onEventDisfrazDetalle(String imagen,String nombre,String descripcion,String talla,int cantidad,float precio);
    }
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public DisfrazAdapter(@NonNull @NotNull FirebaseRecyclerOptions<Disfraz> options, CategoriaAdapter.EventListener eventListener) {
        super(options);
        this.eventListenerDetalleDisfraz = (EventListener) eventListener;
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull DisfrazAdapter.DisfrazHolder disfrazHolder, int position, @NonNull @NotNull Disfraz disfraz) {
        String sImg= disfraz.getImg_disfraz();
        String sNom= disfraz.getNom_disfraz();
        String sDesc= disfraz.getDesc_disfraz();
        String sTall= disfraz.getTall_disfraz();
        int iCant=disfraz.getCant_disfraz();
        float fPrec=disfraz.getPrec_disfraz();
        String sCant= "Stock: "+disfraz.getCant_disfraz()+" Unidades";
        String sPrec= "S/. "+disfraz.getPrec_disfraz();

        disfrazHolder.txt_nombre_disfraz_item.setText(sNom);
        disfrazHolder.txt_stock_disfraz_item.setText(sCant);
        disfrazHolder.txt_precio_disfraz_item.setText(sPrec);

        try{
            Picasso.get().load(sImg).placeholder(R.drawable.categoria).into(disfrazHolder.img_disfraz_item);
        }catch (Exception e){
            Picasso.get().load(R.drawable.categoria).into(disfrazHolder.img_disfraz_item);
        }

        disfrazHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventListenerDetalleDisfraz.onEventDisfrazDetalle(sImg,sNom,sDesc,sTall,iCant,fPrec);
            }
        });
    }

    @NonNull
    @NotNull
    @Override
    public DisfrazAdapter.DisfrazHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_disfraz,parent,false);
        return new DisfrazHolder(v);
    }

    public class DisfrazHolder extends RecyclerView.ViewHolder {
        ImageView img_disfraz_item;
        TextView txt_nombre_disfraz_item,txt_stock_disfraz_item,txt_precio_disfraz_item;
        public DisfrazHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            img_disfraz_item=itemView.findViewById(R.id.img_disfraz_item);
            txt_nombre_disfraz_item=itemView.findViewById(R.id.txt_nombre_disfraz_item);
            txt_stock_disfraz_item=itemView.findViewById(R.id.txt_stock_disfraz_item);
            txt_precio_disfraz_item=itemView.findViewById(R.id.txt_precio_disfraz_item);

        }
    }
}
