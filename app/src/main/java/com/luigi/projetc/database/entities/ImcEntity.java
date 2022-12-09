package com.luigi.projetc.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ImcEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private double pesoKg;
    private double altura;
    private String fkUsuario;
    private String data;

    public ImcEntity(int id, double pesoKg, double altura, String fkUsuario, String data) {
        this.id = id;
        this.pesoKg = pesoKg;
        this.altura = altura;
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

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
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
                ", alturaCm=" + altura +
                ", fkUsuario=" + fkUsuario +
                ", data=" + data +
                '}';
    }
}
