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
import android.widget.ImageView;
import android.widget.TextView;

import com.luigi.projetc.R;
import com.luigi.projetc.controller.TelaInicialController;
import com.luigi.projetc.database.RightFitDatabase;

import java.text.DecimalFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TelaInicial extends Fragment {

    private TextView textViewMeta;
    private TextView textViewCalorias;
    private TextView textViewGorduras;
    private TextView textViewProteinas;
    private TextView textViewCarboidratos;
    private TextView textViewRestanteCalorias;
    private TextView textViewRestanteCarboidratos;
    private TextView textViewRestanteGorduras;
    private TextView textViewRestanteProteinas;
    private TextView textViewCarboidratosIndicados;
    private TelaInicialController telaInicialController;
    private ImageView diaAnterior;
    private ImageView diaPosterior;
    private TextView textViewData;
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
        setOnClicks();
        setObservables();
    }

    private void setObservables(){
        telaInicialController.dataAtualObservable().observe(getViewLifecycleOwner(), data -> {
            textViewData.setText(data);
        });
    }

    private void setOnClicks(){

        diaAnterior.setOnClickListener(v -> {
            telaInicialController.diaAnterior();
            getDadosDaTela();
        });

        diaPosterior.setOnClickListener(v -> {
            telaInicialController.diaPosterior();
            getDadosDaTela();
        });
    }

    private void getDadosDaTela(){
        Runnable runnable = () -> {
            DecimalFormat format = new DecimalFormat("#.#");
            Double carboidratos = telaInicialController.getCarboidratosIngeridos();
            Double gorduras = telaInicialController.getGorduras();
            Double proteinas = telaInicialController.getProteinas();
            Integer calorias = telaInicialController.getCalorias();
            Integer meta = telaInicialController.getMeta();

            uiThread.post(() -> {
                if(carboidratos != null){
                    textViewCarboidratos.setText(format.format(carboidratos));
                    if(meta != null){
                        int indicado = meta/2;
                        textViewCarboidratosIndicados.setText(String.valueOf(indicado));
                        Double restanteCarboidratos = indicado - carboidratos;
                        textViewRestanteCarboidratos.setText(restanteCarboidratos < 0 ? (format.format(restanteCarboidratos * -1))+" a mais" : format.format(restanteCarboidratos));
                    }

                }

                if(gorduras != null){
                    textViewGorduras.setText(format.format(gorduras));
                    Double gordurasRestantes = 22 - gorduras;
                    textViewRestanteGorduras.setText(gordurasRestantes < 0 ? (format.format(gordurasRestantes*-1))+" a mais" : format.format(gordurasRestantes));
                }

                if(proteinas != null){
                    textViewProteinas.setText(format.format(proteinas));
                }

                if(calorias != null){
                    textViewCalorias.setText(String.valueOf(calorias));
                    if(meta != null){
                        int caloriasRestantes = meta - calorias;
                        textViewRestanteCalorias.setText(caloriasRestantes < 0 ? (format.format(caloriasRestantes*-1))+" a mais" : format.format(caloriasRestantes ));
                    }
                }

                if(meta != null) {
                    textViewMeta.setText(format.format(meta));
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
                RightFitDatabase.getDatabase(getContext()).metaDao(),
                RightFitDatabase.getDatabase(getContext()).imcDao()
                );
        textViewRestanteCalorias = getView().findViewById(R.id.editText_restante);
        textViewRestanteGorduras = getView().findViewById(R.id.editText_gorduras_restantes);
        textViewRestanteCarboidratos = getView().findViewById(R.id.editText_carboidratos_restantes);
        textViewRestanteProteinas = getView().findViewById(R.id.editText_proteinas_restantes);
        textViewCarboidratosIndicados = getView().findViewById(R.id.editText_carboidratos_indicada);
        diaAnterior = getView().findViewById(R.id.image_view_dia_anterior_inicial);
        diaPosterior = getView().findViewById(R.id.image_view_dia_posterior_inicial);
        textViewData = getView().findViewById(R.id.textView_data_inicial);
    }
}