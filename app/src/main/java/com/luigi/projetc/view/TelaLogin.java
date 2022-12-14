package com.luigi.projetc.view;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.luigi.projetc.R;
import com.luigi.projetc.controller.UserController;

public class TelaLogin extends AppCompatActivity {

    private TextView text_tela_cadastro;
    private EditText edit_email, edit_senha;
    private Button button_entrar;
    private ProgressBar progressBar;
    private UserController userController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        init();

        text_tela_cadastro.setOnClickListener(view -> {

            Intent intent = new Intent(TelaLogin.this, TelaCadastro.class);
            intent.putExtra("id_user", 0);
            startActivity(intent);
        });

        button_entrar.setOnClickListener(view -> {
            String email = edit_email.getText().toString();
            String senha = edit_senha.getText().toString();

            if (email.isEmpty() || senha.isEmpty()) {
                Snackbar snackbar = Snackbar.make(view, "Preencha todos os campos", Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(Color.WHITE);
                snackbar.setTextColor(Color.BLACK);
                snackbar.show();
            } else {
                autenticarUsuario(view);
            }
        });
    }

    private void autenticarUsuario(View view) {

        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();

        userController.logarUsuario(email, senha, task -> {
            if (task.isSuccessful()) {
                progressBar.setVisibility(View.VISIBLE);
                new Handler().postDelayed(() -> abrirMainActivity(), 2000);
            } else {
                String error = task.getException().getMessage();

                Snackbar snackbar = Snackbar.make(view, error == null ? "Ocorreu um erro inexperado!" : error, Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(Color.WHITE);
                snackbar.setTextColor(Color.BLACK);
                snackbar.show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser usurarioAtual = FirebaseAuth.getInstance().getCurrentUser();
        if (usurarioAtual != null) {
            abrirMainActivity();
        }
    }

    private void abrirMainActivity() {
        Intent intent = new Intent(TelaLogin.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void init() {
        userController = new UserController();
        text_tela_cadastro = findViewById(R.id.text_tela_cadastro);
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        button_entrar = findViewById(R.id.bt_entrar);
        progressBar = findViewById(R.id.progressbar);
    }

}