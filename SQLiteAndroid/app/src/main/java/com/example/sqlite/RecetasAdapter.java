package com.example.sqlite;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sqlite.R;
import com.example.sqlite.Receta;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RecetasAdapter extends RecyclerView.Adapter<RecetasAdapter.RecetaViewHolder> {

    private List<Receta> listaRecetas;
    private OnRecetaSeleccionadaListener listener;
    private Receta recetaSeleccionada; // Variable para almacenar la receta seleccionada

    public RecetasAdapter(List<Receta> listaRecetas) {
        this.listaRecetas = listaRecetas;
    }

    public void setOnRecetaSeleccionadaListener(OnRecetaSeleccionadaListener listener) {
        this.listener = listener;
    }

    public void setRecetaSeleccionada(Receta receta) {
        this.recetaSeleccionada = receta;
        notifyDataSetChanged(); // Notifica al adaptador para que actualice la vista
    }

    @NonNull
    @Override
    public RecetaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receta, parent, false);
        return new RecetaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecetaViewHolder holder, int position) {
        Receta receta = listaRecetas.get(position);
        holder.nombre.setText(receta.getNombre());
        holder.ingredientes.setText(receta.getIngredientes());
        holder.preparacion.setText(receta.getPreparacion());

        // Cambia el color de fondo del elemento seleccionado
        if (recetaSeleccionada != null && recetaSeleccionada.equals(receta)) {
            holder.itemView.setBackgroundColor(Color.parseColor("#B950D6")); // Color amarillo para resaltar
            // Cambia el color del texto a negro para resaltar el elemento seleccionado
            holder.nombre.setTextColor(Color.WHITE);
            holder.ingredientes.setTextColor(Color.WHITE);
            holder.preparacion.setTextColor(Color.WHITE);
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT); // Color transparente para los elementos no seleccionados
            // Cambia el color del texto a gris para los elementos no seleccionados
            holder.nombre.setTextColor(Color.WHITE);
            holder.ingredientes.setTextColor(Color.GRAY);
            holder.preparacion.setTextColor(Color.GRAY);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = holder.getAdapterPosition();
                if (listener != null && currentPosition != RecyclerView.NO_POSITION) {
                    listener.onRecetaSeleccionada(listaRecetas.get(currentPosition), currentPosition);
                    recetaSeleccionada = listaRecetas.get(currentPosition); // Actualiza la receta seleccionada
                    notifyDataSetChanged(); // Notifica al adaptador para que actualice la vista
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaRecetas.size();
    }

    public static class RecetaViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, ingredientes, preparacion;

        public RecetaViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombreReceta);
            ingredientes = itemView.findViewById(R.id.ingredientesReceta);
            preparacion = itemView.findViewById(R.id.preparacionReceta);
        }
    }

    public interface OnRecetaSeleccionadaListener {
        void onRecetaSeleccionada(Receta receta, int position);
    }

    // Método para filtrar las recetas basándose en el texto de búsqueda
    public void filtrar(String textoBusqueda) {
        // Lista temporal para almacenar las recetas filtradas
        List<Receta> listaFiltrada = new ArrayList<>();

        // Si el texto de búsqueda está vacío, añade todas las recetas a la lista filtrada
        if (textoBusqueda.isEmpty()) {
            listaFiltrada.addAll(listaRecetas);
        } else {
            textoBusqueda = textoBusqueda.toLowerCase(Locale.getDefault());
            for (Receta receta : listaRecetas) {
                if (receta.getNombre().toLowerCase(Locale.getDefault()).contains(textoBusqueda)) {
                    listaFiltrada.add(receta);
                }
            }
        }

        // Actualiza la lista de recetas con la lista filtrada
        listaRecetas.clear();
        listaRecetas.addAll(listaFiltrada);

        // Notifica al RecyclerView para que se actualice
        notifyDataSetChanged();
    }

}
