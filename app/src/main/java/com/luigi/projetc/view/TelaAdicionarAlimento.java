package com.luigi.projetc.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.luigi.projetc.R;
import com.luigi.projetc.controller.AlimentoController;
import com.luigi.projetc.model.Alimento;

public class TelaAdicionarAlimento extends AppCompatActivity {

    AlimentoController alimentoController;
    EditText edNome,edCalorias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_adicionar_alimento);
        alimentoController = new AlimentoController(getApplicationContext());

        edNome= findViewById(R.id.edit_alimento);
        edCalorias= findViewById(R.id.edit_calorias);
    }

    public void adicionarAlimento(View v){
       Alimento a= alimentoController.adicionarNovoAlimento(new Alimento(edNome.getText().toString(),edCalorias.getText().toString() ));
    }
}