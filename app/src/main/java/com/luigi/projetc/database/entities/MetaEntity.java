package com.luigi.projetc.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class MetaEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private Date data;
    private int qtdKcal;
    private int fkUsuario;

    public MetaEntity(int id, Date data, int qtdKcal, int fkUsuario) {
        this.id = id;
        this.data = data;
        this.qtdKcal = qtdKcal;
        this.fkUsuario = fkUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getQtdKcal() {
        return qtdKcal;
    }

    public void setQtdKcal(int qtdKcal) {
        this.qtdKcal = qtdKcal;
    }

    public int getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(int fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}
