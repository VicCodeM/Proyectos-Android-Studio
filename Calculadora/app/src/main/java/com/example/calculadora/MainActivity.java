package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText inputNumero1;
    private EditText inputNumero2;
    private TextView resultadofinalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputNumero1 = findViewById(R.id.editTextText);
        inputNumero2 = findViewById(R.id.editTextText2);
        resultadofinalTextView = findViewById(R.id.resultadofinal);
    }

    public void Resta1(View view) {
        realizarOperacion("-");
    }

    public void Suma(View view) {
        realizarOperacion("+");
    }

    public void Division(View view) {
        realizarOperacion("/");
    }

    public void Multiplicacion(View view) {
        realizarOperacion("*");
    }


    private void realizarOperacion(String operador) {
        try {
            double numero1 = Double.parseDouble(inputNumero1.getText().toString());
            double numero2 = Double.parseDouble(inputNumero2.getText().toString());
            double resultado = 0;

            switch (operador) {
                case "+":
                    resultado = numero1 + numero2;
                    break;
                case "-":
                    resultado = numero1 - numero2;
                    break;
                case "/":
                    if (numero2 != 0) {
                        resultado = numero1 / numero2;
                    } else {
                        // Manejar división por cero, por ejemplo, mostrar un mensaje de error
                        resultadofinalTextView.setText("Error: División por cero");
                        return;
                    }
                    break;
                case "*":
                    resultado = numero1 * numero2;
                    break;
                // Puedes añadir más casos para otras operaciones si es necesario
            }

            // Mostrar el resultado en el TextView
            resultadofinalTextView.setText(""+resultado);

        } catch (NumberFormatException e) {
            // Manejar excepción si no se ingresaron números válidos
           resultadofinalTextView.setText("Error: Ingresa números válidos");
        }
    }
}
