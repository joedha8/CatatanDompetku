package com.rackspira.catatandompetku;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.rackspira.catatandompetku.database.AliranUang;

public class LihatData extends AppCompatActivity {
    AliranUang dbmasuk;
    Button save;
    TextView teks0, teks1, teks2;
    protected Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);

        teks1=(TextView)findViewById(R.id.text1);
        teks2=(TextView) findViewById(R.id.text2);
        save=(Button)findViewById(R.id.btn1);
        dbmasuk=new AliranUang(this);
        SQLiteDatabase db = dbmasuk.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM masukkan WHERE keterangan = '" +
                getIntent().getStringExtra("keterangan") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            teks1.setText(cursor.getString(0).toString());
            teks2.setText(cursor.getString(1).toString());
        }
    }
}
