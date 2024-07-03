package com.siky.tonemate;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    private TextView questionTextView, correctAnswerTextView;
    private Spinner chordSpinner, scaleSpinner, keySignatureSpinner;
    private Button submitButton;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionTextView = findViewById(R.id.questionTextView);
        chordSpinner = findViewById(R.id.chordSpinner);
        scaleSpinner = findViewById(R.id.scaleSpinner);
        keySignatureSpinner = findViewById(R.id.keySignatureSpinner);
        submitButton = findViewById(R.id.submitButton);
        correctAnswerTextView = findViewById(R.id.correctAnswerTextView);
<<<<<<< HEAD
=======
        rotationScreenButton = findViewById(R.id.rotateScreenButton);
>>>>>>> parent of cf9a5bd (Detaily)

        ArrayAdapter<String> chordAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, tones);
        chordAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chordSpinner.setAdapter(chordAdapter);

        ArrayAdapter<String> scaleAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, keys);
        scaleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        scaleSpinner.setAdapter(scaleAdapter);

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

    private void generateNewQuestion() {
        Random rand = new Random();

        int keysIndex = rand.nextInt(keys.length);
        int tonesIndex = rand.nextInt(tones.length);
        String selectedKey = tones[tonesIndex];
        String selectedKeySignature = keySignatures[rand.nextInt(keySignatures.length)];
        currentChordIndex = rand.nextInt(7);

        String fullKey = selectedKey + selectedKeySignature;
        String correctChord;
        String question;

        if (keysIndex == 0) {
            correctChord = majorChords[tonesIndex][currentChordIndex];
            question = "Jaký je " + (currentChordIndex + 1) + ". akord ve stupnici " + majorChords[tonesIndex][0] + " " + keys[keysIndex] + "?";
        } else {
            correctChord = minorChords[tonesIndex][currentChordIndex];
            question = "Jaký je " + (currentChordIndex + 1) + ". akord ve stupnici " + minorChords[tonesIndex][0] + " " + keys[keysIndex] + "?";
        }

        questionTextView.setText(question);
<<<<<<< HEAD
        correctAnswerTextView.setText(getString(R.string.default_correct_answer) + correctChord);
=======
        correctAnswerTextView.setText(getString(R.string.correct_answer) + request);
>>>>>>> parent of cf9a5bd (Detaily)
    }

    private void checkAnswer() {
        String selectedTone = chordSpinner.getSelectedItem().toString();
        String selectedKey = scaleSpinner.getSelectedItem().toString();
        String selectedKeySignature = keySignatureSpinner.getSelectedItem().toString();

        String fullAnswer = selectedTone + selectedKeySignature;
        if (selectedKey.equals("minor")) {
            fullAnswer += "m";
        }

<<<<<<< HEAD
        int keyIndex = chordSpinner.getSelectedItemPosition();
        int scaleIndex = scaleSpinner.getSelectedItemPosition();

        String userChord;
        if (scaleIndex == 0) {
            userChord = majorChords[keyIndex][currentChordIndex];
        } else {
            userChord = minorChords[keyIndex][currentChordIndex];
        }

        String correctChord = correctAnswerTextView.getText().toString().replace(getString(R.string.default_correct_answer), "").trim();

        if (userChord.equals(correctChord)) {
            Toast.makeText(this, "Správně!", Toast.LENGTH_SHORT).show();
=======
//        int toneIndex = chordSpinner.getSelectedItemPosition();
//        int keyIndex = keySpinner.getSelectedItemPosition();
//        int keySignatureIndex = keySpinner.getSelectedItemPosition();
//
//        String userChord;
//        if (keyIndex == 0) {
//            userChord = majorChords[toneIndex][currentChordIndex];
//        } else {
//            userChord = minorChords[toneIndex][currentChordIndex];
//        }
//
//        String correctChord = correctAnswerTextView.getText().toString().replace(getString(R.string.correct_answer), "").trim();

//        if (userChord.equals(correctChord)) {
//            Toast.makeText(this, R.string.correct_answer_text, Toast.LENGTH_SHORT).show();
//            generateNewQuestion();
//        } else {
//            Toast.makeText(this, R.string.wrong_answer_text, Toast.LENGTH_SHORT).show();
//        }

        if (fullAnswer.equals(request)) {
            Toast.makeText(this, R.string.correct_answer_text, Toast.LENGTH_SHORT).show();
>>>>>>> parent of cf9a5bd (Detaily)
            generateNewQuestion();
        } else {
            Toast.makeText(this, "Špatně!", Toast.LENGTH_SHORT).show();
        }
    }
}
