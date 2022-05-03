package com.luigi.projetc.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.luigi.projetc.R;

public class TelaPerfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil);

        getSupportActionBar().hide();
    }
}