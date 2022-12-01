package com.luigi.projetc.controller;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.luigi.projetc.database.dao.DietaDao;
import com.luigi.projetc.database.dao.MetaDao;
import com.luigi.projetc.database.entities.MetaEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RegistroAlimentoController {

    private DietaDao dietaDao;
    private MetaDao metaDao;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private final MutableLiveData<String> dataAtual = new MutableLiveData<>(simpleDateFormat.format(new Date()));
    private final MutableLiveData<Integer> restantes = new MutableLiveData<>(0);
    private final MutableLiveData<Integer> meta = new MutableLiveData<>(0);
    private final MutableLiveData<Integer> ingeridas = new MutableLiveData<>(0);


    public RegistroAlimentoController(DietaDao dietaDao, MetaDao metaDao) {
        this.dietaDao = dietaDao;
        this.metaDao = metaDao;
    }

    public LiveData<Integer> restantesObservable(){
        return restantes;
    }

    public void diaPosterior() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String dataRegistrada = dataAtual.getValue();
            Date data = format.parse(dataRegistrada);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(data);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            String dateAfter = format.format(calendar.getTime());
            dataAtual.postValue(dateAfter);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void diaAnterior() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String dataRegistrada = dataAtual.getValue();
            Date data = format.parse(dataRegistrada);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(data);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            String dateAfter = format.format(calendar.getTime());
            dataAtual.postValue(dateAfter);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void saveMeta(int meta, String userId) {
        metaDao.insertMeta(new MetaEntity(0, dataAtualObservable().getValue(), meta, userId));
    }

    public LiveData<String> dataAtualObservable() {
        return dataAtual;
    }

    public LiveData<Integer> currentCaloriasObservable() {
        return ingeridas;
    }

    public void getDadosParaTela(String userId){
        Integer calorias = dietaDao.getCaloriasIngeridasPorData(userId, dataAtual.getValue());
        List<MetaEntity> metas = metaDao.getMetaPorUsuarioEData(userId, dataAtual.getValue());

        if(metas.size() == 0){
            meta.postValue(0);
        }

        if(calorias == null || calorias == 0){
            ingeridas.postValue(0);
        }

        if(calorias == null || metas.size() == 0){
            restantes.postValue(0);
        }

        if(metas.size() != 0){
            meta.postValue(metas.get(metas.size()-1).getQtdKcal());
            if(calorias != null){
                restantes.postValue(metas.get(metas.size()-1).getQtdKcal() - calorias);
            }
        }
        if(calorias != null){
            ingeridas.postValue(calorias);
        }
    }

    public LiveData<Integer> currentMetaObservable() {
        return meta;
    }
}
