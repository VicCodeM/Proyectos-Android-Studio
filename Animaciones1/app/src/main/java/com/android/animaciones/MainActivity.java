package com.android.animaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

//nuevas

    private ImageView ivMiImagen;
    private Button btMiBoton;

    private Button btParar;

    private ImageView imageViewFadeRotate;
    private Button buttonFadeRotate;

    private Button btMainActivityCancel;
    //asta aqui
    private ImageView ivEjeX;
    private ImageView ivEjeY;
    private ImageView ivAlpha;
    private ImageView ivRotation;

    private ImageView ivTodo;
    private ImageView ivBucle;
    private ImageView ivScale;

    private Button btEjeX;
    private Button btEjeY;
    private Button btAlpha;
    private Button btRotation;

    private Button btTodo;
    private Button btBucle;
    private Button btScale;
    private Button btMainCancel;

    //ObjectAnimator -> Nos proporciona soporte parar animar nuestros objetos
    private ObjectAnimator animatorX;
    private ObjectAnimator animatorY;
    private ObjectAnimator animatorAlpha;
    private ObjectAnimator animatorRotation;


    private long animationDuration = 1000;

    //AnimatorSet -> Reproduce un conjunto de ObjectAnimator en un orden especificado. Las animaciones pueden ser todas a la vez o secuenciadas
    private AnimatorSet animatorSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animatorSet = new AnimatorSet();

        // Inicialización de la imagen y el botón
        ivMiImagen = findViewById(R.id.ivMiImagen);
        btMiBoton = findViewById(R.id.btMiBoton);
         btParar = findViewById(R.id.btParar);

        btMainActivityCancel = findViewById(R.id.btMainActivityCancel);
        imageViewFadeRotate = findViewById(R.id.imageFadeRotate);
        buttonFadeRotate = findViewById(R.id.buttonFadeRotate);

        btParar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Parar();
            }
        });

        buttonFadeRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateFadeRotate();
            }
        });




        btEjeX = findViewById(R.id.btEjeX);
        ivEjeX = findViewById(R.id.ivEjeX);
        btEjeX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animacion("ejeX");
            }
        });

        btEjeY = findViewById(R.id.btEjeY);
        ivEjeY = findViewById(R.id.ivEjeY);
        btEjeY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animacion("ejeY");
            }
        });

        btAlpha = findViewById(R.id.btAlpha);
        ivAlpha = findViewById(R.id.ivAlpha);
        btAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animacion("alpha");
            }
        });

        btRotation = findViewById(R.id.btRotation);
        ivRotation = findViewById(R.id.ivRotation);
        btRotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animacion("rotation");
            }
        });

        btTodo = findViewById(R.id.btTodo);
        ivTodo = findViewById(R.id.ivTodo);
        btTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animacion("todo");
            }
        });

        btBucle = findViewById(R.id.btBucle);
        ivBucle = findViewById(R.id.ivBucle);
        btBucle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animacion("bucle");
            }
        });

        btScale = findViewById(R.id.btScale);
        ivScale = findViewById(R.id.ivScale);
        btScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animacion("scale");
            }
        });

        btMainCancel = findViewById(R.id.btMainActivityCancel);
        btMainCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatorSet.cancel();
            }
        });

        // Configuración del listener para el botón
        btMiBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Llamar al método de animación cuando se haga clic en el botón
                animacionGirarYTemblar();
            }
        });

        // Asignar onClickListener al botón "Hello World!"
        btMainActivityCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Llamar a todos los métodos de animación
                animatorX = ObjectAnimator.ofFloat(ivEjeX, "x", 420f);
                animatorX.setDuration(animationDuration);
                AnimatorSet animatorSetX = new AnimatorSet();
                animatorSetX.playTogether(animatorX);
                animatorSetX.start();

                animatorY = ObjectAnimator.ofFloat(ivEjeY, "y", 420f);
                animatorY.setDuration(animationDuration);
                AnimatorSet animatorSetY = new AnimatorSet();
                animatorSetY.playTogether(animatorY);
                animatorSetY.start();

                animatorAlpha = ObjectAnimator.ofFloat(ivAlpha, View.ALPHA,1.0f, 0.0f);
                animatorAlpha.setDuration(animationDuration);
                AnimatorSet animatorSetAlpha = new AnimatorSet();
                animatorSetAlpha.playTogether(animatorAlpha);
                animatorSetAlpha.start();

                animatorRotation = ObjectAnimator.ofFloat(ivRotation, "rotation",0f, 360f);
                animatorRotation.setDuration(animationDuration);
                AnimatorSet animatorSetRotator = new AnimatorSet();
                animatorSetRotator.playTogether(animatorRotation);
                animatorSetRotator.start();

                animatorX = ObjectAnimator.ofFloat(ivTodo, "x", 420f);
                animatorX.setDuration(animationDuration);
                animatorY = ObjectAnimator.ofFloat(ivTodo, "y", 420f);
                animatorY.setDuration(animationDuration);
                animatorAlpha = ObjectAnimator.ofFloat(ivTodo, View.ALPHA,1.0f, 0.0f);
                animatorAlpha.setDuration(animationDuration);
                animatorRotation = ObjectAnimator.ofFloat(ivTodo, "rotation",0f, 360f);
                animatorRotation.setDuration(animationDuration);
                animatorSet.playTogether(animatorAlpha, animatorRotation, animatorX);
                animatorSet.start();
                animateFadeRotate();
                animacionGirarYTemblar();
                Scalate1();



                animatorX = ObjectAnimator.ofFloat(ivBucle, "x", 420f);
                animatorX.setDuration(animationDuration);
                AnimatorSet animatorSetBucle = new AnimatorSet();
                animatorSetBucle.playTogether(animatorX);
                animatorSetBucle.addListener(new AnimatorListenerAdapter() {
                    private boolean canceled = false;
                    @Override
                    public void onAnimationCancel(Animator animation) {
                        canceled = true;
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        animation.start();
                    }

                    @Override
                    public void onAnimationStart(Animator animation) {
                        canceled = false;
                    }
                });
                animatorSetBucle.start();
            }
        });
    }

    private void Parar() {
        // Detener todas las animaciones
        if (animatorX != null && animatorX.isRunning()) {
            animatorX.cancel();
        }
        if (animatorY != null && animatorY.isRunning()) {
            animatorY.cancel();
        }
        if (animatorAlpha != null && animatorAlpha.isRunning()) {
            animatorAlpha.cancel();
        }
        if (animatorRotation != null && animatorRotation.isRunning()) {
            animatorRotation.cancel();
        }


        // Reiniciar estado de las vistas
        ivEjeX.setTranslationX(0);
        ivEjeY.setTranslationY(0);
        ivAlpha.setAlpha(1.0f);
        ivRotation.setRotation(0);
        ivTodo.setTranslationX(0);
        ivTodo.setTranslationY(0);
        ivTodo.setAlpha(1.0f);
        ivTodo.setRotation(0);
        ivBucle.setTranslationX(1.0f);
        ivScale.setScaleX(1.0f);
        ivScale.setScaleY(1.0f);
        ivMiImagen.setTranslationX(0);
        ivMiImagen.setTranslationY(0);
        ivMiImagen.setRotation(0);

        AnimatorSet animatorSetBucle = new AnimatorSet();
        animatorSetBucle.playTogether(animatorX);

        animatorSetBucle.cancel();
    }

    private void Scalate1(){
        Animation animationScale = AnimationUtils.loadAnimation(this, R.anim.scale);
        ivScale.startAnimation(animationScale);
    }
    // Método para la animación de girar y temblar
    private void animacionGirarYTemblar() {
        // Definir la animación de rotación
        ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(ivMiImagen, "rotation", 0f, 360f);
        rotationAnimator.setDuration(1000); // Duración de la animación en milisegundos

        // Definir la animación de temblor
        ObjectAnimator shakeAnimator = ObjectAnimator.ofFloat(ivMiImagen, "translationX", 0f, -10f, 10f, -10f, 10f, -5f, 5f, -2f, 2f, 0f);
        shakeAnimator.setDuration(500); // Duración de la animación en milisegundos

        // Crear un AnimatorSet para ejecutar ambas animaciones simultáneamente
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(rotationAnimator, shakeAnimator);
        animatorSet.start();
    }
    private void animateFadeRotate() {
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(imageViewFadeRotate, "alpha", 1f, 0f);
        fadeOut.setDuration(1000);

        ObjectAnimator rotate = ObjectAnimator.ofFloat(imageViewFadeRotate, "rotation", 0f, 360f);
        rotate.setDuration(1000);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(fadeOut, rotate);
        animatorSet.start();
    }
    private void animacion(String animacion){
        switch (animacion){
            case "ejeX":
                animatorX = ObjectAnimator.ofFloat(ivEjeX, "x", 420f);
                animatorX.setDuration(animationDuration);
                AnimatorSet animatorSetX = new AnimatorSet();
                animatorSetX.playTogether(animatorX);
                animatorSetX.start();
                break;
            case "ejeY":
                animatorY = ObjectAnimator.ofFloat(ivEjeY, "y", 420f);
                animatorY.setDuration(animationDuration);
                AnimatorSet animatorSetY = new AnimatorSet();
                animatorSetY.playTogether(animatorY);
                animatorSetY.start();
                break;
            case "alpha":
                animatorAlpha = ObjectAnimator.ofFloat(ivAlpha, View.ALPHA,1.0f, 0.0f);
                animatorAlpha.setDuration(animationDuration);
                AnimatorSet animatorSetAlpha = new AnimatorSet();
                animatorSetAlpha.playTogether(animatorAlpha);
                animatorSetAlpha.start();
                break;
            case "rotation":
                animatorRotation = ObjectAnimator.ofFloat(ivRotation, "rotation",0f, 360f);
                animatorRotation.setDuration(animationDuration);
                AnimatorSet animatorSetRotator = new AnimatorSet();
                animatorSetRotator.playTogether(animatorRotation);
                animatorSetRotator.start();
                break;
            case "todo":
                animatorX = ObjectAnimator.ofFloat(ivTodo, "x", 420f);
                animatorX.setDuration(animationDuration);
                animatorY = ObjectAnimator.ofFloat(ivTodo, "y", 420f);
                animatorY.setDuration(animationDuration);
                animatorAlpha = ObjectAnimator.ofFloat(ivTodo, View.ALPHA,1.0f, 0.0f);
                animatorAlpha.setDuration(animationDuration);
                animatorRotation = ObjectAnimator.ofFloat(ivTodo, "rotation",0f, 360f);
                animatorRotation.setDuration(animationDuration);
                animatorSet.playTogether(animatorAlpha, animatorRotation, animatorX);
                animatorSet.start();
                break;
            case "bucle":
                animatorX = ObjectAnimator.ofFloat(ivBucle, "x", 420f);
                animatorX.setDuration(animationDuration);
                AnimatorSet animatorSetBucle = new AnimatorSet();
                animatorSetBucle.playTogether(animatorX);
                animatorSetBucle.addListener(new AnimatorListenerAdapter() {
                    private boolean canceled = false;
                    @Override
                    public void onAnimationCancel(Animator animation) {
                        canceled = true;
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        animation.start();
                    }

                    @Override
                    public void onAnimationStart(Animator animation) {
                        canceled = false;
                    }
                });
                animatorSetBucle.start();
                break;
            case "scale":
                Scalate1();
                break;
            default: break;
        }
    }
}
