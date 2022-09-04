package com.luigi.projetc.database.entities;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(foreignKeys = {@ForeignKey(entity = AlimentoEntity.class,
        parentColumns = "id",
        childColumns = "fkAlimento",
        onDelete = ForeignKey.CASCADE)
})
public class DietaEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String fkUsuario;
    private int fkAlimento;
    private int quantidade;
    private int periodo;
    private String data;

    public DietaEntity(int id, String fkUsuario, int fkAlimento, int quantidade, int periodo, String data) {
        this.id = id;
        this.fkUsuario = fkUsuario;
        this.fkAlimento = fkAlimento;
        this.quantidade = quantidade;
        this.periodo = periodo;
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(String fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public int getFkAlimento() {
        return fkAlimento;
    }

    public void setFkAlimento(int fkAlimento) {
        this.fkAlimento = fkAlimento;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    @Override
    public String toString() {
        return "DietaEntity{" +
                "id=" + id +
                ", fkUsuario=" + fkUsuario +
                ", fkAlimento=" + fkAlimento +
                ", quantidade=" + quantidade +
                ", periodo=" + periodo +
                ", data=" + data +
                '}';
    }
}
