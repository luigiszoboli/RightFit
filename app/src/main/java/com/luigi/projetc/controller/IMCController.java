package com.luigi.projetc.controller;

import com.luigi.projetc.database.dao.ImcDao;
import com.luigi.projetc.database.entities.ImcEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IMCController {

    private final ImcDao imcDao;

    public IMCController(ImcDao imcDao) {
        this.imcDao = imcDao;
    }

    public void insertImc(String altura, String peso, String userId) {
        imcDao.insertImc(new ImcEntity(0, Double.parseDouble(peso), Integer.parseInt(altura), userId, new SimpleDateFormat("dd/MM/yyyy").format(new Date())));
    }
}