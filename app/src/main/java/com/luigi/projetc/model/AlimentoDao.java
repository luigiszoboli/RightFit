package com.luigi.projetc.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class AlimentoDao {
    SQLiteDatabase database;
    public AlimentoDao(Context c) {
    database=c.openOrCreateDatabase("dbAlimentos", c.MODE_PRIVATE, null);
    database.execSQL("CREATE TABLE IF NOT EXISTS alimentos(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nome varchar,"+
            "calorias varchar);");
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
        Cursor cursor= database.rawQuery("SELECT * FROM alimentos", null);
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
