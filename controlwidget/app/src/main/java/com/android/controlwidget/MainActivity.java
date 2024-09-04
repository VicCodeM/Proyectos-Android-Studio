package com.android.controlwidget;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.button);
        final CheckBox checkBox = findViewById(R.id.checkBox);
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        final SeekBar seekBar = findViewById(R.id.seekBar);
        final Spinner spinner = findViewById(R.id.spinner);

        // Ejemplo de uso de Button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Botón presionado", Toast.LENGTH_SHORT).show();
            }
        });

        // Ejemplo de uso de CheckBox y ProgressBar
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = ((CheckBox) v).isChecked();
                if (isChecked) {
                    progressBar.setProgress(100); // Completa la ProgressBar al 100%
                } else {
                    progressBar.setProgress(0); // Restablece la ProgressBar al 0%
                }
            }
        });

        // Ejemplo de uso de SeekBar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressBar.setProgress(progress); // Actualiza la ProgressBar con el progreso del SeekBar
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Opcional: hacer algo cuando el usuario comienza a mover el SeekBar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Opcional: hacer algo cuando el usuario deja de mover el SeekBar
            }
        });

        // Ejemplo de uso de Spinner (necesita un ArrayAdapter para funcionar correctamente)
        String[] opciones = new String[]{"Opción 1", "Opción 2", "Opción 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Seleccionado: " + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Opcional: hacer algo cuando no se selecciona ningún elemento
            }
        });
    }
}


