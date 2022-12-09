package com.luigi.projetc.controller;

import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.luigi.projetc.database.dao.DietaDao;
import com.luigi.projetc.database.dao.MetaDao;
import com.luigi.projetc.database.entities.MetaEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TelaInicialController {

    private DietaDao dietaDao;
    private MetaDao metaDao;
    private final String data = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

    public TelaInicialController(DietaDao dietaDao, MetaDao metaDao) {
        this.dietaDao = dietaDao;
        this.metaDao = metaDao;
    }

    public Integer getMeta(){
        List<MetaEntity> meta = metaDao.getMetaPorUsuarioEData(FirebaseAuth.getInstance().getUid(), data);

        if(meta == null || meta.size() == 0){
            return 0;
        }
        return meta.get(0).getQtdKcal();
    }

    public Integer getCalorias(){
        return dietaDao.getCaloriasIngeridasPorData(FirebaseAuth.getInstance().getUid(), data);
    }

    public Double getGorduras(){
        return dietaDao.getGordurasIngeridas(FirebaseAuth.getInstance().getUid(), data);
    }

    public Double getProteinas(){
        return dietaDao.getProteinasIngeridas(FirebaseAuth.getInstance().getUid(), data);
    }

    public Double getCarboidratosIngeridos(){
        return dietaDao.getCarboidratosIngeridos(FirebaseAuth.getInstance().getUid(), data);
    }

}
