package com.luigi.projetc.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.luigi.projetc.R;
import com.luigi.projetc.controller.RegistroAlimentoController;
import com.luigi.projetc.database.RightFitDatabase;
import com.luigi.projetc.database.enums.PeriodoEnum;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TelaRegistroAlimento extends Fragment {

    private static final String TAG = "TelaRegistroAlimento";
    private Button bt_entrar, bt_entrar2, bt_entrar3;
    private TextView textViewData;
    private RegistroAlimentoController registroAlimentoController;
    private ImageView imageViewDiaAnterior;
    private ImageView imageViewDiaPosterior;
    private TextView textViewCaloriasIngeridas;
    private TextView textViewRestantes;
    private EditText editTextMeta;
    ExecutorService mExecutor = Executors.newSingleThreadExecutor();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tela_registro_alimento, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view2, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view2, savedInstanceState);
        initComponents();
        setOnClickButtons();
        setObservables();
    }

    private void setObservables() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        registroAlimentoController.dataAtualObservable().observe(getViewLifecycleOwner(), data -> {
            textViewData.setText(data);
        });
        imageViewDiaAnterior.setOnClickListener(view -> {
            registroAlimentoController.diaAnterior();
            getDadosDaTela(userId);
        });
        imageViewDiaPosterior.setOnClickListener(view -> {
            registroAlimentoController.diaPosterior();
            getDadosDaTela(userId);
        });

        registroAlimentoController.currentCaloriasObservable().observe(getViewLifecycleOwner(), calorias -> {
            textViewCaloriasIngeridas.setText(calorias.toString());
        });

        registroAlimentoController.currentMetaObservable().observe(getViewLifecycleOwner(), meta -> {
            editTextMeta.setText(String.valueOf(meta));
        });

        editTextMeta.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                saveMeta(v.getText().toString());
                getDadosDaTela(userId);
                return true;
            }
            return false;
        });

        registroAlimentoController.restantesObservable().observe(getViewLifecycleOwner(), restantes -> {
           textViewRestantes.setText(restantes.toString());
        });

        getDadosDaTela(userId);
    }

    @Override
    public void onResume() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        getDadosDaTela(userId);
        super.onResume();
    }

    private void saveMeta(String meta) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        Runnable backgroundRunnable = () -> {
            registroAlimentoController.saveMeta(Integer.parseInt(meta), userId);
        };
        mExecutor.execute(backgroundRunnable);
    }

    private void getDadosDaTela(String userId){
        Runnable backgroundRunnable = () -> {
            registroAlimentoController.getDadosParaTela(userId);
        };
        mExecutor.execute(backgroundRunnable);
    }

    private void setOnClickButtons() {
        bt_entrar.setOnClickListener(view -> {
            Intent intent = new Intent(getView().getContext(), TelaSelecionarAlimento.class);
            intent.putExtra("periodo", PeriodoEnum.CAFE_DA_MANHA.name());
            startActivity(intent);
        });
        bt_entrar2.setOnClickListener(view -> {
            Intent intent = new Intent(getView().getContext(), TelaSelecionarAlimento.class);
            intent.putExtra("periodo", PeriodoEnum.ALMOCO.name());
            startActivity(intent);
        });
        bt_entrar3.setOnClickListener(view -> {
            Intent intent = new Intent(getView().getContext(), TelaSelecionarAlimento.class);
            intent.putExtra("periodo", PeriodoEnum.JANTA.name());
            startActivity(intent);
        });
    }

    private void initComponents() {
        registroAlimentoController = new RegistroAlimentoController(
                RightFitDatabase.getDatabase(getView().getContext()).dietaDao(),
                RightFitDatabase.getDatabase(getView().getContext()).metaDao()
        );
        bt_entrar = getView().findViewById(R.id.bt_entrar);
        bt_entrar2 = getView().findViewById(R.id.bt_entrar2);
        bt_entrar3 = getView().findViewById(R.id.bt_entrar3);
        textViewData = getView().findViewById(R.id.textView_data);
        imageViewDiaAnterior = getView().findViewById(R.id.image_view_dia_anterior);
        imageViewDiaPosterior = getView().findViewById(R.id.image_view_dia_posterior);
        textViewCaloriasIngeridas = getView().findViewById(R.id.text_view_calorias_ingeridas);
        textViewRestantes = getView().findViewById(R.id.editText_calorias_restantes);
        editTextMeta = getView().findViewById(R.id.editText_meta);
    }
}
