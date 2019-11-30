package com.example.mohramadhanurh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UbahData extends AppCompatActivity {

    SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
    EditText editnohp, editnama, editemail;
    Button buttonubah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_data);

        editnohp = (EditText) findViewById(R.id.ubahNoHp);
        editnama = (EditText) findViewById(R.id.ubahNama);
        editemail = (EditText) findViewById(R.id.ubahEmail);

        SQLiteDatabase db = sqLiteHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM table_siswa WHERE Nama = '"+getIntent().getStringExtra("Nama")+"'", null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            editnohp.setText(cursor.getString(0).toString());
            editnama.setText(cursor.getString(1).toString());
            editemail.setText(cursor.getString(2).toString());
        }

        buttonubah = (Button) findViewById(R.id.btnUbah);
        //daftarkan event onClick pada btnSimpan
        buttonubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
                db.execSQL("UPDATE table_siswa SET Nama='"+editnama.getText().toString()+"', Email='"+editemail.getText().toString()+"' WHERE No_Hp='"+editnohp.getText().toString()+"'");
                Toast.makeText(getApplicationContext(), "Berhasil diubah", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
