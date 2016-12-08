package com.rackspira.catatandompetku;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MenuUtama extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    AliranUang dbuang;
    protected Cursor cursor, x, y;
    String[] daftar, masuk, keluar;
    ListView ListView01;
    public static MenuUtama mu;
    public TextView in, out, tot;
    //public int a=100, b=200, c, d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        in=(TextView)findViewById(R.id.in);
        out=(TextView)findViewById(R.id.out);
        tot=(TextView)findViewById(R.id.tot);
        mu=this;
        dbuang=new AliranUang(this);
        RefreshList();

        in.setText("100");
        out.setText("200");
        //tot.setText(c);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_utama, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent=new Intent(MenuUtama.this, MenuUtama.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_masuk) {
            Intent intent=new Intent(MenuUtama.this, BuatData.class);
            startActivity(intent);
            //d=0;
        }
        else if (id == R.id.nav_keluar) {
            Intent intent=new Intent(MenuUtama.this, BuatData.class);
            startActivity(intent);
            //d=1;
        }
        else if (id == R.id.nav_share) {

        }
        else if (id == R.id.nav_tentang) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void RefreshList(){
        SQLiteDatabase db = dbuang.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM masukkan",null);
        daftar = new String[cursor.getCount()];
        /*if(d==0){
            masuk=new String[cursor.getCount()];
        }
        else if(d==1){
            keluar=new String[cursor.getCount()];
        }*/
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(0).toString();
        }
        ListView01 = (ListView)findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2]; //.getItemAtPosition(arg2).toString();
                final CharSequence[] dialogitem = {"Lihat", "Edit", "Hapus"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuUtama.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch(item){
                            case 0 :
                                Intent i = new Intent(getApplicationContext(), LihatData.class);
                                i.putExtra("keterangan", selection);
                                startActivity(i);
                                break;
                            case 1 :
                                Intent in = new Intent(getApplicationContext(), EditData.class);
                                in.putExtra("keterangan", selection);
                                startActivity(in);
                                break;
                            case 2 :
                                SQLiteDatabase db = dbuang.getWritableDatabase();
                                db.execSQL("delete from masukkan");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }});
        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();
    }
    /*public void hitung(){
        int masuk, keluar, jumlah;
        masuk=a;
        keluar=b;
        jumlah=masuk-keluar;
        c=jumlah;
    }

    public void pilih(){
        int yati;
        yati=d;
        if(yati==0){
            SQLiteDatabase db = dbuang.getReadableDatabase();
            x=db.rawQuery("SELECT masuk from masukkan",null);
            a=x.getCount();
            x.moveToFirst();
        }
        else if(yati==1){
            SQLiteDatabase db = dbuang.getReadableDatabase();
            y=db.rawQuery("SELECT masuk from masukkan",null);
            b=y.getCount();
            y.moveToFirst();
        }
    }*/
}
