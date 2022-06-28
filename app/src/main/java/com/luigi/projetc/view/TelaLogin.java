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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.luigi.projetc.R;

public class TelaLogin extends AppCompatActivity {

    private TextView text_tela_cadastro;
    private EditText edit_email, edit_senha;
    private Button button_entrar;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        IniciarComponentes();

        text_tela_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TelaLogin.this, TelaCadastro.class);
                intent.putExtra("id_user",0);
                startActivity(intent);
            }
        });

        button_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = edit_email.getText().toString();
                String senha = edit_senha.getText().toString();


                if (email.isEmpty() || senha.isEmpty()){
                    String mensagens_status = "Preencha todos os campos";
                    Snackbar snackbar = Snackbar.make(view, mensagens_status, Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }else {
                    AutenticarUsuario(view);
                }
            }
        });
    }

    private void AutenticarUsuario(View view){

        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    progressBar.setVisibility(View.VISIBLE);


                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            MainActvity();
                        }
                    },2000);
                }else {
                    String error;

                    try {
                        throw task.getException();
                    }catch (Exception e){
                        error = "Erro no login";
                    }
                    Snackbar snackbar = Snackbar.make(view,error, Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser usurarioAtual = FirebaseAuth.getInstance().getCurrentUser();

        if (usurarioAtual != null){
            MainActvity();
        }


    }

    private void MainActvity(){
        Intent intent = new Intent(TelaLogin.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void IniciarComponentes(){
        text_tela_cadastro = findViewById(R.id.text_tela_cadastro);
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        button_entrar = findViewById(R.id.bt_entrar);
        progressBar = findViewById(R.id.progressbar);
    }

}