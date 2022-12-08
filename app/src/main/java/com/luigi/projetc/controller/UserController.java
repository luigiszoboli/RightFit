package com.luigi.projetc.controller;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class UserController {

    public UserController() {
    }

    public void cadastrarUsuario(String nome, String email, String senha, OnCompleteListener onCompleteListener) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(task -> {

            if (!task.isSuccessful()) {
                onCompleteListener.onComplete(task);
                return;
            }

            // Atualizando usuario no firebase
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(nome)
                    .build();
            user.updateProfile(profileUpdates).addOnCompleteListener(onCompleteListener);
        });


    }

    public void logarUsuario(String email, String senha, OnCompleteListener onCompleteListener) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener(onCompleteListener);
    }

}