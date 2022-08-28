package com.luigi.projetc.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.luigi.projetc.R;

public class TelaRegistroAlimento extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tela_registro_alimento, container, false);;

        Button bt_entrar = (Button) view.findViewById(R.id.bt_entrar) ;
        bt_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity().getApplicationContext(), TelaSelecionarAlimento.class);
                intent.putExtra("id_user",0);
                startActivity(intent);
            }
        });

        Button bt_entrar2 = (Button) view.findViewById(R.id.bt_entrar2) ;
        bt_entrar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), TelaSelecionarAlimento.class);
                intent.putExtra("id_user",0);
                startActivity(intent);
            }
        });

        Button bt_entrar3 = (Button) view.findViewById(R.id.bt_entrar3) ;
        bt_entrar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), TelaSelecionarAlimento.class);
                intent.putExtra("id_user",0);
                startActivity(intent);
            }
        });
        return view;
    }

}


