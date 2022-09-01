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
    private int fkUsuario;
    private Date data;

    public ImcEntity(int id, double pesoKg, int alturaCm, int fkUsuario, Date data) {
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

    public int getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(int fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
