package com.luigi.projetc.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.luigi.projetc.database.entities.AlimentoEntity;

import java.util.List;

@Dao
public interface AlimentoDao {

    @Insert
    void insertAlimento(AlimentoEntity... alimentoEntity);

    @Query("SELECT * FROM AlimentoEntity")
    LiveData<List<AlimentoEntity>> getAllAlimentos();

    @Query("SELECT * FROM AlimentoEntity WHERE nome LIKE '%' || :search ||'%'")
    List<AlimentoEntity> pesquisarAlimentos(String search);

    @Query("SELECT * FROM AlimentoEntity WHERE id = :id")
    LiveData<AlimentoEntity> getAlimentoPorId(int id);
}
