package com.luigi.projetc.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.luigi.projetc.R;


public class TelaConfiguracao extends Fragment {

    private TextView nomeUser, emailUser;
    private Button bt_deslogar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_tela_configuracao, container, false);
    }

    private void InciarComponents(){
        nomeUser = getView().findViewById(R.id.text_nome_user);
        emailUser = getView().findViewById(R.id.text_nome_email_user);
        bt_deslogar = getView().findViewById(R.id.bt_deslogar);
    }

}