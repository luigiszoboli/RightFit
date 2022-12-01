package com.luigi.projetc.controller;

import com.luigi.projetc.database.dao.DietaDao;
import com.luigi.projetc.database.entities.DietaEntity;
import com.luigi.projetc.database.enums.PeriodoEnum;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AdicionarAlimentoController {

    private final DietaDao dietaDao;

    public AdicionarAlimentoController(DietaDao dietaDao){
        this.dietaDao = dietaDao;
    }

    public void adicionarAlimentoNaDieta(String periodo, int alimentoId, String userId, int quantidade){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        DietaEntity dieta = new DietaEntity(0, userId, alimentoId,  quantidade, PeriodoEnum.valueOf(periodo), format.format(new Date()));
        dietaDao.insertDieta(dieta);
    }
}
