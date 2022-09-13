package com.luigi.projetc.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.luigi.projetc.R;
import com.luigi.projetc.controller.AlimentoController;
import com.luigi.projetc.database.RightFitDatabase;

import java.util.ArrayList;

public class TelaSelecionarAlimento extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_selecionar_alimento);
        listView = findViewById(R.id.listView);
    }
    public void adicionarAlimento(View v){
        Intent intent = new Intent(this, TelaAdicionarAlimento.class);
        intent.putExtra("id_alimente", 0);
        startActivity(intent);
    }

    public void getAlimento(View v) {
        ArrayList<String> listaAlimentos = new ArrayList<String>();

        RightFitDatabase.getDatabase(getApplicationContext()).alimentoDao().getAllAlimentos().observe(this, alimentos -> {
            Log.e("Alimentos", alimentos != null ? alimentos.toString() : "Nada Cadastrado");
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        AlimentoController alimentoController = new AlimentoController(this);
        ArrayList<String> dataset= alimentoController.ListNomesAlimentos();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                dataset
        );
        listView.setAdapter(adapter);
    }


}