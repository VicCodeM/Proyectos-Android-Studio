package com.android.recetarioaleatrio;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private ImageView recipeImage;
    private TextView recipeName;
    private TextView recipePreparation; // Añadido para mostrar la preparación
    private Button randomRecipeButton;
    private Button addRecipeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        recipeImage = findViewById(R.id.recipeImage);
        recipeName = findViewById(R.id.recipeName);
        recipePreparation = findViewById(R.id.recipePreparation); // Inicializado
        randomRecipeButton = findViewById(R.id.randomRecipeButton);
        addRecipeButton = findViewById(R.id.addRecipeButton);

        randomRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Recipe randomRecipe = dbHelper.getRandomRecipe();
                if (randomRecipe != null) {
                    recipeName.setText(randomRecipe.getName());
                    recipePreparation.setText(randomRecipe.getPreparation()); // Mostrar preparación
                    Bitmap bitmap = BitmapFactory.decodeByteArray(randomRecipe.getImage(), 0, randomRecipe.getImage().length);
                    recipeImage.setImageBitmap(bitmap);
                }
            }
        });

        addRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddRecipeActivity.class);
                startActivity(intent);
            }
        });
    }
}
