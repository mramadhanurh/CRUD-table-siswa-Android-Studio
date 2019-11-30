package com.example.mohramadhanurh;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    private  static String nama_database = "db_datasiswa.db";
    private static int versi_database = 1;
    private static String query_buat_tabel_siswa = "CREATE TABLE IF NOT EXISTS table_siswa(No_Hp INTEGER PRIMARY KEY,Nama TEXT, Email TEXT)";
    public SQLiteHelper(Context context) {super(context, nama_database, null, versi_database); }

    @Override
    public void onCreate(SQLiteDatabase sqlitedbase) { sqlitedbase.execSQL(query_buat_tabel_siswa); }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2){

    }
}