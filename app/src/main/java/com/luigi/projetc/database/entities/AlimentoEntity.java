package com.luigi.projetc.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AlimentoEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nome;
    private int caloria;
    private int gorduras;
    private int proteinas;
    private int carboidratos;
    private int fibra;
    private int sodio;
    private int acucares;

    public AlimentoEntity(int id, String nome, int caloria, int gorduras, int proteinas, int carboidratos, int fibra, int sodio, int acucares) {
        this.id = id;
        this.nome = nome;
        this.caloria = caloria;
        this.gorduras = gorduras;
        this.proteinas = proteinas;
        this.carboidratos = carboidratos;
        this.fibra = fibra;
        this.sodio = sodio;
        this.acucares = acucares;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCaloria() {
        return caloria;
    }

    public void setCaloria(int caloria) {
        this.caloria = caloria;
    }

    public int getGorduras() {
        return gorduras;
    }

    public void setGorduras(int gorduras) {
        this.gorduras = gorduras;
    }

    public int getProteinas() {
        return proteinas;
    }

    public void setProteinas(int proteinas) {
        this.proteinas = proteinas;
    }

    public int getCarboidratos() {
        return carboidratos;
    }

    public void setCarboidratos(int carboidratos) {
        this.carboidratos = carboidratos;
    }

    public int getFibra() {
        return fibra;
    }

    public void setFibra(int fibra) {
        this.fibra = fibra;
    }

    public int getSodio() {
        return sodio;
    }

    public void setSodio(int sodio) {
        this.sodio = sodio;
    }

    public int getAcucares() {
        return acucares;
    }

    public void setAcucares(int acucares) {
        this.acucares = acucares;
    }

    public int getId(){
        return id;
    }
}
