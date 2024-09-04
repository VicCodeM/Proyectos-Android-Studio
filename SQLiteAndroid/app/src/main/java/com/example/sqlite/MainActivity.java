package com.example.sqlite;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
//import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.widget.SearchView;


public class MainActivity extends AppCompatActivity implements RecetasAdapter.OnRecetaSeleccionadaListener {

    private RecyclerView recyclerView;
    private RecetasAdapter adapter;
    private List<Receta> listaRecetas;

    private Receta recetaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewRecetas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaRecetas = new ArrayList<>();
        // Inicializa tu lista de recetas con datos iniciales si es necesario

        adapter = new RecetasAdapter(listaRecetas);
        recyclerView.setAdapter(adapter);
        adapter.setOnRecetaSeleccionadaListener(this);

        FloatingActionButton fabAgregarReceta = findViewById(R.id.fabAgregarReceta);
        fabAgregarReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddRecetaDialog();
            }
        });

        FloatingActionButton fabActualizarReceta = findViewById(R.id.fabActualizarReceta);
        fabActualizarReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recetaSeleccionada != null) {
                    showUpdateRecetaDialog(recetaSeleccionada);
                } else {
                    Toast.makeText(MainActivity.this, "Por favor, selecciona una receta para actualizar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        FloatingActionButton fabEliminarReceta = findViewById(R.id.fabEliminarReceta);
        fabEliminarReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recetaSeleccionada != null) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("¿Desea Eliminar la Receta?")
                            .setMessage("¿Estás seguro de que quieres eliminar esta receta?")
                            .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    listaRecetas.remove(recetaSeleccionada);
                                    adapter.notifyDataSetChanged();
                                    recetaSeleccionada = null; // Resetea la receta seleccionada
                                    Toast.makeText(MainActivity.this, "Receta eliminada", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();
                } else {
                    Toast.makeText(MainActivity.this, "Por favor, selecciona una receta para eliminar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        FloatingActionButton fabBuscarReceta = findViewById(R.id.fabBuscarReceta);
        fabBuscarReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSearchDialog();
            }
        });
    }

    @Override
    public void onRecetaSeleccionada(Receta receta, int position) {
        this.recetaSeleccionada = receta;
        adapter.setRecetaSeleccionada(recetaSeleccionada); // Notifica al adaptador sobre la nueva receta seleccionada
    }

    private void showAddRecetaDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Agregar Receta");

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_receta, null);
        builder.setView(dialogView);

        final EditText inputNombre = dialogView.findViewById(R.id.inputNombre);
        final EditText inputIngredientes = dialogView.findViewById(R.id.inputIngredientes);
        final EditText inputPreparacion = dialogView.findViewById(R.id.inputPreparacion);

        builder.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nombre = inputNombre.getText().toString();
                String ingredientes = inputIngredientes.getText().toString();
                String preparacion = inputPreparacion.getText().toString();
                listaRecetas.add(new Receta(nombre, ingredientes, preparacion));
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void showUpdateRecetaDialog(Receta recetaSeleccionada) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Actualizar Receta");

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_update_receta, null);
        builder.setView(dialogView);

        final EditText inputNombre = dialogView.findViewById(R.id.inputNombreUpdate);
        final EditText inputIngredientes = dialogView.findViewById(R.id.inputIngredientesUpdate);
        final EditText inputPreparacion = dialogView.findViewById(R.id.inputPreparacionUpdate);

        inputNombre.setText(recetaSeleccionada.getNombre());
        inputIngredientes.setText(recetaSeleccionada.getIngredientes());
        inputPreparacion.setText(recetaSeleccionada.getPreparacion());

        builder.setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nuevoNombre = inputNombre.getText().toString();
                String nuevosIngredientes = inputIngredientes.getText().toString();
                String nuevaPreparacion = inputPreparacion.getText().toString();
                recetaSeleccionada.setNombre(nuevoNombre);
                recetaSeleccionada.setIngredientes(nuevosIngredientes);
                recetaSeleccionada.setPreparacion(nuevaPreparacion);
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void showSearchDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Buscar Receta");

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_search, null);
        builder.setView(dialogView);

        final SearchView searchView = dialogView.findViewById(R.id.searchView);
        final RecyclerView recyclerViewResultados = dialogView.findViewById(R.id.recyclerViewResultados);

        // Configura el RecyclerView con tu adaptador
        recyclerViewResultados.setLayoutManager(new LinearLayoutManager(this));
        RecetasAdapter adapterResultados = new RecetasAdapter(listaRecetas); // Asegúrate de tener una lista de recetas inicial
        recyclerViewResultados.setAdapter(adapterResultados);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Aquí puedes realizar la búsqueda y actualizar el RecyclerView con los resultados
                adapterResultados.filtrar(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Actualiza el RecyclerView con los resultados basados en el nuevo texto
                adapterResultados.filtrar(newText);
                return false;
            }
        });

        builder.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Actualiza el RecyclerView para mostrar todas las recetas
                adapterResultados.filtrar("");
                dialog.dismiss();
            }
        });

        builder.setCancelable(true);

        AlertDialog dialog = builder.create();
        dialog.show();

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                // Actualiza el RecyclerView para mostrar todas las recetas
                adapterResultados.filtrar("");
            }
        });
    }





}
