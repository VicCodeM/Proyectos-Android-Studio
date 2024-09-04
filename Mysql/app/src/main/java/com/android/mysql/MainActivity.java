package com.android.mysql;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private EditText editText;
    private Button btnActualizar, btnInsertar, btnEliminar;
    private List<Participante> participantes;
    private ArrayAdapter<Participante> adapter;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        editText = findViewById(R.id.editText);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnInsertar = findViewById(R.id.btnInsertar);
        btnEliminar = findViewById(R.id.btnEliminar);

        participantes = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, participantes);
        listView.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://tu_dominio.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        obtenerParticipantes();

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarParticipante();
            }
        });

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarParticipante();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarParticipante();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Participante participanteSeleccionado = participantes.get(position);
                // Aquí puedes mostrar un diálogo o un formulario para editar el participante seleccionado
            }
        });
    }

    private void obtenerParticipantes() {
        Call<List<Participante>> call = apiService.obtenerParticipantes();
        call.enqueue(new Callback<List<Participante>>() {
            @Override
            public void onResponse(Call<List<Participante>> call, Response<List<Participante>> response) {
                if (response.isSuccessful()) {
                    participantes.clear();
                    participantes.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Error al obtener participantes", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Participante>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void insertarParticipante() {
        String nombre = editText.getText().toString();
        if (!nombre.isEmpty()) {
            Call<Void> call = apiService.insertarParticipante(nombre);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        obtenerParticipantes();
                        Toast.makeText(MainActivity.this, "Participante insertado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Error al insertar", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(MainActivity.this, "Nombre no puede estar vacío", Toast.LENGTH_SHORT).show();
        }
    }

    private void actualizarParticipante() {
        String nombre = editText.getText().toString();
        if (!nombre.isEmpty()) {
            Call<Void> call = apiService.actualizarParticipante(nombre);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        obtenerParticipantes();
                        Toast.makeText(MainActivity.this, "Participante actualizado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Error al actualizar", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(MainActivity.this, "Nombre no puede estar vacío", Toast.LENGTH_SHORT).show();
        }
    }

    private void eliminarParticipante() {
        String nombre = editText.getText().toString();
        if (!nombre.isEmpty()) {
            Call<Void> call = apiService.eliminarParticipante(nombre);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        obtenerParticipantes();
                        Toast.makeText(MainActivity.this, "Participante eliminado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Error al eliminar", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(MainActivity.this, "Nombre no puede estar vacío", Toast.LENGTH_SHORT).show();
        }
    }
}
