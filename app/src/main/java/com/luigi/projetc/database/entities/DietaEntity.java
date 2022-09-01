package com.luigi.projetc.database.entities;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = AlimentoEntity.class,
        parentColumns = "id",
        childColumns = "fkAlimento",
        onDelete = ForeignKey.CASCADE)
})
public class DietaEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int fkUsuario;
    private int fkAlimento;
    private int quantidade;
    private int periodo;

    public DietaEntity(int id, int fkUsuario, int fkAlimento, int quantidade, int periodo) {
        this.id = id;
        this.fkUsuario = fkUsuario;
        this.fkAlimento = fkAlimento;
        this.quantidade = quantidade;
        this.periodo = periodo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(int fkUsuario) {
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
}
