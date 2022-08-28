package com.luigi.projetc.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AlimentoEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId(){
        return id;
    }
}
