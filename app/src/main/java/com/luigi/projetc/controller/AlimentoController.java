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
    AlimentoDao alimentoDao;

    public AlimentoController(AlimentoDao alimentoDao) {
        this.alimentoDao = alimentoDao;
    }

    public void adicionarNovoAlimento(AlimentoEntity a){
        alimentoDao.insertAlimento(a);
    }

    public LiveData<List<AlimentoEntity>> listAlimentos(){
        return alimentoDao.getAllAlimentos();
    }

    public LiveData<AlimentoEntity> getAlimentoById(int id){
        return alimentoDao.getAlimentoPorId(id);
    }
}
