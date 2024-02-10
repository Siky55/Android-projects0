package com.example.firstapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Button btnM2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // volani metody tridy Activity, kde se nachazi kod k sestaveni aktivity
        // savedInstanceState uchovava stav aktivity, pokud se vytvari poprve, ma hodnotu null
        setContentView(R.layout.activity_main); // nastavuje (XML) layout pro danou aktivitu
        // trida R obsahuje reference na vsechny zdrojove soubory aplikace (layouty, obrazky, texty...)
        setTitle("Home"); //oznaceni aktivity

        btnM2=(Button) findViewById(R.id.btnM2);
    }
    public void ZmenaTextu(View v){
        String jmenoTlacitka = v.getTag().toString();
        Log.d("kliknuti","Bylo kliknuto na tlacitko: "+jmenoTlacitka);

        Button b = (Button) v;
        b.setText("Clicked");
        TextView txt1 = findViewById(R.id.txt1); // meneni objektu z TextBoxu na Tlacitko kvuli metodam, ktere je potreba pouzit
    }

    public void launchSettings(View v){
        Intent i = new Intent(this,SettingsActivity.class); // vytvoreni Intentu ("mostu") mezi danymi aktivitami
        startActivity(i); //presun na danou aktivitu (parametry konstruktoru tridy Intent: odkud,kam)
        //i.putExtra("key","Ahoj");
    }

    public void launchDrawing(View v){
        Intent i = new Intent(this,DrawingActivity.class);
        startActivity(i);
    }

}