package com.example.firstapp;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.content.Context;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class CircleActivity extends AppCompatActivity implements View.OnTouchListener {

    private Vibrator vibrator;
    private int vibrationStrength = 6;
    private ImageView circle, firstHalfCircle, secondHalfCircle, cover1, cover2, rotControlL, rotControlR;
    private ImageButton rotationScreenButton, zoomButton, rotationModeButton, buttonLeftR, buttonRightR, buttonLeftL,buttonRightL, coverButton, resetButton;
    private boolean zoomedIn = false;
    private boolean cover1Visible = false;
    private boolean cover2Visible = false;
    private float initialY, initialRotation, rotationAngle = 0;
    final int rotationStep = 30;
    private boolean rotationBySteps=true;

    @SuppressLint("ClickableViewAccessibility") // Potlaceni varovnych zprav s doporucenim Android Studia pro hadicapovane
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        circle = findViewById(R.id.circleBtn);
        cover1 = findViewById(R.id.cover1);
        cover2 = findViewById(R.id.cover2);
        rotControlL = findViewById(R.id.rotControlL);
        rotControlR = findViewById(R.id.rotControlR);
        zoomButton = findViewById(R.id.zoomButton);
        rotationModeButton = findViewById(R.id.rotationModeButton);
        buttonLeftR = findViewById(R.id.buttonLeftR);
        buttonRightR = findViewById(R.id.buttonRightR);
        buttonLeftL = findViewById(R.id.buttonLeftLL);
        buttonRightL = findViewById(R.id.buttonRightLL);
        coverButton = findViewById(R.id.coverButton);
        resetButton = findViewById(R.id.resetButton);
        rotationScreenButton = findViewById(R.id.rotateScreenButton);
        firstHalfCircle = findViewById(R.id.firstHalfCircle);
        secondHalfCircle = findViewById(R.id.secondHalfCircle);

        circle.setOnTouchListener(this);
        rotControlL.setOnTouchListener(this);
        rotControlR.setOnTouchListener(this);
        buttonLeftR.setOnTouchListener(this);
        buttonRightR.setOnTouchListener(this);
        buttonLeftL.setOnTouchListener(this);
        buttonRightL.setOnTouchListener(this);
        coverButton.setOnTouchListener(this);
        resetButton.setOnTouchListener(this);
        firstHalfCircle.setOnTouchListener(this);
        secondHalfCircle.setOnTouchListener(this);

        rotationScreenButton.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int currentOrientation = getResources().getConfiguration().orientation;

                if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                } else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
                return true;
            }
        });

        coverButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            vibrator.vibrate(vibrationStrength);
                            if (zoomedIn==false) {
                                if (cover2Visible==false) {
                                    cover2.setVisibility(View.VISIBLE);
                                    cover2Visible = true;
                                } else {
                                    cover2.setVisibility(View.INVISIBLE);
                                    cover2Visible = false;
                                }
                            }
                            break;
                        case MotionEvent.ACTION_UP:
                            vibrator.vibrate(vibrationStrength);
                            break;

                }
                return true; // Informuje o tom, ze akce BYLA vykonana
            }
        });


        resetButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        vibrator.vibrate(vibrationStrength);
                        circle.setRotation(0);
                        break;
                    case MotionEvent.ACTION_UP:
                        vibrator.vibrate(vibrationStrength);
                        break;
                }
                return true;
            }
        });

        rotationModeButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        vibrator.vibrate(vibrationStrength);
                        if (rotationBySteps) {
                            buttonLeftR.setVisibility(View.GONE);
                            buttonRightR.setVisibility(View.GONE);
                        } else {
                            buttonLeftR.setVisibility(View.VISIBLE);
                            buttonRightR.setVisibility(View.VISIBLE);
                        }
                        rotationBySteps = !rotationBySteps;
                        break;
                    case MotionEvent.ACTION_UP:
                        vibrator.vibrate(vibrationStrength);
                        break;
                }
                return true;
            }
        });

        zoomButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        cover2.setVisibility(View.INVISIBLE);
                        cover2Visible=false;
                        vibrator.vibrate(vibrationStrength);
                        if (zoomedIn) {
                            cover1.setVisibility(View.INVISIBLE);
                            //cover1Visible = false;

                            zoomButton.setImageResource(R.drawable.baseline_zoom_in_24);
                            circle.setScaleX(1f);
                            circle.setScaleY(1f);
                            circle.setTranslationY(0);
                            cover1.setScaleX(1f);
                            cover1.setScaleY(1f);
                            cover1.setTranslationY(0);
                        } else {
                            cover1.setVisibility(View.VISIBLE);
                            //cover1Visible = true;

                            zoomButton.setImageResource(R.drawable.baseline_zoom_out_24);
                            circle.setScaleX(1.43f);
                            circle.setScaleY(1.43f);
                            circle.setTranslationY(circle.getHeight() * 0.215f);
                            cover1.setScaleX(1.43f);
                            cover1.setScaleY(1.43f);
                            cover1.setTranslationY(circle.getHeight() * 0.215f);
                        }
                        zoomedIn = !zoomedIn;
                        break;
                    case MotionEvent.ACTION_UP:
                        vibrator.vibrate(vibrationStrength);
                        break;
                }
                return true;
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                if (v instanceof ImageButton) v.setBackgroundResource(R.drawable.white_button_not_pressed);

                initialY = event.getRawY();
                initialRotation = rotationAngle;

                if (v == buttonLeftR || v == buttonLeftL) {
                    rotationAngle -= rotationStep;
                    circle.setRotation(rotationAngle);
                    vibrator.vibrate(vibrationStrength);
                } else if (v == buttonRightR || v == buttonRightL) {
                    rotationAngle += rotationStep;
                    circle.setRotation(rotationAngle);
                    vibrator.vibrate(vibrationStrength);
                }

                break;
            case MotionEvent.ACTION_MOVE:
                float newY = event.getRawY();
                float workingY = newY - initialY;

                if (v == secondHalfCircle || v == rotControlR) rotationAngle = initialRotation + workingY / 6; // Rychlost otaceni
                else if (v == firstHalfCircle || v == rotControlL) rotationAngle = initialRotation - workingY / 6;

                // V rezimu krokove otaceni
                if (rotationBySteps)  {
                    rotationAngle = Math.round(rotationAngle / rotationStep) * rotationStep;
                }
                circle.setRotation(rotationAngle);
                break;
            case MotionEvent.ACTION_UP:
                if (v == buttonLeftR || v == buttonRightR  || v == buttonRightL || v == buttonLeftL) {
                    vibrator.vibrate(vibrationStrength);
                }
                break;
        }
        return true;
    }

    // omezeni funkcnosti gesta "zpet"
    private boolean backPressed = false;
    private long elapsedTime = 0;
    private final long WAITING_TIME = 2000; // 2 sekundy

    @Override
    public void onBackPressed() {
        if (backPressed) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - elapsedTime <= WAITING_TIME)
                super.onBackPressed(); // provede akci ZPET
            else {
                backPressed = false;
            }
        } else {
            backPressed = true;
            elapsedTime = System.currentTimeMillis();
            Toast.makeText(this, "Stiskněte znovu pro vyvolání akce zpět", Toast.LENGTH_SHORT).show();
        }
    }
}
