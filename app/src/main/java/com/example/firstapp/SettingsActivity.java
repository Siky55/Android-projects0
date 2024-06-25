package com.example.firstapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends Activity {
    private Button btn; //lepsi zpusob prace s prvky je inicializace jejich promennych na zacatku pro celou tridu/aktivitu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings); // pripojeni k souboru XML s layoutem
        setTitle("Settings"); //nazev aktivity

        Intent i = getIntent(); // Prijmani dat z intentu
        String message = i.getStringExtra("key"); // lepe String message = getIntent().getStringExtra("key");

//        btn = findViewById(R.id.btnS2); // starsi, ale stale mozny zpusob prirazeni metody onClick tlacitku
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //kod
//            }
//        });

    }

    public void backToMenu(View v)
    {
        Intent i2 = new Intent(this, MainActivity0.class); // vytvoreni Intentu (mostu) mezi danymi aktivitami
        startActivity(i2);
    }

    public void bambus(View v){
        // ((Button)findViewById(R.id.btnS2)).setText(""); // lepsi a kratsi moznost prevadeni na jiny typ objektu nez pres deklaraci a inicializaci promenne Button (viz MainActivity - zmenaTextu)
        String TxtFrmBtn = ((Button)findViewById(R.id.btnS2)).getText().toString();
        ((TextView)findViewById(R.id.txt2)).setText(TxtFrmBtn);
    }
        public void ahoj(View v){
        String TxtFrmBtn = ((Button)findViewById(R.id.btnS3)).getText().toString();
        ((TextView)findViewById(R.id.txt2)).setText(TxtFrmBtn);
    }

    public void text(View v){
        String input = ((EditText)findViewById(R.id.txt3)).getText().toString();
        Log.d("info", input);
        ((TextView)findViewById(R.id.txt2)).setText(input);
        Toast.makeText(this,"Nastavili jste vlastní text", Toast.LENGTH_LONG).show();
    }
}

