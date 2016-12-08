package com.rackspira.catatandompetku;

/**
 * Created by YUDHA on 16/11/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AliranUang extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "aliranuang.db";
    private static final int DATABASE_VERSION = 1;
    public AliranUang(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String sql = "create table masukkan(keterangan text null, masuk integer);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
    }

}