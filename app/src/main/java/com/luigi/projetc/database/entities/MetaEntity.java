package com.luigi.projetc.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class MetaEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String data;
    private int qtdKcal;
    private String fkUsuario;

    public MetaEntity(int id, String data, int qtdKcal, String fkUsuario) {
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getQtdKcal() {
        return qtdKcal;
    }

    public void setQtdKcal(int qtdKcal) {
        this.qtdKcal = qtdKcal;
    }

    public String getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(String fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    @Override
    public String toString() {
        return "MetaEntity{" +
                "id=" + id +
                ", data=" + data +
                ", qtdKcal=" + qtdKcal +
                ", fkUsuario=" + fkUsuario +
                '}';
    }
}
