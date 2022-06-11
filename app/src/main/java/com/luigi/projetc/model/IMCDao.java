package com.luigi.projetc.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.luigi.projetc.R;
import com.luigi.projetc.controller.IMCController;

public class IMCDao extends AppCompatActivity {

    private Button bt_calcular;
    private TextView text_peso, text_altura, text_imc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tela_imc);
        IniciarComponents();
    }

        public void calculaIMC(View v) {
        Intent i = new Intent(this, IMCController.class);
        Double peso = Double.parseDouble(text_peso.getText().toString());  //pega o texto transforma em string e depois em double
        Double altura = Double.parseDouble(text_altura.getText().toString());
        i.putExtra("peso", peso);
        i.putExtra("altura", altura);
        startActivity(i);

    }

    private void IniciarComponents() {

        text_peso = findViewById(R.id.text_peso);
        text_altura = findViewById(R.id.text_altura);
        text_imc = findViewById(R.id.text_imc);
        bt_calcular = findViewById(R.id.bt_calcular);
    }
}