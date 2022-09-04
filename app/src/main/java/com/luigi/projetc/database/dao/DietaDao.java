package com.luigi.projetc.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.luigi.projetc.database.entities.DietaEntity;

import java.util.Date;
import java.util.List;

@Dao
public interface DietaDao {

    @Insert
    void insertDieta(DietaEntity... dieta);

    @Query("SELECT d.* FROM DietaEntity d INNER JOIN AlimentoEntity a ON d.fkAlimento = a.id WHERE d.fkUsuario = :userId")
    LiveData<List<DietaEntity>> getDietasPorUsuarioEData(String userId);

    @Query("SELECT SUM(a.caloria * d.quantidade) FROM DietaEntity d INNER JOIN AlimentoEntity a ON d.fkAlimento = a.id WHERE d.fkUsuario = :userId AND d.data = :date")
    LiveData<Integer> getCaloriasIngeridasPorData(String userId, String date);

    @Query("SELECT SUM(a.gorduras * d.quantidade) FROM DietaEntity d INNER JOIN AlimentoEntity a ON d.fkAlimento = a.id WHERE d.fkUsuario = :userId AND d.data = :date")
    LiveData<Integer> getGordurasIngeridas(String userId, String date);

    @Query("SELECT SUM(a.proteinas * d.quantidade) FROM DietaEntity d INNER JOIN AlimentoEntity a ON d.fkAlimento = a.id WHERE d.fkUsuario = :userId AND d.data = :date")
    LiveData<Integer> getProteinasIngeridas(String userId, String date);

    @Query("SELECT SUM(a.carboidratos * d.quantidade) FROM DietaEntity d INNER JOIN AlimentoEntity a ON d.fkAlimento = a.id WHERE d.fkUsuario = :userId AND d.data = :date")
    LiveData<Integer> getCarboidratosIngeridos(String userId, String date);

}
