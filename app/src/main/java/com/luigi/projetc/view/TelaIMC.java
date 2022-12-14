package com.luigi.projetc.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.luigi.projetc.R;
import com.luigi.projetc.controller.IMCController;
import com.luigi.projetc.database.RightFitDatabase;
import com.luigi.projetc.database.entities.ImcEntity;

import java.text.DecimalFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TelaIMC extends Fragment {

    private EditText editPeso;
    private EditText editAltura;
    private Button buttonCalcular;
    private TextView tvImc;
    private TextView tvImcTipo;
    private IMCController imcController;
    private ImageView diaAnterior;
    private ImageView diaPosterior;
    private TextView textViewData;
    ExecutorService mExecutor = Executors.newSingleThreadExecutor();
    Handler uiThread = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tela_imc, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        setOnClicks();
        setObservables();
    }

    private void setObservables() {
        imcController.dataAtualObservable().observe(getViewLifecycleOwner(), data -> {
            textViewData.setText(data);
        });
    }

    private void setOnClicks() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        buttonCalcular.setOnClickListener(v -> {
            DecimalFormat format = new DecimalFormat("#.##");
            String peso = editPeso.getText().toString();
            String altura = editAltura.getText().toString();

            //Verificando se usuario preencheu campos
            if(peso.equals("") || altura.equals("")){
                Snackbar.make(getView(), "Preencha os campos de altura e peso", Snackbar.LENGTH_LONG)
                        .show();
                return;
            }

            Runnable backgroundRunnable = () -> {
                //Inserindo imc no banco
                imcController.insertImc(altura, peso, user.getUid());
            };

            Double imc = calculaIMC(Double.parseDouble(peso), Double.parseDouble(altura));
            tvImc.setText("IMC: "+format.format(imc));
            tvImcTipo.setText(getTipoImc(imc));
            mExecutor.execute(backgroundRunnable);
        });
        diaAnterior.setOnClickListener(v -> {
            imcController.diaAnterior();
            buscaDadosImc();
        });
        diaPosterior.setOnClickListener(v -> {
            imcController.diaPosterior();
            buscaDadosImc();
        });
    }

    private void buscaDadosImc(){
        Runnable runnable = () -> {
            ImcEntity imc = imcController.getImc();

            uiThread.post(() -> {
                DecimalFormat format = new DecimalFormat("#.##");
                if(imc == null){
                    editPeso.setText("0.00");
                    editAltura.setText("0.00");
                    tvImc.setText("Calcule seu IMC!");
                    return;
                }
                Double peso = imc.getPesoKg();
                Double altura = imc.getAltura();
                Double imcCalculado = calculaIMC(peso, altura);


                tvImcTipo.setText(getTipoImc(imcCalculado));
                editPeso.setText(String.valueOf(peso));
                editAltura.setText(String.valueOf(altura));
                tvImc.setText("IMC: "+format.format(imcCalculado));
            });
        };
        mExecutor.execute(runnable);
    }

    private String getTipoImc(double imc){
        if (imc < 18.5) {
            return "Voce esta abaixo de seu peso";
        } else if(imc >= 18.5 && imc <= 24.9) {
            return "Voce esta no seu peso ideal";
        } else if(imc >= 25.0 && imc <= 29.9) {
            return "Você está acima de seu peso (sobrepeso)";
        } else if(imc >= 30.0 && imc <= 34.9) {
            return "Você está com Obesidade grau I.";
        }else{
            return "Você está com Obesidade grau II (severa)";
        }
    }

    public Double calculaIMC (Double peso, Double altura) {
        Double imc = peso / (altura * altura);
        return imc;
    }

    @Override
    public void onResume() {
        super.onResume();
        buscaDadosImc();
    }

    private void init(){
        editPeso = getView().findViewById(R.id.text_peso);
        editAltura = getView().findViewById(R.id.text_altura);
        buttonCalcular = getView().findViewById(R.id.bt_calcular);
        tvImc = getView().findViewById(R.id.text_image_imc);
        imcController = new IMCController(RightFitDatabase.getDatabase(getContext()).imcDao());
        diaAnterior = getView().findViewById(R.id.iv_dia_anterior);
        diaPosterior = getView().findViewById(R.id.iv_dia_posterior);
        textViewData = getView().findViewById(R.id.tv_data);
        tvImcTipo = getView().findViewById(R.id.text_imc);
    }
}
