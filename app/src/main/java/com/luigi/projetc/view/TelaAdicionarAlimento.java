package com.luigi.projetc.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.luigi.projetc.R;
import com.luigi.projetc.controller.AdicionarAlimentoController;
import com.luigi.projetc.controller.AlimentoController;
import com.luigi.projetc.database.RightFitDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TelaAdicionarAlimento extends AppCompatActivity {

    private AlimentoController alimentoController;
    private AdicionarAlimentoController adicionarAlimentoController;
    private TextView textViewNome, textViewCalorias, textViewGorduras, textViewProteinas, textViewCarboidratos, textViewFibras;
    private EditText editTextQuantidade;
    private Button buttonAlimento;
    ExecutorService mExecutor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_adicionar_alimento);
        init();
        setObservables();
        setButtonOnClick();
    }

    private void setButtonOnClick() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Intent intentExtra = getIntent();
        int alimentoId = intentExtra.getIntExtra("alimento_id", 0);
        String periodo = intentExtra.getStringExtra("periodo");
        String userId = firebaseUser.getUid();

        buttonAlimento.setOnClickListener(v -> {
            String quantidade = editTextQuantidade.getText().toString();
            if (quantidade.equals("") || Integer.parseInt(quantidade) <= 0) {
                View parentLayout = findViewById(android.R.id.content);
                Snackbar.make(parentLayout, "Insira uma quantidade!", Snackbar.LENGTH_LONG)
                        .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                        .show();
                return;
            }
            Runnable backgroundRunnable = () -> {
                adicionarAlimentoController.adicionarAlimentoNaDieta(periodo, alimentoId, userId, Integer.parseInt(quantidade));
            };
            mExecutor.execute(backgroundRunnable);
            finish();
        });
    }

    private void init() {
        alimentoController = new AlimentoController(RightFitDatabase.getDatabase(getBaseContext()).alimentoDao());
        adicionarAlimentoController = new AdicionarAlimentoController(RightFitDatabase.getDatabase(getBaseContext()).dietaDao());
        textViewNome = findViewById(R.id.edit_alimento);
        textViewCalorias = findViewById(R.id.edit_calorias);
        textViewGorduras = findViewById(R.id.text_gorduras);
        textViewProteinas = findViewById(R.id.text_proteinas);
        textViewCarboidratos = findViewById(R.id.text_carboidratos);
        textViewFibras = findViewById(R.id.text_fibras);
        buttonAlimento = findViewById(R.id.button);
        editTextQuantidade = findViewById(R.id.edit_quantidade);
    }

    private void setObservables() {
        int alimentoId = getAlimentoIdFromIntent();
        alimentoController.getAlimentoById(alimentoId).observe(this, alimento -> {
            textViewCalorias.setText(getBaseContext().getString(R.string.Calorias) + ": " + alimento.getCaloria());
            textViewNome.setText(alimento.getNome());
            textViewGorduras.setText(getBaseContext().getString(R.string.Gorduras) + ": " + alimento.getGorduras());
            textViewProteinas.setText(getBaseContext().getString(R.string.Proteinas) + ": " + alimento.getProteinas());
            textViewCarboidratos.setText(getBaseContext().getString(R.string.Carboidratos) + ": " + alimento.getCarboidratos());
            textViewFibras.setText(getBaseContext().getString(R.string.Fibras) + ": " + alimento.getFibra());
        });
    }

    private int getAlimentoIdFromIntent() {
        Intent intentExtra = getIntent();
        return intentExtra.getIntExtra("alimento_id", 0);
    }
}
