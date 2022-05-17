package com.luigi.projetc.model;

import android.widget.Button;
import android.widget.EditText;

import com.luigi.projetc.R;
import com.luigi.projetc.view.TelaCadastro;

public class Usuario {

    String nome;
    String senha;
    String email;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}