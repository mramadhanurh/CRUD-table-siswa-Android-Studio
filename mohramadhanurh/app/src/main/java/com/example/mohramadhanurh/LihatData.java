package com.example.mohramadhanurh;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class LihatData extends AppCompatActivity {

    SQLiteHelper sqlithelper = new SQLiteHelper(this);
    TextView tampilnohp, tampilnama, tampilemail;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);

        tampilnohp = (TextView) findViewById(R.id.textViewNoHp);
        tampilnama = (TextView) findViewById(R.id.textViewNama);
        tampilemail = (TextView) findViewById(R.id.textViewEmail);

        SQLiteDatabase db = sqlithelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM table_siswa WHERE Nama = '"+getIntent().getStringExtra("Nama")+"'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0){
            cursor.moveToPosition(0);
            tampilnohp.setText("No Hp : "+cursor.getString(0).toString());
            tampilnama.setText("Nama : "+cursor.getString(1).toString());
            tampilemail.setText("Email : "+cursor.getString(2).toString());
        }
    }
}
