package com.luigi.projetc.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.luigi.projetc.database.entities.AlimentoEntity;
import com.luigi.projetc.database.entities.DietaEntity;
import com.luigi.projetc.database.enums.PeriodoEnum;

import java.util.Date;
import java.util.List;

@Dao
public interface DietaDao {

    @Insert
    void insertDieta(DietaEntity... dieta);

    @Query("SELECT a.* FROM DietaEntity d INNER JOIN AlimentoEntity a ON d.fkAlimento = a.id WHERE d.fkUsuario = :userId AND d.data = :data AND d.periodo = :periodoEnum")
    List<AlimentoEntity> getDietasPorUsuarioPeriodoEData(String userId, String data, PeriodoEnum periodoEnum);

    @Query("SELECT SUM(a.caloria * d.quantidade) FROM DietaEntity d INNER JOIN AlimentoEntity a ON d.fkAlimento = a.id WHERE d.fkUsuario = :userId AND d.data = :date")
    Integer getCaloriasIngeridasPorData(String userId, String date);

    @Query("SELECT SUM(a.gorduras * d.quantidade) FROM DietaEntity d INNER JOIN AlimentoEntity a ON d.fkAlimento = a.id WHERE d.fkUsuario = :userId AND d.data = :date")
    LiveData<Double> getGordurasIngeridas(String userId, String date);

    @Query("SELECT SUM(a.proteinas * d.quantidade) FROM DietaEntity d INNER JOIN AlimentoEntity a ON d.fkAlimento = a.id WHERE d.fkUsuario = :userId AND d.data = :date")
    LiveData<Double> getProteinasIngeridas(String userId, String date);

    @Query("SELECT SUM(a.carboidratos * d.quantidade) FROM DietaEntity d INNER JOIN AlimentoEntity a ON d.fkAlimento = a.id WHERE d.fkUsuario = :userId AND d.data = :date")
    LiveData<Double> getCarboidratosIngeridos(String userId, String date);

}
