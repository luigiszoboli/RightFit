package com.luigi.projetc.model;

public class Alimento {
    Integer id;
    String nome;
    String calorias;

    public Alimento(Integer id, String nome, String calorias) {
        this.id = id;
        this.nome = nome;
        this.calorias = calorias;
    }

    public Alimento(String nome, String calorias) {
        this.nome = nome;
        this.calorias = calorias;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCalorias() {
        return calorias;
    }

    public void setCalorias(String calorias) {
        this.calorias = calorias;
    }
}

