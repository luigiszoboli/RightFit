package com.luigi.projetc.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.luigi.projetc.R;
import com.luigi.projetc.controller.AlimentoController;

import java.util.ArrayList;

public class TelaRegistroAlimento extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_registro_alimento);
        listView = findViewById(R.id.listView);
    }
    public void adicionarAlimento(View v){
        Intent intent = new Intent(this, TelaAdicionarAlimento.class);
        intent.putExtra("id_alimente", 0);
        startActivity(intent);
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