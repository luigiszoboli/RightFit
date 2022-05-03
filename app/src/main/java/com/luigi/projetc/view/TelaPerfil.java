package com.luigi.projetc.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.luigi.projetc.R;

public class TelaPerfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil);

        getSupportActionBar().hide();
    }
}