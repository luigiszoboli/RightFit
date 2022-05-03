package com.luigi.projetc.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.luigi.projetc.R;

public class FormCadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        getSupportActionBar().hide();
    }
}