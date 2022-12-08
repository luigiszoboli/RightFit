package com.luigi.projetc.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.luigi.projetc.R;
import com.luigi.projetc.controller.AlimentoController;
import com.luigi.projetc.database.RightFitDatabase;


public class TelaSelecionarAlimento extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private AlimentoController alimentoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_selecionar_alimento);
        init();
        setObservables();
    }

    private void setObservables() {
        alimentoController.listAlimentos().observe(this, alimentos -> {
            adapter.setAlimentos(alimentos);
        });
    }

    private void init(){
        Intent intentExtras = getIntent();
        String periodo = intentExtras.getStringExtra("periodo");
        String data = intentExtras.getStringExtra("data");

        alimentoController = new AlimentoController(RightFitDatabase.getDatabase(getBaseContext()).alimentoDao());
        adapter = new RecyclerViewAdapter();
        recyclerView = findViewById(R.id.listView);
        recyclerView.setLayoutManager( new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        //Redirecionando para tela de adicionar alimentos ao clicar em um item da lista
        adapter.setOnClickItem(alimentoId -> {
            Intent intent = new Intent(this, TelaAdicionarAlimento.class);
            intent.putExtra("alimento_id", alimentoId);
            intent.putExtra("periodo", periodo);
            intent.putExtra("data", data);
            startActivity(intent);
        });
    }

}
