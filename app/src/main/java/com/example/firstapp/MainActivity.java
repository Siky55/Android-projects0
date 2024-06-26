package com.example.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this); // vykreslovani obsahu pres navigacni a informacni listu
        setContentView(R.layout.activity_main);
        /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets sy0stemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        }); */
        setTitle("Home");
    }
    public void menuButton(View v){

        if(v.getId() == R.id.metronomeBtn2) startActivity(new Intent(this, MetronomeActivity.class));
        else if(v.getId() == R.id.xylophoneBtn2) startActivity(new Intent(this, XylophoneActivity.class));
        else if(v.getId() == R.id.circleBtn2) startActivity(new Intent(this, CircleActivity.class));
        else if(v.getId() == R.id.settingsBtn2) startActivity(new Intent(this, SettingsActivity.class));

    }
}