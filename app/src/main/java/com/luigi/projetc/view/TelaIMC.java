package com.luigi.projetc.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TelaIMC extends Fragment {

    private EditText editPeso;
    private EditText editAltura;
    private Button buttonCalcular;
    private TextView tvImc;
    private IMCController imcController;
    ExecutorService mExecutor = Executors.newSingleThreadExecutor();

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
    }

    private void setOnClicks() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        buttonCalcular.setOnClickListener(v -> {
            String peso = editPeso.getText().toString();
            String altura = editAltura.getText().toString();

            if(peso.equals("") || altura.equals("")){
                Snackbar.make(getView(), "Preencha os campos de altura e peso", Snackbar.LENGTH_LONG)
                        .show();
                return;
            }

            Runnable backgroundRunnable = () -> {
                imcController.insertImc(altura, peso, user.getUid());
            };

            Double imc = calculaIMC(Double.parseDouble(peso), Double.parseDouble(altura));
            tvImc.setText(imc.toString());
            mExecutor.execute(backgroundRunnable);
        });
    }

    public Double calculaIMC (Double peso, Double altura) {
        Double imc = (peso / (altura * altura)) * 100;
        return imc;
    }

    private void init(){
        editPeso = getView().findViewById(R.id.text_peso);
        editAltura = getView().findViewById(R.id.text_altura);
        buttonCalcular = getView().findViewById(R.id.bt_calcular);
        tvImc = getView().findViewById(R.id.text_image_imc);
        imcController = new IMCController(RightFitDatabase.getDatabase(getContext()).imcDao());
    }
}
