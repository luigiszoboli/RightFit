package com.luigi.projetc.model;

import java.util.HashMap;

public class Alimento {
    Integer id;
    String nome ;
    HashMap<String,Float>  nutrientes;

    public Alimento(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
        this.nutrientes= new HashMap<>();
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

    public HashMap<String, Float> getNutrientes() {
        return nutrientes;
    }

    public void setNutrientes(HashMap<String, Float> nutrientes) {
        this.nutrientes = nutrientes;
    }
    public  void addNutriente( String key, Float value){
        this.nutrientes.put(key,value);
    }
    public Float getNutriente ( String key) {
        if (this.nutrientes.containsKey(key)) {
           return this.nutrientes.get(key);
        } else {
            return null;
        }
    }
}
