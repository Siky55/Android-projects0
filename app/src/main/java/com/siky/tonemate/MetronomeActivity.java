package com.siky.tonemate;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MetronomeActivity extends AppCompatActivity {

    private Button startStopButton;
    private EditText bpmEditText;
    private ImageView indicatorImageView;
    private boolean isRunning = false;
    private int bpm = 60;

    private SoundPool soundPool;
    private int soundId;

    private long lastTickTime = 0;
    private long tickInterval = 0;

    private ScheduledExecutorService scheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metronome);

        startStopButton = findViewById(R.id.startStopButton);
        bpmEditText = findViewById(R.id.bpmEditText);
        indicatorImageView = findViewById(R.id.indicatorImageView);

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundId = soundPool.load(this, R.raw.click, 1);

        indicatorImageView.setTag("grey");

        startStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning) {
                    startMetronome();
                    startStopButton.setText("stop");
                } else {
                    stopMetronome();
                    startStopButton.setText("start");
                }
            }
        });
    }

    private void startMetronome() {
        try {
            bpm = Integer.parseInt(bpmEditText.getText().toString());
            if (bpm < 10 || bpm > 240) {
                Toast.makeText(MetronomeActivity.this, "Hodnota BPM musí být v rozmezí 10-240", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(MetronomeActivity.this, "Neplatná hodnota BPM", Toast.LENGTH_SHORT).show();
            return;
        }
        isRunning = true;

        tickInterval = 60000 / bpm;

        // Vytvoření a spuštění plánovače pro metronom
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        toggleIndicator();
                    }
                });
            }
        }, 0, tickInterval, TimeUnit.MILLISECONDS);
    }

    private void stopMetronome() {
        isRunning = false;
        if (scheduler != null) {
            scheduler.shutdown();
        }
        indicatorImageView.setImageResource(R.drawable.grey_indicator);
        indicatorImageView.setTag("grey");
    }

    private void toggleIndicator() {
        soundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
        if (indicatorImageView.getTag() == null || "grey".equals(indicatorImageView.getTag())) {
            indicatorImageView.setImageResource(R.drawable.red_indicator);
            indicatorImageView.setTag("red");
        } else {
            indicatorImageView.setImageResource(R.drawable.grey_indicator);
            indicatorImageView.setTag("grey");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (scheduler != null) {
            scheduler.shutdown();
        }
        soundPool.release();
        soundPool = null;
    }
}
