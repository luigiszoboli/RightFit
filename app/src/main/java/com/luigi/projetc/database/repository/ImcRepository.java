package com.luigi.projetc.database.repository;

import androidx.lifecycle.LiveData;

import com.luigi.projetc.database.dao.ImcDao;
import com.luigi.projetc.database.entities.ImcEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Repository criado para inserir IMC, imc é substituido caso já exista um imc cadastrado no dia
 *
 */
public class ImcRepository {

    private ImcDao imcDao;

    public ImcRepository(ImcDao imcDao) {
        this.imcDao = imcDao;
    }

    public void insertImc(double peso, int alturaCm, String usuario){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        LiveData<List<ImcEntity>> imcs = imcDao.getImcPorUsuarioEData(formatter.format(new Date()), usuario);

        List<ImcEntity> imcCadastradoNoMesmoDia = imcs.getValue();
        if(imcCadastradoNoMesmoDia != null && imcCadastradoNoMesmoDia.size() == 1){
            ImcEntity imcAtualizar = imcs.getValue().get(0);
            imcAtualizar.setData(formatter.format(new Date()));
            imcAtualizar.setAlturaCm(alturaCm);
            imcAtualizar.setPesoKg(peso);

            imcDao.updateImc(imcAtualizar);
            return;
        }
        imcDao.insertImc(new ImcEntity(0, peso, alturaCm, usuario, formatter.format(new Date())));
    }
}
