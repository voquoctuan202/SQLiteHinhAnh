package com.example.sqlitehinhanh;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public void QueryData(String str){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(str);
    }

    public void insertDoVat(String ten, String mota, byte[] hinhanh ){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO DoVat VALUES(null,?,?,?) ";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,ten); // index tai vi tri 1 tuc la ten do vat trong bang DoVat
        statement.bindString(2,mota);// index tai vi tri 2 tuc la mota  do vat trong bang DoVat
        statement.bindBlob(3,hinhanh); // index tai vi tri 3 tuc la hinhanh do vat trong bang DoVat, do blindBlob nhan du lieu la byte[] nen hinhanh duoc khai bao la byte[]

        statement.executeInsert();
    }
    public Cursor getData(String str){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(str,null);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
