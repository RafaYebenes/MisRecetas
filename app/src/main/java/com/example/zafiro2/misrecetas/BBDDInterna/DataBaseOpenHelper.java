package com.example.zafiro2.misrecetas.BBDDInterna;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class DataBaseOpenHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "bbddRecetas";
    private static final int DATABASE_VERSION = 1;

    public DataBaseOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
