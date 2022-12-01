package com.luigi.projetc.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.luigi.projetc.R;


public class TelaConfiguracao extends Fragment {

    private TextView nomeUser, emailUser;
    private Button bt_deslogar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_tela_configuracao, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InciarComponents();
    }

    private void InciarComponents(){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        nomeUser = getView().findViewById(R.id.text_nome_user);
        emailUser = getView().findViewById(R.id.text_nome_email_user);
        bt_deslogar = getView().findViewById(R.id.bt_deslogar);

        nomeUser.setText(firebaseUser.getDisplayName());
        emailUser.setText(firebaseUser.getEmail());

        bt_deslogar.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getActivity(), TelaLogin.class));
            getActivity().finish();
        });
    }

}