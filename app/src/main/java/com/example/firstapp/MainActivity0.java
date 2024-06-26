package com.example.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity0 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // volani metody tridy Activity, kde se nachazi kod k sestaveni aktivity
        // savedInstanceState uchovava stav aktivity, pokud se vytvari poprve, ma hodnotu null
        setContentView(R.layout.activity_main0); // nastavuje (XML) layout pro danou aktivitu
        // trida R obsahuje reference na vsechny zdrojove soubory aplikace (layouty, obrazky, texty...)
        setTitle("Home"); //oznaceni aktivity
    }

//        Intent i = new Intent(this,SettingsActivity.class); // vytvoreni Intentu ("mostu") mezi danymi aktivitami
//        startActivity(i); //presun na danou aktivitu (parametry konstruktoru tridy Intent: odkud,kam)
//        //i.putExtra("key","Ahoj"); // prenos dat za pomoci klicove hodnoty

    public void menuButton(View v){

        if(v.getId() == R.id.metronomeBtn) startActivity(new Intent(this, MetronomeActivity.class));
        else if(v.getId() == R.id.xylophoneBtn) startActivity(new Intent(this, XylophoneActivity.class));
        else if(v.getId() == R.id.circlePanel) startActivity(new Intent(this, CircleActivity.class));
        else if(v.getId() == R.id.settingsBtn) startActivity(new Intent(this, MainActivity.class));

    }

}
