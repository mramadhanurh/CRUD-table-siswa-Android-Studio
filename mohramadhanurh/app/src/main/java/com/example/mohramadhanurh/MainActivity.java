package com.example.mohramadhanurh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.opengl.ETC1;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
    Button buttonsiman;
    EditText inputNoHp, inputNama, inputEmail;
    ListView listViewsiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RefreshList();
        inputNoHp = (EditText) findViewById(R.id.editTextNoHp);
        inputNama = (EditText) findViewById(R.id.editTextNama);
        inputEmail = (EditText) findViewById(R.id.editTextEmail);

        buttonsiman = (Button) findViewById(R.id.btnSimpan);
        buttonsiman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
                db.execSQL("insert into table_siswa values ('"+inputNoHp.getText().toString()+"', '"+inputNama.getText().toString()+"', '"+inputEmail.getText().toString()+"')");
                Toast.makeText(getApplicationContext(), "Berhasil disimpan", Toast.LENGTH_SHORT).show();
                RefreshList();

                inputNoHp.setText("");
                inputNama.setText("");
                inputEmail.setText("");
            }
        });
    }

    public void RefreshList(){
        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from table_siswa", null);
        final String[] daftar = new String[cursor.getCount()];
        cursor.moveToFirst(); //cursor langsung diarahkan ke posisi paling awal data pada table_siswa
        for (int i=0; i<cursor.getCount(); i++){
            cursor.moveToPosition(i);
            daftar[i] = cursor.getString(1).toString();
        }
        listViewsiswa = (ListView) findViewById(R.id.listView1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, daftar);
        listViewsiswa.setAdapter(adapter);
        listViewsiswa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String selection = daftar[position];
                CharSequence[] dialogitem = {"Lihat Data", "Ubah Data", "Hapus Data"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0 :
                                Intent i = new Intent(getApplicationContext(), LihatData.class);
                                i.putExtra("Nama", selection);
                                startActivity(i);
                                break;
                            case 1 :
                                Intent in = new Intent(getApplicationContext(), UbahData.class);
                                in.putExtra("Nama", selection);
                                startActivity(in);
                                finish();
                                break;
                            case 2 :
                                SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
                                db.execSQL("delete from table_siswa where Nama = '"+selection+"'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
    }
}