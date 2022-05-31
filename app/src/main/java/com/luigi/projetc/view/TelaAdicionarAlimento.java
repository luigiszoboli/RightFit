package com.luigi.projetc.view;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.luigi.projetc.R;
import com.luigi.projetc.controller.AlimentoController;
import com.luigi.projetc.model.Alimento;
import com.luigi.projetc.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TelaAdicionarAlimento extends AppCompatActivity {

    AlimentoController alimentoController;
    EditText edNome, edCalorias;

    Connection connection = null;
    Statement statement = null;
    ResultSet result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_adicionar_alimento);
        alimentoController = new AlimentoController(getApplicationContext());
        getSupportActionBar().hide();
        edNome = findViewById(R.id.edit_alimento);
        edCalorias = findViewById(R.id.edit_calorias);
    }


    public void adicionarAlimento(View v) {
        try {
            Toast.makeText(this, result.getString(2), Toast.LENGTH_SHORT).show();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Alimento a = alimentoController.adicionarNovoAlimento(new Alimento(edNome.getText().toString(), edCalorias.getText().toString()));
    }


}