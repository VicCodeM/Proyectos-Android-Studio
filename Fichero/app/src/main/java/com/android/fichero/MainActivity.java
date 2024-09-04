package com.android.fichero;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.inappmessaging.model.Button;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText etFile;
    private View btSave;
    private View btRead;
    private static final String FILE_NAME = "texto.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setUpView();
    }

    private void setUpView(){
        etFile = findViewById(R.id.etFile);
        btSave = findViewById(R.id.btSave);
        btSave.setOnClickListener(view -> saveFile());
        btRead = findViewById(R.id.btRead);
        btRead.setOnClickListener(view -> readFile());
    }


    private void saveFile(){
        String textoASalvar = etFile.getText().toString();
        try (FileOutputStream fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE)) {
            fileOutputStream.write(textoASalvar.getBytes());
            Log.d("TAG1", "Fichero Salvado en: " + getFilesDir() + "/" + FILE_NAME);
            Toast.makeText(this, "Archivo guardado correctamente", Toast.LENGTH_SHORT).show();
            etFile.getText().clear(); // Limpia el EditText después de guardar
        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Error al guardar el archivo", Toast.LENGTH_SHORT).show();
        }
    }

    private void readFile(){
        try (FileInputStream fileInputStream = openFileInput(FILE_NAME);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            StringBuilder stringBuilder = new StringBuilder();
            String lineaTexto;
            while((lineaTexto = bufferedReader.readLine()) != null){
                stringBuilder.append(lineaTexto).append("\n");
            }
            etFile.setText(stringBuilder.toString());
        } catch (Exception e){
            Toast.makeText(this, "Error al leer el archivo", Toast.LENGTH_SHORT).show();
        }
    }




}