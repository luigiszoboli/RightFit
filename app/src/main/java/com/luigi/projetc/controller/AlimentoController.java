package com.luigi.projetc.controller;

import android.content.Context;

import com.luigi.projetc.model.Alimento;
import com.luigi.projetc.model.AlimentoDao;

import java.util.ArrayList;

public class AlimentoController {
    Context mContext;
    AlimentoDao alimentoDao;
    public AlimentoController(Context c) {
        mContext = c;
        alimentoDao = new AlimentoDao(c);
    }

    public Alimento adicionarNovoAlimento(Alimento a){
        return alimentoDao.inserirAlimento(a);
    }

    public ArrayList<Alimento> listAlimentos(){
        return alimentoDao.getListAlimentos();
    }
    public ArrayList<String> ListNomesAlimentos(){
        ArrayList<String> result = new ArrayList<String>();
        for (Alimento alimento: this.listAlimentos()) {
            result.add(alimento.getNome());
        }
        return result;
    }
}
