package com.luigi.projetc.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.luigi.projetc.R;

public class TelaRegistroAlimento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_tela_registro_alimento);
    }
}