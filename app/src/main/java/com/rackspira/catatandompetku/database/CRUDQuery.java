package com.rackspira.catatandompetku.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by YUDHA on 08/12/2016.
 */

public class CRUDQuery {

    private SQLiteDatabase db;
    private AliranUang dbmasuk;

    public CRUDQuery(Context context) {
        dbmasuk = new AliranUang(context);
        db = dbmasuk.getWritableDatabase();
    }

    public void insert(String keterangan, String masuk) {
        db.execSQL("insert into masukkan" +
                "(keterangan, masuk) " +
                "values('" +
                keterangan + "','" +
                masuk + "');");
    }
}
