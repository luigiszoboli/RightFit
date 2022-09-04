package com.luigi.projetc.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class ImcEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private double pesoKg;
    private int alturaCm;
    private String fkUsuario;
    private String data;

    public ImcEntity(int id, double pesoKg, int alturaCm, String fkUsuario, String data) {
        this.id = id;
        this.pesoKg = pesoKg;
        this.alturaCm = alturaCm;
        this.fkUsuario = fkUsuario;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public int getAlturaCm() {
        return alturaCm;
    }

    public void setAlturaCm(int alturaCm) {
        this.alturaCm = alturaCm;
    }

    public String getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(String fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ImcEntity{" +
                "id=" + id +
                ", pesoKg=" + pesoKg +
                ", alturaCm=" + alturaCm +
                ", fkUsuario=" + fkUsuario +
                ", data=" + data +
                '}';
    }
}
