package com.luigi.projetc.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.luigi.projetc.R;
import com.luigi.projetc.controller.UserController;

public class TelaCadastro extends AppCompatActivity {

    private EditText edit_name, edit_email, edit_senha;
    private Button bt_cadastrar;
    String[] mensagens_status = {"Preencha todos os campos", "Cadastro realizado com sucesso"};
    String usuarioID;
    UserController userController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
        iniciarComponents();

        bt_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = edit_name.getText().toString();
                String email = edit_email.getText().toString();
                String senha = edit_senha.getText().toString();

                if (name.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(view, mensagens_status[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                } else {
                    cadastrarUser(view);

                }
            }
        });
    }

    public void cadastrarUser(View view) {

        String name = edit_name.getText().toString();
        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();

        userController.cadastrarUsuario(name, email,senha, task -> {
            if (task.isSuccessful()) {
                Snackbar snackbar = Snackbar.make(view, mensagens_status[1], Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(Color.WHITE);
                snackbar.setTextColor(Color.BLACK);
                snackbar.show();
            } else {
                String error;
                try {
                    throw task.getException();

                } catch (FirebaseAuthWeakPasswordException exception) {
                    error = "Digite uma senha com no mínimo 6 caracteres";

                } catch (FirebaseAuthUserCollisionException exception) {
                    error = "Uma conta com este e-mail já foi cadastrada";

                } catch (FirebaseAuthInvalidCredentialsException exception) {
                    error = "E-mail inválido";

                } catch (Exception exception) {
                    error = "Erro ao cadastrar usuário";
                }

                Snackbar snackbar = Snackbar.make(view, error, Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(Color.WHITE);
                snackbar.setTextColor(Color.BLACK);
                snackbar.show();
            }
        });
    }

    private void iniciarComponents() {
        userController = new UserController();
        edit_name = findViewById(R.id.edit_nome);
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        bt_cadastrar = findViewById(R.id.bt_cadastrar);
    }
}