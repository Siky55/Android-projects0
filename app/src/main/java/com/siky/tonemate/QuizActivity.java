package com.siky.tonemate;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    private TextView questionTextView, correctAnswerTextView;
    private Spinner chordSpinner, keySpinner, keySignatureSpinner;
    private Button submitButton;
    private ImageButton rotationScreenButton;

    private final String[] tones = {"C", "D", "E", "F", "G", "A", "B"};
    private final String[] keys = {"major", "minor"};
    private final String[] keySignatures = {"", "#", "b"};

    private final String[][] majorChords = {
            {"C", "Dm", "Em", "F", "G", "Am", "Bdim"},
            {"D", "Em", "F#m", "G", "A", "Bm", "C#dim"},
            {"E", "F#m", "G#m", "A", "B", "C#m", "D#dim"},
            {"F", "Gm", "Am", "Bb", "C", "Dm", "Edim"},
            {"G", "Am", "Bm", "C", "D", "Em", "F#dim"},
            {"A", "Bm", "C#m", "D", "E", "F#m", "G#dim"},
            {"B", "C#m", "D#m", "E", "F#", "G#m", "A#dim"}
    };

    private final String[][] minorChords = {
            {"Am", "Bdim", "C", "Dm", "Em", "F", "G"},
            {"Bm", "C#dim", "D", "Em", "F#m", "G", "A"},
            {"C#m", "D#dim", "E", "F#m", "G#m", "A", "B"},
            {"Dm", "Edim", "F", "Gm", "Am", "Bb", "C"},
            {"Em", "F#dim", "G", "Am", "Bm", "C", "D"},
            {"F#m", "G#dim", "A", "Bm", "C#m", "D", "E"},
            {"G#m", "A#dim", "B", "C#m", "D#m", "E", "F#"}
    };

    private int currentChordIndex;

    private String fullAnswer;

    private String request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionTextView = findViewById(R.id.questionTextView);
        chordSpinner = findViewById(R.id.toneSpinner);
        keySpinner = findViewById(R.id.keySpinner);
        keySignatureSpinner = findViewById(R.id.keySignatureSpinner);
        submitButton = findViewById(R.id.submitButton);
        //correctAnswerTextView = findViewById(R.id.correctAnswerTextView);
        rotationScreenButton = findViewById(R.id.rotateScreenButton);

        ArrayAdapter<String> chordAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, tones);
        chordAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chordSpinner.setAdapter(chordAdapter);

        ArrayAdapter<String> scaleAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, keys);
        scaleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        keySpinner.setAdapter(scaleAdapter);

        ArrayAdapter<String> keySignatureAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, keySignatures);
        keySignatureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        keySignatureSpinner.setAdapter(keySignatureAdapter);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        generateNewQuestion();

    }

    public void rotateScreen(View v) {
        int currentOrientation = getResources().getConfiguration().orientation;

        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private void generateNewQuestion() {
        Random rand = new Random();

        int keysIndex = rand.nextInt(keys.length);
        int tonesIndex = rand.nextInt(tones.length);
        currentChordIndex = rand.nextInt(7);

        String question;

        if (keysIndex == 0) {
            request = majorChords[tonesIndex][currentChordIndex];
            question = "Jaký je " + (currentChordIndex + 1) + ". akord ve stupnici " + majorChords[tonesIndex][0] + "?";
        } else {
            request = minorChords[tonesIndex][currentChordIndex];
            question = "Jaký je " + (currentChordIndex + 1) + ". akord ve stupnici " + minorChords[tonesIndex][0] + "?";
        }

        questionTextView.setText(question);
        //correctAnswerTextView.setText(getString(R.string.correct_answer) + request);
    }

    private void checkAnswer() {
        String selectedTone = chordSpinner.getSelectedItem().toString();
        String selectedKey = keySpinner.getSelectedItem().toString();
        String selectedKeySignature = keySignatureSpinner.getSelectedItem().toString();

        fullAnswer = selectedTone + selectedKeySignature;
        if (selectedKey.equals("minor")) {
            fullAnswer += "m";
        }

        if (fullAnswer.equals(request)) {
            Toast.makeText(this, R.string.correct_answer_text, Toast.LENGTH_SHORT).show();
            generateNewQuestion();
        } else {
            Toast.makeText(this, R.string.wrong_answer_text, Toast.LENGTH_SHORT).show();
        }
    }

}
