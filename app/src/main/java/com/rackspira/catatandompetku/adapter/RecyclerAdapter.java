package com.rackspira.catatandompetku.adapter;

import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rackspira.catatandompetku.R;
import com.rackspira.catatandompetku.database.AliranUang;




/**
 * Created by WIN 10 on 08/12/2016.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewAdapter> {


    private final Context context;
    LayoutInflater inflater;

    protected Cursor cursor, cursor2;
    AliranUang dbmasuk;
    String[] daftar, daftar2;

    public static int batas = 1;


    public RecyclerAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_view, parent, false);
        RecyclerViewAdapter viewHolder = new RecyclerViewAdapter(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter holder, int position) {

        dbmasuk = new AliranUang(context);
        SQLiteDatabase db = dbmasuk.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM masukkan ", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        cursor2 = db.rawQuery("SELECT * FROM masukkan ", null);
        daftar2 = new String[cursor2.getCount()];
        cursor2.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            cursor2.moveToPosition(cc);
            daftar[cc] = cursor.getString(0).toString();
            daftar2[cc] = cursor2.getString(1).toString();

        }
        if (cursor.getCount() > 0) {
            // for (int cc = 0; cc < cursor.getCount(); cc++) {

            //}
            holder.keterangan1.setText(daftar[position]);
            holder.nominal.setText(daftar2[position]);
        }
        batas = cursor.getCount();

    }


    @Override
    public int getItemCount() {


        return batas;
    }


}
