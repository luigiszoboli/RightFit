package com.luigi.projetc.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import javax.annotation.Nullable;

@Entity
public class AlimentoEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nome;
    private int caloria;
    private double gorduras;
    private double proteinas;
    private double carboidratos;
    private double fibra;
    private int sodio;

    public AlimentoEntity(int id, String nome, int caloria, double gorduras, double proteinas, double carboidratos, double fibra, int sodio) {
        this.id = id;
        this.nome = nome;
        this.caloria = caloria;
        this.gorduras = gorduras;
        this.proteinas = proteinas;
        this.carboidratos = carboidratos;
        this.fibra = fibra;
        this.sodio = sodio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
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

    public double getGorduras() {
        return gorduras;
    }

    public void setGorduras(double gorduras) {
        this.gorduras = gorduras;
    }

    public double getProteinas() {
        return proteinas;
    }

    public void setProteinas(double proteinas) {
        this.proteinas = proteinas;
    }

    public double getCarboidratos() {
        return carboidratos;
    }

    public void setCarboidratos(double carboidratos) {
        this.carboidratos = carboidratos;
    }

    public double getFibra() {
        return fibra;
    }

    public void setFibra(double fibra) {
        this.fibra = fibra;
    }

    public int getSodio() {
        return sodio;
    }

    public void setSodio(int sodio) {
        this.sodio = sodio;
    }

    @NonNull
    @Override
    public String toString() {
        return "AlimentoEntity{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", caloria=" + caloria +
                ", gorduras=" + gorduras +
                ", proteinas=" + proteinas +
                ", carboidratos=" + carboidratos +
                ", fibra=" + fibra +
                ", sodio=" + sodio +
                '}';
    }
}
