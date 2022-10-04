package com.luigi.projetc.controller;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.luigi.projetc.database.RightFitDatabase;
import com.luigi.projetc.database.dao.AlimentoDao;
import com.luigi.projetc.database.entities.AlimentoEntity;

import java.util.ArrayList;
import java.util.List;

public class AlimentoController {
    Context mContext;
    AlimentoDao alimentoDao;

    public AlimentoController(Context c) {
        mContext = c;
        alimentoDao = RightFitDatabase.getDatabase(c).alimentoDao();
    }

    public void adicionarNovoAlimento(AlimentoEntity a){
        alimentoDao.insertAlimento(a);
    }

    public LiveData<List<AlimentoEntity>> listAlimentos(){
        return alimentoDao.getAllAlimentos();
    }

    public MutableLiveData<List<String>> ListNomesAlimentos(){
        List<String> result = new ArrayList<String>();
        for (AlimentoEntity alimento: this.listAlimentos().getValue()) {
            result.add(alimento.getNome());
        }
        return new MutableLiveData(result);
    }
}
