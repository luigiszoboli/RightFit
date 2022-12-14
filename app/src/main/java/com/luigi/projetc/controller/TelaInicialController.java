package com.luigi.projetc.controller;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.luigi.projetc.database.dao.DietaDao;
import com.luigi.projetc.database.dao.ImcDao;
import com.luigi.projetc.database.dao.MetaDao;
import com.luigi.projetc.database.entities.ImcEntity;
import com.luigi.projetc.database.entities.MetaEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TelaInicialController {

    private DietaDao dietaDao;
    private MetaDao metaDao;
    private ImcDao imcDao;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private final MutableLiveData<String> dataAtual = new MutableLiveData<>(simpleDateFormat.format(new Date()));

    public TelaInicialController(DietaDao dietaDao, MetaDao metaDao, ImcDao imcDao) {
        this.dietaDao = dietaDao;
        this.metaDao = metaDao;
        this.imcDao = imcDao;
    }

    public Integer getMeta(){
        List<MetaEntity> meta = metaDao.getMetaPorUsuarioEData(FirebaseAuth.getInstance().getUid(), dataAtual.getValue());

        if(meta == null || meta.size() == 0){
            return 0;
        }
        return meta.get(0).getQtdKcal();
    }

    public LiveData<String> dataAtualObservable() {
        return dataAtual;
    }

    public Integer getCalorias(){
        return dietaDao.getCaloriasIngeridasPorData(FirebaseAuth.getInstance().getUid(), dataAtual.getValue());
    }

    public Double getGorduras(){
        return dietaDao.getGordurasIngeridas(FirebaseAuth.getInstance().getUid(), dataAtual.getValue());
    }

    public Double getProteinas(){
        return dietaDao.getProteinasIngeridas(FirebaseAuth.getInstance().getUid(), dataAtual.getValue());
    }

    public Double getCarboidratosIngeridos(){
        return dietaDao.getCarboidratosIngeridos(FirebaseAuth.getInstance().getUid(), dataAtual.getValue());
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

    public Double getPeso() {
        List<ImcEntity> imc = imcDao.getImcPorUsuarioEData(dataAtual.getValue(), FirebaseAuth.getInstance().getUid());
        System.out.println(imc);
        if(imc == null || imc.size() == 0){
            return 0.0;
        }

        return imc.get(imc.size() - 1).getPesoKg();
    }
}
