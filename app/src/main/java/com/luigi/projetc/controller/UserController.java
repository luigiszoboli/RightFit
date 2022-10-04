package com.luigi.projetc.controller;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class UserController {

    public UserController() {}

    public void cadastrarUsuario(String nome, String email, String senha, OnCompleteListener onCompleteListener){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(task -> {

            if(!task.isSuccessful()){
                onCompleteListener.onComplete(task);
                return;
            }

            FirebaseFirestore db = FirebaseFirestore.getInstance();

            Map<String,Object> usuarios = new HashMap<>();
            usuarios.put("nome", nome);

            String usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

            DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);
            documentReference.set(usuarios).addOnSuccessListener(unused -> Log.d("db","Sucesso ao salvar os dados"))
                    .addOnFailureListener(e -> Log.d("db_error", "Erro ao salvar os dados" + e));
            onCompleteListener.onComplete(task);
        });


    }

    public void logarUsuario(String email, String senha, OnCompleteListener onCompleteListener){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener(onCompleteListener);
    }

}