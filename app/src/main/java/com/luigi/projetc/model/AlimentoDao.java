package com.luigi.projetc.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class AlimentoDao {
    SQLiteDatabase database;
    public AlimentoDao(Context c) {
    database=c.openOrCreateDatabase("dbTaco", c.MODE_PRIVATE, null);
    database.execSQL("CREATE TABLE IF NOT EXISTS taco( id INTEGER DEFAULT NULL,"+
                                                    "Caterogia text(37) DEFAULT NULL,"+
                                                    "Alimento text(64) DEFAULT NULL,"+
                                                    "Umidade text(4) DEFAULT NULL," +
                                                    "Energiakcal text(3) DEFAULT NULL,"+
                                                    "kJ text(4) DEFAULT NULL,"+
                                                    "Proteonag text(4) DEFAULT NULL,"+
                                                    "Lipodeosg text(5) DEFAULT NULL,"+
                                                    "Colesterolmg text(4) DEFAULT NULL,"+
                                                    "Carboidratosg text(4) DEFAULT NULL,"+
                                                    "FibraAlimentarg text(4) DEFAULT NULL,"+
                                                    "Cinzasg text(4) DEFAULT NULL,"+
                                                    "Calciomg text(4) DEFAULT NULL,"+
                                                    "Magnesiomg text(3) DEFAULT NULL,"+
                                                    "Manganesmg text(5) DEFAULT NULL,"+
                                                    "Fósforomg text(4) DEFAULT NULL,"+
                                                    "Ferromg text(4) DEFAULT NULL,"+
                                                    "Sódiomg text(5) DEFAULT NULL,"+
                                                    "Potassiomg text(5) DEFAULT NULL,"+
                                                    "Cobremg text(5) DEFAULT NULL,"+
                                                    "Zincomg text(4) DEFAULT NULL,"+
                                                    "Retinolmcg text(5) DEFAULT NULL,"+
                                                    "REmcg text(5) DEFAULT NULL,"+
                                                    "RAEmcg text(5) DEFAULT NULL,"+
                                                    "Tiaminamg text(4) DEFAULT NULL,"+
                                                    "Riboflavinamg text(4) DEFAULT NULL,"+
                                                    "Piridoxinamg text(5) DEFAULT NULL,"+
                                                    "Niacinamg text(5) DEFAULT NULL,"+
                                                    "VitaminaCmg text(5) DEFAULT NULL)");
    }
    public Alimento inserirAlimento(Alimento a){
        ContentValues contentValues= new ContentValues();
        contentValues.put("nome",a.getNome());
        contentValues.put("calorias",a.getCalorias());
        int i = (int) database.insert("alimentos",null,contentValues);
        a.setId(i);
        return a;
    }

    public ArrayList<Alimento> getListAlimentos() {
        Cursor cursor= database.rawQuery("SELECT * FROM taco", null);
        cursor.moveToFirst();
        ArrayList<Alimento> arrayList = new ArrayList<>();
        while (!cursor.isAfterLast()){
            Alimento a= new Alimento(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
            arrayList.add(a);
            cursor.moveToNext();
        }
        return arrayList;
    }
}
