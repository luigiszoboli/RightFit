package com.luigi.projetc.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.luigi.projetc.R;
import com.luigi.projetc.controller.AdicionarAlimentoController;
import com.luigi.projetc.controller.AlimentoController;
import com.luigi.projetc.database.RightFitDatabase;
import com.luigi.projetc.database.entities.DietaEntity;
import com.luigi.projetc.database.enums.PeriodoEnum;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TelaAdicionarAlimento extends AppCompatActivity {

    private AlimentoController alimentoController;
    private AdicionarAlimentoController adicionarAlimentoController;
    private TextView textViewNome, textViewCalorias, textViewGorduras, textViewProteinas, textViewCarboidratos, textViewFibras;
    private EditText editTextQuantidade;
    private Button buttonAlimento;
    private Button buttonExcluir;
    private ImageView buttonBack;
    private DietaEntity dieta;
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
        String data = intentExtra.getStringExtra("data");
        boolean isUpdateScreen = getIntent().getBooleanExtra("update", false);
        String userId = firebaseUser.getUid();

        buttonAlimento.setOnClickListener(v -> {
            String quantidade = editTextQuantidade.getText().toString();
            //Verificando se o usuario digitou no campo
            if (quantidade.equals("") || Integer.parseInt(quantidade) <= 0) {
                View parentLayout = findViewById(android.R.id.content);
                Snackbar.make(parentLayout, "Insira uma quantidade!", Snackbar.LENGTH_LONG)
                        .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                        .show();
                return;
            }
            Runnable backgroundRunnable = () -> {
                //Inserindo alimento na Dieta
                if(isUpdateScreen && dieta != null){
                    dieta.setQuantidade(Integer.parseInt(quantidade));
                    adicionarAlimentoController.atualizarQuantidadeDoALimento(dieta);
                    return;
                }
                adicionarAlimentoController.adicionarAlimentoNaDieta(periodo, alimentoId, userId, Integer.parseInt(quantidade), data);
            };
            mExecutor.execute(backgroundRunnable);
            finish();
        });

        buttonExcluir.setOnClickListener(v -> {
            Runnable runnable = () -> {
                adicionarAlimentoController.excluirDieta(dieta);

                runOnUiThread(() -> {
                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "Alimento retirado da sua dieta.", Snackbar.LENGTH_LONG)
                            .show();
                });
            };
            mExecutor.execute(runnable);
        });

        buttonBack.setOnClickListener(v -> finish());
    }

    private void init() {
        boolean isUpdateScreen = getIntent().getBooleanExtra("update", false);
        alimentoController = new AlimentoController(RightFitDatabase.getDatabase(getBaseContext()).alimentoDao());
        adicionarAlimentoController = new AdicionarAlimentoController(RightFitDatabase.getDatabase(getBaseContext()).dietaDao());
        textViewNome = findViewById(R.id.edit_alimento);
        textViewCalorias = findViewById(R.id.edit_calorias);
        textViewGorduras = findViewById(R.id.text_gorduras);
        textViewProteinas = findViewById(R.id.text_proteinas);
        textViewCarboidratos = findViewById(R.id.text_carboidratos);
        textViewFibras = findViewById(R.id.text_fibras);
        buttonAlimento = findViewById(R.id.button);
        buttonExcluir = findViewById(R.id.button_excluir);
        editTextQuantidade = findViewById(R.id.edit_quantidade);
        buttonBack = findViewById(R.id.imageview_arrowback);

        if(isUpdateScreen){
            buttonAlimento.setText("Atualizar");
            buttonExcluir.setVisibility(View.VISIBLE);
        }
    }

    private void setObservables() {
        int alimentoId = getIntent().getIntExtra("alimento_id", 0);
        boolean isUpdateScreen = getIntent().getBooleanExtra("update", false);
        String periodo = getIntent().getStringExtra("periodo");

        alimentoController.getAlimentoById(alimentoId).observe(this, alimento -> {
            textViewCalorias.setText(getBaseContext().getString(R.string.Calorias) + ": " + alimento.getCaloria());
            textViewNome.setText(alimento.getNome());
            textViewGorduras.setText(getBaseContext().getString(R.string.Gorduras) + ": " + alimento.getGorduras());
            textViewProteinas.setText(getBaseContext().getString(R.string.Proteinas) + ": " + alimento.getProteinas());
            textViewCarboidratos.setText(getBaseContext().getString(R.string.Carboidratos) + ": " + alimento.getCarboidratos());
            textViewFibras.setText(getBaseContext().getString(R.string.Fibras) + ": " + alimento.getFibra());
        });

        if(isUpdateScreen){
            adicionarAlimentoController.getQuantidadeDoAlimento(alimentoId, FirebaseAuth.getInstance().getUid(), PeriodoEnum.valueOf(periodo)).observe(this, dietaRecebida -> {
                if(dietaRecebida == null || dietaRecebida.getQuantidade() == 0){
                    return;
                }
                dieta = dietaRecebida;
                editTextQuantidade.setText(String.valueOf(dietaRecebida.getQuantidade()));
            });
        }
    }
}
