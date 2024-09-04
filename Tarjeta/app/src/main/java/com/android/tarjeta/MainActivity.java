package com.android.tarjeta;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
import android.widget.ViewAnimator;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ViewAnimator viewAnimator;
    private GestureDetector gestureDetector;
    private VideoView rVideo;

    private MediaPlayer rAudio;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewAnimator = findViewById(R.id.animador);
        rVideo = findViewById(R.id.rvideo);

        // Botones
        Button btnAnterior1 = findViewById(R.id.btnAnterior1);
        Button btnSiguiente1 = findViewById(R.id.btnSiguiente1);
        Button btnAnterior2 = findViewById(R.id.btnAnterior2);
        Button btnSiguiente2 = findViewById(R.id.btnSiguiente2);
        Button btnAnterior3 = findViewById(R.id.btnAnterior3);
        Button btnSiguiente3 = findViewById(R.id.btnSiguiente3);
        Button btnAnterior4 = findViewById(R.id.btnAnterior4);
        Button btnSiguiente4 = findViewById(R.id.btnSiguiente4);
        Button btnAnterior = findViewById(R.id.btnAnterior);
        Button btnSiguiente = findViewById(R.id.btnSiguiente);
        Button btnPlay = findViewById(R.id.btnPlay);

        Button btnPause = findViewById(R.id.btnPause);

       rAudio = MediaPlayer.create(this,R.raw.ouo);
        String ruta = "android.resource://" + getPackageName() + "/" + R.raw.oooo;
        rVideo.setVideoURI(Uri.parse(ruta));




       btnPause.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               rAudio.pause();
           }
       });

       btnPlay.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v){
               rAudio.start();
           }
       });






        // Asignar listeners a los botones
        btnAnterior1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewAnimator.getDisplayedChild() > 0) {
                    viewAnimator.showPrevious();
                    checkAndPlayVideo();
                }
            }
        });

        btnSiguiente1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewAnimator.getDisplayedChild() < viewAnimator.getChildCount() - 1) {
                    viewAnimator.showNext();
                    checkAndPlayVideo();
                }
            }
        });

        btnAnterior2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewAnimator.getDisplayedChild() > 0) {
                    viewAnimator.showPrevious();
                    checkAndPlayVideo();
                }
            }
        });

        btnSiguiente2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewAnimator.getDisplayedChild() < viewAnimator.getChildCount() - 1) {
                    viewAnimator.showNext();
                    checkAndPlayVideo();
                }
            }
        });

        btnAnterior3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewAnimator.getDisplayedChild() > 0) {
                    viewAnimator.showPrevious();
                    checkAndPlayVideo();
                }
            }
        });

        btnSiguiente3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewAnimator.getDisplayedChild() < viewAnimator.getChildCount() - 1) {
                    viewAnimator.showNext();
                    checkAndPlayVideo();
                }
            }
        });

        btnAnterior4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewAnimator.getDisplayedChild() > 0) {
                    viewAnimator.showPrevious();
                    checkAndPlayVideo();
                }
            }
        });

        btnSiguiente4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewAnimator.getDisplayedChild() < viewAnimator.getChildCount() - 1) {
                    viewAnimator.showNext();
                    checkAndPlayVideo();
                }
            }
        });

        btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewAnimator.getDisplayedChild() > 0) {
                    viewAnimator.showPrevious();
                    checkAndPlayVideo();
                }
            }
        });

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewAnimator.getDisplayedChild() < viewAnimator.getChildCount() - 1) {
                    viewAnimator.showNext();
                    checkAndPlayVideo();
                }
            }
        });

        // Configurar GestureDetector
        gestureDetector = new GestureDetector(this, new SwipeGestureListener());

        // Asignar listener de clic a VideoView
        rVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAndPlayVideo();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event);
    }

    private void checkAndPlayVideo() {
        if (viewAnimator.getDisplayedChild() == viewAnimator.indexOfChild(findViewById(R.id.rvideo))) {

            rVideo.setMediaController(new MediaController(this));
            rVideo.start();

            if (rAudio.isPlaying()) {
                rAudio.stop(); // Pausar la música si está reproduciéndose
            }
            rVideo.start();
        }
    }



    private class SwipeGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float deltaX = e2.getX() - e1.getX();
            if (Math.abs(deltaX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (deltaX > 0 && viewAnimator.getDisplayedChild() > 0) {
                    viewAnimator.showPrevious();
                    checkAndPlayVideo();
                } else if (deltaX < 0 && viewAnimator.getDisplayedChild() < viewAnimator.getChildCount() - 1) {
                    viewAnimator.showNext();
                    checkAndPlayVideo();
                }
                return true;
            }
            return false;
        }
    }
}
