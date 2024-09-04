package com.android.usuarioedad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editTextName, editTextAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
    }

    public void openSecondActivity(View view) {
        String name = editTextName.getText().toString();
        int age = Integer.parseInt(editTextAge.getText().toString());

        Intent intent = new Intent(this, activity_second.class);
        intent.putExtra("name", name);
        intent.putExtra("age", age);
        startActivity(intent);
    }
}
