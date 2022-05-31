package com.luigi.projetc.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.luigi.projetc.R;
import com.luigi.projetc.controller.AlimentoController;
import com.luigi.projetc.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TelaSelecionarAlimento extends AppCompatActivity {

    ListView listView;

    Connection connection;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_tela_selecionar_alimento);
        listView = findViewById(R.id.listView);
        {
            try {
                connection = ConnectionHelper.getConnection();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public void adicionarAlimento(View v){
        Intent intent = new Intent(this, TelaAdicionarAlimento.class);
        intent.putExtra("id_alimente", 0);
        startActivity(intent);
    }

    public void getAlimento(View v) {
        ArrayList<String> listaAlimentos = new ArrayList<String>();
        try {

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select Alimento from tabela_alimentos;");
            while (result.next()) {
                listaAlimentos.add(result.getString("Alimento"));
            }

            statement.close();
            result.close();

            System.out.println(listaAlimentos);

        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }

        }


    @Override
    protected void onStart() {
        super.onStart();
        AlimentoController alimentoController = new AlimentoController(this);
        ArrayList<String> dataset= alimentoController.ListNomesAlimentos();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                dataset
        );
        listView.setAdapter(adapter);
    }


}