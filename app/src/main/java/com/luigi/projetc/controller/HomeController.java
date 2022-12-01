package com.luigi.projetc.controller;


import com.luigi.projetc.database.dao.DietaDao;
import com.luigi.projetc.database.dao.MetaDao;

public class HomeController {

    DietaDao dietaDao;
    MetaDao metaDao;

    public HomeController(DietaDao dietaDao){
        this.dietaDao = dietaDao;
    }


}
