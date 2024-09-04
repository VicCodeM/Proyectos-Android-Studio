package com.android.usuarioedad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class activity_second extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ImageView imageView = findViewById(R.id.imageView);
        String name = getIntent().getStringExtra("name");
        int age = getIntent().getIntExtra("age", 0);

        if (age < 18) {
            imageView.setImageResource(R.drawable.jugete);
            Toast.makeText(this, "Eres aún pequeño, " + name + "!", Toast.LENGTH_SHORT).show();
        } else {
            imageView.setImageResource(R.drawable.carro);
            Toast.makeText(this, "¡Bienvenido, " + name + "!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}