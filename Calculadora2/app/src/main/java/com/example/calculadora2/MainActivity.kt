package com.example.calculadora2

import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var pantalla: EditText
    private lateinit var resultado: EditText

    private var operacionActual: String? = null
    private var operando1: Double = 0.0
    private var operando2: Double = 0.0
    private var resultadoAnterior: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pantalla = findViewById(R.id.pantalla)
        resultado = findViewById(R.id.resultado)

        val numeros = listOf(R.id.b0, R.id.b1, R.id.b2, R.id.b3, R.id.b4, R.id.b5, R.id.b6, R.id.b7, R.id.b8, R.id.b9, R.id.punto)
        for (id in numeros) {
            findViewById<Button>(id).setOnClickListener {
                agregarDigito((it as Button).text.toString())
            }
        }

        val operaciones = listOf(R.id.mas, R.id.menos, R.id.multiplicacion, R.id.division)
        for (id in operaciones) {
            findViewById<Button>(id).setOnClickListener {
                manejarOperacion((it as Button).text.toString())
            }
        }

        findViewById<Button>(R.id.igual).setOnClickListener {
            calcularResultado()
        }

        findViewById<Button>(R.id.AC).setOnClickListener {
            pantalla.text.clear()
            resultado.text.clear()
            reiniciarOperacion()
        }

        findViewById<Button>(R.id.borrar).setOnClickListener {
            borrarUltimoDigito()
        }

        findViewById<Button>(R.id.porciento).setOnClickListener {
            calcularPorcentaje()
        }
    }

    private fun operar(operando1: Double, operacion: String, operando2: Double): Double {
        return when (operacion) {
            "+" -> operando1 + operando2
            "-" -> operando1 - operando2
            "x" -> operando1 * operando2
            "÷" -> operando1 / operando2
            else -> throw IllegalArgumentException("Operación no válida")
        }
    }

    private fun agregarDigito(digito: String) {
        pantalla.append(digito)
    }

    private fun manejarOperacion(operacion: String) {
        if (pantalla.text.isNotEmpty() && operacionActual == null) {
            operando1 = pantalla.text.toString().toDouble()
            operacionActual = operacion
            pantalla.text.clear()
        }
    }

    private fun calcularResultado() {
        if (pantalla.text.isNotEmpty() && operacionActual != null) {
            operando2 = pantalla.text.toString().toDouble()
            resultadoAnterior = operar(operando1, operacionActual!!, operando2)
            actualizarPantallas(resultadoAnterior.toString(), resultadoAnterior)
            reiniciarOperacion()
        }
    }

    private fun reiniciarOperacion() {
        operacionActual = null
        operando1 = 0.0
        operando2 = 0.0
    }

    private fun borrarUltimoDigito() {
        val textoPantalla = pantalla.text.toString()
        if (textoPantalla.isNotEmpty()) {
            pantalla.text.delete(textoPantalla.length - 1, textoPantalla.length)
        }
    }

    private fun calcularPorcentaje() {
        if (pantalla.text.isNotEmpty()) {
            val valorActual = pantalla.text.toString().toDouble()
            val porcentaje = operando1 - (operando1 * (valorActual / 100))

            pantalla.text = Editable.Factory.getInstance().newEditable(porcentaje.toString())
        }
    }

    private fun actualizarPantallas(textoPantalla: String, textoResultado: Double) {
        pantalla.text = Editable.Factory.getInstance().newEditable(textoPantalla)
        resultado.text = Editable.Factory.getInstance().newEditable(textoResultado.toString())
    }
}
