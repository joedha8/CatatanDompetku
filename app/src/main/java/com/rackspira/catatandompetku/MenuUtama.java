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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.rackspira.catatandompetku.adapter.RecyclerAdapter;
import com.rackspira.catatandompetku.database.AliranUang;

public class MenuUtama extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    AliranUang dbuang;
  //  protected Cursor cursor, x, y;
    //String[] daftar, masuk, keluar;

    public static MenuUtama mu;
    public TextView in, out, tot;

    public RecyclerView rview;
    //public int a=100, b=200, c, d;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        in = (TextView) findViewById(R.id.in);
        out = (TextView) findViewById(R.id.out);
        tot = (TextView) findViewById(R.id.tot);
        mu = this;
        dbuang = new AliranUang(this);

           // RefreshList();

        in.setText("100");
        out.setText("400");
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

    @Override
    protected void onResume() {
        super.onResume();
        RefreshList();

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent(MenuUtama.this, MenuUtama.class);
            startActivity(intent);
        } else if (id == R.id.nav_masuk) {
            Intent intent = new Intent(MenuUtama.this, BuatData.class);
            startActivity(intent);
            //d=0;
        } else if (id == R.id.nav_keluar) {
            Intent intent = new Intent(MenuUtama.this, BuatData.class);
            startActivity(intent);
            //d=1;
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_tentang) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void RefreshList() {
        rview = (RecyclerView) findViewById(R.id.listView);
        RecyclerAdapter adapter = new RecyclerAdapter(this);
        rview.setAdapter(adapter);
        rview.setHasFixedSize(true);
        rview.setLayoutManager(new LinearLayoutManager(this));

    }
}
