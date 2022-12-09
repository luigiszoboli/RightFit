package com.luigi.projetc.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.luigi.projetc.R;
import com.luigi.projetc.controller.AlimentoController;
import com.luigi.projetc.database.RightFitDatabase;
import com.luigi.projetc.database.entities.AlimentoEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TelaSelecionarAlimento extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private AlimentoController alimentoController;
    private EditText editTextSearchBox;
    ExecutorService mExecutor = Executors.newSingleThreadExecutor();
    Handler uiThread = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_selecionar_alimento);
        init();
        setObservables();
        adicionarEventoAoDigitar();
    }

    private void setObservables() {
        alimentoController.listAlimentos().observe(this, alimentos -> {
            adapter.setAlimentos(alimentos);
        });
    }

    private void adicionarEventoAoDigitar(){
        editTextSearchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Buscar alimento somente depois da terceira letra
                if(s.length() >= 3) {
                    buscarAlimento(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void buscarAlimento(String pesquisa){
        Runnable runnable = () -> {

            List<AlimentoEntity> alimentos = alimentoController.pesquisarAlimento(pesquisa);

            if(alimentos == null || alimentos.size() == 0){
                return;
            }

            uiThread.post(() -> {
                adapter.setAlimentos(alimentos);
            });
        };

        mExecutor.execute(runnable);
    }

    private void init(){
        Intent intentExtras = getIntent();
        String periodo = intentExtras.getStringExtra("periodo");
        String data = intentExtras.getStringExtra("data");

        alimentoController = new AlimentoController(RightFitDatabase.getDatabase(getBaseContext()).alimentoDao());
        editTextSearchBox = findViewById(R.id.edit_searchbox);
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
