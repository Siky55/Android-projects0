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

    // Definice dat
    private final String[] keys = {"C", "D", "E", "F", "G", "A", "B"};
    private final String[] scales = {"major", "minor", "diminished"};
    private final String[] keySignatures = {"", "#", "b"};
    private final String[][] chords = {
            // Akordy pro major stupnici
            {"C", "Dm", "Em", "F", "G", "Am", "Bdim"},
            {"G", "Am", "Bm", "C", "D", "Em", "F#dim"},
            {"D", "Em", "F#m", "G", "A", "Bm", "C#dim"},
            {"A", "Bm", "C#m", "D", "E", "F#m", "G#dim"},
            {"E", "F#m", "G#m", "A", "B", "C#m", "D#dim"},
            {"B", "C#m", "D#m", "E", "F#", "G#m", "A#dim"},
            {"F#", "G#m", "A#m", "B", "C#", "D#m", "E#dim"},
            {"Db", "Ebm", "Fm", "Gb", "Ab", "Bbm", "Cdim"},
            {"Ab", "Bbm", "Cm", "Db", "Eb", "Fm", "Gdim"},
            {"Eb", "Fm", "Gm", "Ab", "Bb", "Cm", "Ddim"},
            {"Bb", "Cm", "Dm", "Eb", "F", "Gm", "Adim"},
            {"F", "Gm", "Am", "Bb", "C", "Dm", "Edim"},

            // Akordy pro minor stupnici
            {"A", "Bdim", "C", "Dm", "E", "F", "G"},
            {"E", "F#dim", "G", "Am", "B", "C", "D"},
            {"B", "C#dim", "D", "Em", "F#", "G", "A"},
            {"F#", "G#dim", "A", "Bm", "C#", "D", "E"},
            {"C#", "D#dim", "E", "F#m", "G#", "A", "B"},
            {"G#", "A#dim", "B", "C#m", "D#", "E", "F#"},
            {"D#", "E#dim", "F#", "G#m", "A#", "B", "C#"},
            {"Cb", "Dbm", "Ebm", "Fb", "Gbm", "Abm", "Bbb"},
            {"Gb", "Abm", "Bbm", "Cb", "Db", "Ebm", "Fdim"},
            {"Db", "Ebm", "Fm", "Gb", "Ab", "Bbm", "Cdim"},
            {"Ab", "Bbm", "Cm", "Db", "Eb", "Fm", "Gdim"},
            {"Eb", "Fm", "Gm", "Ab", "Bb", "Cm", "Ddim"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Inicializace prvků rozhraní
        questionTextView = findViewById(R.id.questionTextView);
        chordSpinner = findViewById(R.id.chordSpinner);
        scaleSpinner = findViewById(R.id.scaleSpinner);
        keySignatureSpinner = findViewById(R.id.keySignatureSpinner);
        submitButton = findViewById(R.id.submitButton);
        correctAnswerTextView = findViewById(R.id.correctAnswerTextView);

        // Adaptéry pro Spinners
        ArrayAdapter<String> chordAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, keys);
        chordAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chordSpinner.setAdapter(chordAdapter);

        ArrayAdapter<String> scaleAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, scales);
        scaleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        scaleSpinner.setAdapter(scaleAdapter);

        ArrayAdapter<String> keySignatureAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, keySignatures);
        keySignatureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        keySignatureSpinner.setAdapter(keySignatureAdapter);

        // Nastavení akce po kliknutí na tlačítko "Potvrdit"
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        // Generování nové otázky při spuštění aktivity
        generateNewQuestion();
    }

    // Metoda pro generování nové otázky
    private void generateNewQuestion() {
        Random rand = new Random();

        // Náhodný výběr tónu
        String selectedKey = keys[rand.nextInt(keys.length)];

        // Náhodný výběr key signature
        String selectedKeySignature = keySignatures[rand.nextInt(keySignatures.length)];

        // Náhodný výběr stupnice
        int selectedScaleIndex = rand.nextInt(scales.length);

        // Získání indexu náhodného akordu
        int randomChordIndex = rand.nextInt(chords[selectedScaleIndex].length);
        String randomChord = chords[selectedScaleIndex][randomChordIndex];

        // Sestavení textu otázky
        String question = "Jaký je " + (randomChordIndex + 1) + ". akord v " +
                selectedKey + selectedKeySignature + (selectedScaleIndex == 0 ? "" : scales[selectedScaleIndex].substring(0, 1)) + " stupnici?";
        questionTextView.setText(question);

        // Nastavení správné odpovědi do correctAnswerTextView
        correctAnswerTextView.setText(getString(R.string.default_correct_answer) + randomChord);
    }

    // Metoda pro kontrolu odpovědi
    private void checkAnswer() {
        String selectedKey = keys[chordSpinner.getSelectedItemPosition()];
        int selectedScaleIndex = scaleSpinner.getSelectedItemPosition();
        String selectedKeySignature = keySignatureSpinner.getSelectedItem().toString();

        // Získání správné odpovědi pro danou kombinaci tónu, stupnice a key signature
        String correctChord = chords[selectedScaleIndex][chordSpinner.getSelectedItemPosition()];

        // Sestavení odpovědi uživatele
        String userAnswer = selectedKey + selectedKeySignature + (selectedScaleIndex == 0 ? "" : scales[selectedScaleIndex].substring(0, 1));

        // Kontrola odpovědi
        if (userAnswer.equals(correctChord)) {
            Toast.makeText(this, "Správně!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Špatně!", Toast.LENGTH_SHORT).show();
        }

        // Zobrazení správné odpovědi pro nápovědu
        correctAnswerTextView.setVisibility(View.VISIBLE);
    }
}
