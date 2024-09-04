package com.android.recetarioaleatrio;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.recetarioaleatrio.DatabaseHelper;

import java.io.ByteArrayOutputStream;

public class AddRecipeActivity extends AppCompatActivity {
    private EditText recipeNameEditText, recipePreparationEditText;
    private ImageView selectedImage;
    private Button addRecipeButton;
    private DatabaseHelper dbHelper;
    private Bitmap selectedBitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        dbHelper = new DatabaseHelper(this);
        recipeNameEditText = findViewById(R.id.recipeNameEditText);
        recipePreparationEditText = findViewById(R.id.recipePreparationEditText);
        selectedImage = findViewById(R.id.selectedImage);
        addRecipeButton = findViewById(R.id.addRecipeButton);

        Button selectImageButton = findViewById(R.id.selectImageButton);
        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });

        addRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recipeName = recipeNameEditText.getText().toString();
                String recipePreparation = recipePreparationEditText.getText().toString();
                if (selectedBitmap != null && !recipeName.isEmpty() && !recipePreparation.isEmpty()) {
                    byte[] imageBytes = convertBitmapToByteArray(selectedBitmap);
                    long newRowId = dbHelper.insertRecipe(recipeName, recipePreparation, imageBytes);
                    if (newRowId != -1) {
                        Toast.makeText(AddRecipeActivity.this, "Receta agregada con éxito", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(AddRecipeActivity.this, "Error al agregar la receta", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddRecipeActivity.this, "Por favor, selecciona una imagen, escribe el nombre de la receta y el método de preparación", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            try {
                selectedBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                selectedImage.setImageBitmap(selectedBitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private byte[] convertBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}
