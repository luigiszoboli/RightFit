package com.luigi.projetc.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luigi.projetc.R;
import com.luigi.projetc.controller.TelaInicialController;
import com.luigi.projetc.database.RightFitDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TelaInicial extends Fragment {

    private TextView textViewMeta;
    private TextView textViewCalorias;
    private TextView textViewGorduras;
    private TextView textViewProteinas;
    private TextView textViewCarboidratos;
    private TelaInicialController telaInicialController;
    private final ExecutorService mExecutor = Executors.newSingleThreadExecutor();
    private final Handler uiThread = new Handler(Looper.getMainLooper());


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tela_inicial, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        getDadosDaTela();
    }

    private void getDadosDaTela(){
        Runnable runnable = () -> {
            Double carboidratos = telaInicialController.getCarboidratosIngeridos();
            Double gorduras = telaInicialController.getGorduras();
            Double proteinas = telaInicialController.getProteinas();
            Integer calorias = telaInicialController.getCalorias();
            Integer meta = telaInicialController.getMeta();

            uiThread.post(() -> {
                if(carboidratos != null){
                    textViewCarboidratos.setText(carboidratos.toString());
                }

                if(gorduras != null){
                    textViewGorduras.setText(gorduras.toString());
                }

                if(proteinas != null){
                    textViewProteinas.setText(proteinas.toString());
                }

                if(calorias != null){
                    textViewCalorias.setText(calorias.toString());
                }

                if(meta != null) {
                    textViewMeta.setText(meta.toString());
                }
            });
        };
        mExecutor.execute(runnable);
    }

    private void init(){
        textViewMeta = getView().findViewById(R.id.editText_meta_inicio);
        textViewCalorias = getView().findViewById(R.id.editText_calorias_ingeridas);
        textViewProteinas = getView().findViewById(R.id.editText_proteinas_ingeridas);
        textViewGorduras = getView().findViewById(R.id.editText_gorduras_ingeridas);
        textViewCarboidratos = getView().findViewById(R.id.editText_carboidratos_ingeridas);
        telaInicialController = new TelaInicialController(
                RightFitDatabase.getDatabase(getContext()).dietaDao(),
                RightFitDatabase.getDatabase(getContext()).metaDao()
        );
    }
}