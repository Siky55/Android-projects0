package com.siky.tonemate;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class XylophoneActivity extends AppCompatActivity { //implements View.OnTouchListener, View.OnClickListener {
    private SoundPool soundPool = new SoundPool(8, AudioManager.STREAM_MUSIC, 0);
    private int[] soundIds = new int[8]; // pole pro ukládání ID zvuků

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xylophone);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) // nova metoda s definovanim atributu pro specifikaci vyuziti (nepovinne)
        {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    // navrhovy vzor Builder, ktery nahrazuje velke mnozstvi konstruktoru, kterymi je potreba osetrit vynechani parametru pri vytvareni objektu
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION) // vice: CTRL + B
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build(); // vytvoreni konecne instance objektu

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(8)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else // stara metoda
            soundPool = new SoundPool(8, AudioManager.STREAM_MUSIC, 0);

        // parametry: PRVNI - kolik zvuku lze prehravat soucasne, DRUHY - typ/povaha (napr. slider hlasitosti), TRETI - kvalita (nebyla definovana)
        // STREAM_MUSIC - vyher slideru hlasitosti (music, ring, notification, alarm)
        // zvuk je v pripade music prehravan v pripojenem zarizeni (je-li pripojeno)

        // Načtení zvuků a jejich přiřazení do pole soundIds
        soundIds[0] = soundPool.load(this, R.raw.c, 1);
        soundIds[1] = soundPool.load(this, R.raw.d, 1);
        soundIds[2] = soundPool.load(this, R.raw.e, 1);
        soundIds[3] = soundPool.load(this, R.raw.f, 1);
        soundIds[4] = soundPool.load(this, R.raw.g, 1);
        soundIds[5] = soundPool.load(this, R.raw.a, 1);
        soundIds[6] = soundPool.load(this, R.raw.h, 1);
        soundIds[7] = soundPool.load(this, R.raw.c2, 1);

        // Pole ID tlačítek
        int[] buttonIds = {
                R.id.buttonC,
                R.id.buttonD,
                R.id.buttonE,
                R.id.buttonF,
                R.id.buttonG,
                R.id.buttonA,
                R.id.buttonB,
                R.id.buttonC2
        };

        // Nastavení posluchače dotyků pro všechna tlačítka
        for (int i = 0; i < buttonIds.length; i++) {
            Button button = findViewById(buttonIds[i]);
            final int soundId = soundIds[i]; // získání odpovídajícího ID zvuku

            button.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            soundPool.play(soundId, 1, 1, 0, 0, 1);
                            v.setBackgroundResource(R.drawable.xylophone_plate_pressed); // Animace
                            v.setScaleY(0.95f);
                            v.setScaleX(0.95f);
                            break;
                        case MotionEvent.ACTION_UP:
                            v.setBackgroundResource(R.drawable.xylophone_plate);
                            v.setScaleY(1f);
                            v.setScaleX(1f);
                            break;
                    }
                    return true;
                }
            });
        }
    }

    public void rotateScreen(View v) {
        int currentOrientation = getResources().getConfiguration().orientation;

        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
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

    @Override
    protected void onDestroy() // Je potreba uvolnit systemove zdroje
    {
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }
}
