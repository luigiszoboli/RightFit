package com.luigi.projetc.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.luigi.projetc.R;

import java.text.DecimalFormat;

public class IMCController extends AppCompatActivity {


    private Button bt_calcular;
    private TextView text_peso, text_altura, text_imc;
    private Double peso, altura, imc;
    DecimalFormat decimalFormat = new DecimalFormat("##,###,###,##0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tela_imc);

        text_peso = findViewById(R.id.text_peso);
        text_altura = findViewById(R.id.text_altura);
        text_imc = findViewById(R.id.text_imc);
        bt_calcular = findViewById(R.id.bt_calcular);
        Bundle bundle = getIntent().getExtras();
        peso = bundle.getDouble("peso");
        altura = bundle.getDouble("altura");
        ImageView image_imc = findViewById(R.id.image_imc);
        imc = calculaIMC(peso, altura);

         /*if(imc < 18.5) {
                    image_imc.setImageResource(R.drawable.abaixopeso);
                     }else */
        if (imc < 24.9) {
            image_imc.setImageResource(R.drawable.normal);
        } else if (imc < 29) {
            image_imc.setImageResource(R.drawable.sobrepeso);
        } else if (imc < 34.9) {
            image_imc.setImageResource(R.drawable.obesidade1);
        } else if (imc < 39.9) {
            image_imc.setImageResource(R.drawable.obesidade2);
        } else {
            image_imc.setImageResource(R.drawable.obesidade3);

        }

    }


    public Double calculaIMC (Double peso, Double altura) {
        Double imc = peso / (altura * altura);
        return imc;
    }


}