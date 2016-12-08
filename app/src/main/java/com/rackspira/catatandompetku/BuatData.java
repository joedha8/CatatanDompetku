package com.rackspira.catatandompetku;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuatData extends AppCompatActivity {
    AliranUang dbmasuk;
    Button save;
    EditText teks1, teks2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_data);

        teks1=(EditText)findViewById(R.id.edit1);
        teks2=(EditText)findViewById(R.id.edit2);
        save=(Button)findViewById(R.id.btn1);
        dbmasuk=new AliranUang(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbmasuk.getWritableDatabase();
                db.execSQL("insert into masukkan(keterangan, masuk) values('" +
                        teks1.getText().toString()+"','"+
                        teks2.getText().toString() + "');");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                MenuUtama.mu.RefreshList();
                finish();
            }
        });
    }
}
