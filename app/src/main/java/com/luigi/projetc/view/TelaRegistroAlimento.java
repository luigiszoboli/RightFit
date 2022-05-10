package com.luigi.projetc.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.luigi.projetc.R;

public class TelaRegistroAlimento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_registro_alimento);
    }
    public void adicionarAlimento(View v){
        Intent intent = new Intent(this, TelaAdicionarAlimento.class);
        intent.putExtra("id_alimente", 0);
        startActivity(intent);
    }
}