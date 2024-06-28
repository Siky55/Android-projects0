package com.example.firstapp;

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

    private TextView questionTextView;
    private Spinner noteSpinner, scaleSpinner, accidentalSpinner;
    private Button submitButton;

    private String currentKey;
    private int currentChordNumber;

    private String[] keys = {"C", "G", "D", "A", "E", "B", "F#", "Db", "Ab", "Eb", "Bb", "F"};
    private String[][] majorScales = {
            {"C", "D", "E", "F", "G", "A", "B"},
            {"G", "A", "B", "C", "D", "E", "F#"},
            {"D", "E", "F#", "G", "A", "B", "C#"},
            {"A", "B", "C#", "D", "E", "F#", "G#"},
            {"E", "F#", "G#", "A", "B", "C#", "D#"},
            {"B", "C#", "D#", "E", "F#", "G#", "A#"},
            {"F#", "G#", "A#", "B", "C#", "D#", "E#"},
            {"Db", "Eb", "F", "Gb", "Ab", "Bb", "C"},
            {"Ab", "Bb", "C", "Db", "Eb", "F", "G"},
            {"Eb", "F", "G", "Ab", "Bb", "C", "D"},
            {"Bb", "C", "D", "Eb", "F", "G", "A"},
            {"F", "G", "A", "Bb", "C", "D", "E"}
    };

    private String[][] minorScales = {
            {"A", "B", "C", "D", "E", "F", "G"},
            {"E", "F#", "G", "A", "B", "C", "D"},
            {"B", "C#", "D", "E", "F#", "G", "A"},
            {"F#", "G#", "A", "B", "C#", "D", "E"},
            {"C#", "D#", "E", "F#", "G#", "A", "B"},
            {"G#", "A#", "B", "C#", "D#", "E", "F#"},
            {"D#", "E#", "F#", "G#", "A#", "B", "C#"},
            {"Bb", "C", "Db", "Eb", "F", "Gb", "Ab"},
            {"F", "G", "Ab", "Bb", "C", "Db", "Eb"},
            {"C", "D", "Eb", "F", "G", "Ab", "Bb"},
            {"G", "A", "Bb", "C", "D", "Eb", "F"},
            {"D", "E", "F", "G", "A", "Bb", "C"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionTextView = findViewById(R.id.questionTextView);
        noteSpinner = findViewById(R.id.noteSpinner);
        scaleSpinner = findViewById(R.id.scaleSpinner);
        accidentalSpinner = findViewById(R.id.accidentalSpinner);
        submitButton = findViewById(R.id.submitButton);

        ArrayAdapter<CharSequence> noteAdapter = ArrayAdapter.createFromResource(this,
                R.array.notes_array, android.R.layout.simple_spinner_item);
        noteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        noteSpinner.setAdapter(noteAdapter);

        ArrayAdapter<CharSequence> scaleAdapter = ArrayAdapter.createFromResource(this,
                R.array.scales_array, android.R.layout.simple_spinner_item);
        scaleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        scaleSpinner.setAdapter(scaleAdapter);

        ArrayAdapter<CharSequence> accidentalAdapter = ArrayAdapter.createFromResource(this,
                R.array.accidentals_array, android.R.layout.simple_spinner_item);
        accidentalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accidentalSpinner.setAdapter(accidentalAdapter);
        accidentalSpinner.setSelection(0);

        generateNewQuestion();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void generateNewQuestion() {
        Random rand = new Random();
        int keyIndex = rand.nextInt(keys.length);
        currentKey = keys[keyIndex];
        currentChordNumber = rand.nextInt(7) + 1;

        String question = getString(R.string.question_text, currentChordNumber, currentKey);
        questionTextView.setText(question);
    }

    private void checkAnswer() {
        String selectedNote = noteSpinner.getSelectedItem().toString();
        String selectedScale = scaleSpinner.getSelectedItem().toString();
        String selectedAccidental = accidentalSpinner.getSelectedItem().toString();

        int keyIndex = -1;
        for (int i = 0; i < keys.length; i++) {
            if (keys[i].equals(currentKey)) {
                keyIndex = i;
                break;
            }
        }

        String correctNote;
        if (selectedScale.equals("major")) {
            correctNote = majorScales[keyIndex][currentChordNumber - 1];
        } else {
            correctNote = minorScales[keyIndex][currentChordNumber - 1];
        }

        if (selectedAccidental.equals("-")) {
            selectedAccidental = "";
        }

        if (selectedNote.equals(correctNote) && selectedAccidental.isEmpty() && selectedScale.equals("major")) {
            Toast.makeText(this, getString(R.string.correct_answer_text), Toast.LENGTH_SHORT).show();
            generateNewQuestion();
        } else {
            Toast.makeText(this, getString(R.string.wrong_answer_text), Toast.LENGTH_SHORT).show();
        }
    }
}
