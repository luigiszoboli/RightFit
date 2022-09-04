package com.luigi.projetc.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.luigi.projetc.database.entities.ImcEntity;

import java.util.List;

@Dao
public interface ImcDao {

    @Insert
    void insertImc(ImcEntity... imc);

    @Query("SELECT * FROM ImcEntity WHERE data = :date AND fkUsuario = :userId")
    LiveData<ImcEntity> getImcPorUsuarioEData(String date, String userId);
}
