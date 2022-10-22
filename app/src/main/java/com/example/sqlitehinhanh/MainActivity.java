package com.example.sqlitehinhanh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn;
    ListView listDoVat;
    ArrayList<DoVat> arrayDoVat;
    DoVatAdapter doVatAdapter;
    public static Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById((R.id.buttonThemAnh));
        listDoVat = (ListView) findViewById(R.id.listviewDoVat);
        arrayDoVat = new ArrayList<>();
        doVatAdapter = new DoVatAdapter(this,R.layout.dong_do_vat,arrayDoVat);
        listDoVat.setAdapter(doVatAdapter);

        database = new Database(this,"Quanli.sqlite",null,1);
        database.QueryData("CREATE TABLE IF NOT EXISTS DoVat(Id INTEGER PRIMARY KEY AUTOINCREMENT, TenDoVat VARCHAR(50),MoTa VARCHAR(250) , HinhAnh BLOG)   ");
        //get Data
        Cursor cursor= database.getData("SELECT * FROM DoVat");
        while (cursor.moveToNext()){
            arrayDoVat.add(new DoVat(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getBlob(3)
            ));
        }
        doVatAdapter.notifyDataSetChanged();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainActivity2.class));
            }
        });
    }
}