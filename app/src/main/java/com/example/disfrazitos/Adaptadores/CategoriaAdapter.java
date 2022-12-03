package com.example.disfrazitos.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.disfrazitos.Entidades.Categorias;
import com.example.disfrazitos.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class CategoriaAdapter extends FirebaseRecyclerAdapter<Categorias,CategoriaAdapter.CategoriaHolder> {
    String imagen;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CategoriaAdapter(@NonNull @NotNull FirebaseRecyclerOptions<Categorias> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull CategoriaAdapter.CategoriaHolder categoriaHolder, int position, @NonNull @NotNull Categorias categoria) {
        imagen= categoria.getImg_categoria();
        categoriaHolder.nombreCategoria.setText(categoria.getNom_categoria());
        try {
            Picasso.get().load(imagen).placeholder(R.drawable.categoria).into(categoriaHolder.imageCategoria);
        }catch (Exception e){
            Picasso.get().load(R.drawable.categoria).into(categoriaHolder.imageCategoria);
        }

        categoriaHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), categoria.getNom_categoria(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @NonNull
    @NotNull
    @Override
    public CategoriaAdapter.CategoriaHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.categorias_disponibles,parent,false);
        return new CategoriaHolder(v);
    }

    public class CategoriaHolder extends RecyclerView.ViewHolder {
        TextView nombreCategoria;
        ImageView imageCategoria;
        public CategoriaHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            nombreCategoria=itemView.findViewById(R.id.txt_categoria);
            imageCategoria=itemView.findViewById(R.id.img_categoria);
        }
    }
}
