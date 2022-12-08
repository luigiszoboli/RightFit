package com.luigi.projetc.controller;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.luigi.projetc.database.dao.DietaDao;
import com.luigi.projetc.database.dao.MetaDao;
import com.luigi.projetc.database.entities.AlimentoEntity;
import com.luigi.projetc.database.entities.MetaEntity;
import com.luigi.projetc.database.enums.PeriodoEnum;

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

    public RegistroAlimentoController(DietaDao dietaDao, MetaDao metaDao) {
        this.dietaDao = dietaDao;
        this.metaDao = metaDao;
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

    public List<AlimentoEntity> alimentosPorPeriodo(PeriodoEnum periodoEnum){
        return dietaDao.getDietasPorUsuarioPeriodoEData(FirebaseAuth.getInstance().getCurrentUser().getUid(), dataAtual.getValue(), periodoEnum);
    }

    public Integer getRestantes(){
        Integer calorias = getCalorias();
        Integer metas = getMeta();

        if(calorias == null || metas == null){
            return 0;
        }

        return metas - calorias;
    }

    public Integer getCalorias(){
        Integer calorias = dietaDao.getCaloriasIngeridasPorData(FirebaseAuth.getInstance().getUid(), dataAtual.getValue());
        return calorias == null ? 0 : calorias;
    }

    public Integer getMeta(){
        List<MetaEntity> metas = metaDao.getMetaPorUsuarioEData(FirebaseAuth.getInstance().getUid(), dataAtual.getValue());

        if(metas == null || metas.size() == 0){
            return 0;
        }
        return metas.get(metas.size()-1).getQtdKcal();
    }

}
