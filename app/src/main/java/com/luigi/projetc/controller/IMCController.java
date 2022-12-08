package com.luigi.projetc.controller;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.luigi.projetc.database.dao.ImcDao;
import com.luigi.projetc.database.entities.ImcEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class IMCController {

    private final ImcDao imcDao;

    public IMCController(ImcDao imcDao) {
        this.imcDao = imcDao;
    }

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private final MutableLiveData<String> dataAtual = new MutableLiveData<>(simpleDateFormat.format(new Date()));

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

    public void insertImc(String altura, String peso, String userId) {
        imcDao.insertImc(new ImcEntity(0, Double.parseDouble(peso), Integer.parseInt(altura), userId, new SimpleDateFormat("dd/MM/yyyy").format(new Date())));
    }

    public ImcEntity getImc(){
        List<ImcEntity> imcs = imcDao.getImcPorUsuarioEData(dataAtual.getValue(),FirebaseAuth.getInstance().getUid());

        if(imcs.size() == 0){
            return null;
        }

        return imcs.get(0);
    }


    public LiveData<String> dataAtualObservable() {
        return dataAtual;
    }
}