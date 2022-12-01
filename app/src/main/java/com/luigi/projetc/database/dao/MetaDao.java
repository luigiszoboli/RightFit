package com.luigi.projetc.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.luigi.projetc.database.entities.MetaEntity;

import java.util.List;

@Dao
public interface MetaDao {

    @Insert
    void insertMeta(MetaEntity... meta);

    @Update
    void updateMeta(MetaEntity meta);

    @Query("SELECT * FROM MetaEntity")
    LiveData<List<MetaEntity>> selectAll();

    @Query("SELECT * FROM MetaEntity WHERE fkUsuario = :userId AND data = :date")
    List<MetaEntity> getMetaPorUsuarioEData(String userId, String date);
}
