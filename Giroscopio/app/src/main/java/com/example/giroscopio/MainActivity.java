package com.example.giroscopio;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;
    private TextView textViewX, textViewY, textViewZ;
    private ImageView imageView;

    // Umbral de movimiento
    private static final float MOVEMENT_THRESHOLD = 0.3f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewX = findViewById(R.id.textViewX);
        textViewY = findViewById(R.id.textViewY);
        textViewZ = findViewById(R.id.textViewZ);
        imageView = findViewById(R.id.imageView);

        // Inicializar el SensorManager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Obtener el giroscopio del SensorManager
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        // Comprobar si el dispositivo tiene giroscopio
        if (gyroscopeSensor == null) {
            textViewX.setText("El dispositivo no tiene giroscopio.");
            textViewY.setText("");
            textViewZ.setText("");
            return;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Registrar el Listener del sensor
        sensorManager.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Detener la escucha del sensor para ahorrar energía
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Manejar los cambios en el sensor de giroscopio aquí
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            // Obtener los valores de los ejes X, Y, Z del giroscopio
            float axisX = event.values[0];
            float axisY = event.values[1];
            float axisZ = event.values[2];

            // Mostrar los valores del giroscopio en los TextViews solo si el movimiento es significativo
            if (Math.abs(axisX) > MOVEMENT_THRESHOLD || Math.abs(axisY) > MOVEMENT_THRESHOLD || Math.abs(axisZ) > MOVEMENT_THRESHOLD) {
                // Si el movimiento es significativo, mostrar los valores
                textViewX.setText("Eje X: " + axisX);
                textViewY.setText("Eje Y: " + axisY);
                textViewZ.setText("Eje Z: " + axisZ);

                // Mover la imagen según los valores del giroscopio
                moveImage(axisX, axisY);

                // Rotar la imagen según los valores del giroscopio
                rotateImage(axisX, axisY, axisZ);
            } else {
                // Reiniciar la posición y rotación de la imagen
                resetImage();
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Método requerido pero no se utiliza en este ejemplo
    }

    private void moveImage(float axisX, float axisY) {
        // Calcula el desplazamiento en píxeles basado en los valores del giroscopio
        int displacementX = (int) (axisX * 50);
        int displacementY = (int) (axisY * 50);

        // Mueve la imagen en los ejes X e Y
        imageView.setTranslationX(displacementX);
        imageView.setTranslationY(displacementY);
    }

    private void rotateImage(float axisX, float axisY, float axisZ) {
        // Calcula el ángulo de rotación en grados basado en los valores del giroscopio
        float rotationAngle = (float) Math.toDegrees(Math.atan2(axisY, axisX));

        // Aplica la rotación a la imagen
        imageView.setRotation(rotationAngle);
    }

    private void resetImage() {
        // Reinicia la posición y rotación de la imagen
        imageView.setTranslationX(0);
        imageView.setTranslationY(0);
        imageView.setRotation(0);
    }
}
