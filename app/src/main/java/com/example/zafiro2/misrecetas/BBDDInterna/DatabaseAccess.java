package com.example.zafiro2.misrecetas.BBDDInterna;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.zafiro2.misrecetas.Objetos.Receta;

import java.util.ArrayList;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private  static DatabaseAccess instace;

    private DatabaseAccess(Context context){
        this.openHelper = new DataBaseOpenHelper(context);
    }

    public static DatabaseAccess getInstace(Context context){
        if(instace ==null){
            instace = new DatabaseAccess(context);
        }
        return instace;
    }
    public void open(){
        this.database = openHelper.getWritableDatabase();
    }

    public void close(){
        if(database!=null){
            this.database.close();
        }
    }

    public ArrayList<Receta> getTodosByCategoria(String Categoria){
        Cursor c = null;

        ArrayList<Receta> arrayRecetas = new ArrayList<Receta>();
        c = database.rawQuery("select * from recetas where categoria_receta = '"+Categoria+"'", null);

        if(c.moveToFirst()){
            do{
                arrayRecetas.add(new Receta(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4)));
            }while (c.moveToNext());
        }

        c.close();
        return arrayRecetas;
    }

    public ArrayList<Receta> getTodasLasRecetas(){
        Cursor c = null;

        ArrayList<Receta> arrayRecetas = new ArrayList<Receta>();
        c = database.rawQuery("select * from recetas", null);

        if(c.moveToFirst()){
            do{
                arrayRecetas.add(new Receta(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4)));
            }while (c.moveToNext());
        }

        c.close();
        return arrayRecetas;
    }
}
