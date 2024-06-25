package com.example.firstapp;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MetronomeActivity extends AppCompatActivity {

    private Button startStopButton;
    private EditText bpmEditText;
    private ImageView indicatorImageView;
    private boolean isRunning = false;
    private int bpm = 60;
    private Handler handler;

    private SoundPool soundPool;
    private int soundId;

    private long lastTickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metronome);

        startStopButton = findViewById(R.id.startStopButton);
        bpmEditText = findViewById(R.id.bpmEditText);
        indicatorImageView = findViewById(R.id.indicatorImageView);

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundId = soundPool.load(this, R.raw.click, 1);

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

        handler = new Handler();
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
        indicatorImageView.setImageResource(R.drawable.red_indicator);
        indicatorImageView.setTag("red");
        handler.postDelayed(metronomeRunnable, calculateDelay(bpm)); // Spustí metronom se správným časováním
    }

    private void stopMetronome() {
        isRunning = false;
        handler.removeCallbacks(metronomeRunnable);
        indicatorImageView.setImageResource(R.drawable.grey_indicator);
    }

    private void toggleIndicator() {
        soundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
        if (indicatorImageView.getTag() == null || indicatorImageView.getTag() == "grey") {
            indicatorImageView.setImageResource(R.drawable.red_indicator);
            indicatorImageView.setTag("red");
        } else {
            indicatorImageView.setImageResource(R.drawable.grey_indicator);
            indicatorImageView.setTag("grey");
        }
    }

    private long calculateDelay(int bpm) {
        long currentSystemTime = SystemClock.elapsedRealtime();
        long ticksElapsed = currentSystemTime - lastTickTime;
        lastTickTime = currentSystemTime;
        long ticksPerBeat = 60000 / bpm;
        return ticksPerBeat - ticksElapsed;
    }

    private Runnable metronomeRunnable = new Runnable() {
        @Override
        public void run() {
            toggleIndicator(); // Blikne indikátorem v pravidelných intervalech
            handler.postDelayed(this, calculateDelay(bpm)); // 60000 ms (1 minute) divided by bpm gives interval in milliseconds
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }
}
