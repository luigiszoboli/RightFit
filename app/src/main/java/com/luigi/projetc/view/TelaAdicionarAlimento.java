package com.luigi.projetc.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.luigi.projetc.R;
import com.luigi.projetc.controller.AlimentoController;
import com.luigi.projetc.model.Alimento;

public class TelaAdicionarAlimento extends AppCompatActivity {

    AlimentoController alimentoController;
    TextView edNome, edCalorias;
    Button buttonAlimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_adicionar_alimento);
        alimentoController = new AlimentoController(getApplicationContext());
        edNome = findViewById(R.id.edit_alimento);
        edCalorias = findViewById(R.id.edit_calorias);
    }


    public void adicionarAlimento(View v) {
        // TODO Adicionar alimento
        Alimento a = alimentoController.adicionarNovoAlimento(new Alimento(edNome.getText().toString(), edCalorias.getText().toString()));
    }
}
