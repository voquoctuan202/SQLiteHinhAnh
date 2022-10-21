package com.example.sqlitehinhanh;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity2 extends AppCompatActivity {
    EditText ten, mota;
    ImageView img;
    ImageButton camera, folder;
    Button them, huy;
    int REQUEST_CODE_CAMERA =123;
    int REQUEST_CODE_FOLDER =456;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ten = (EditText) findViewById(R.id.editThemTenDoVat);
        mota = (EditText) findViewById(R.id.editThemMotTa);
        img = (ImageView) findViewById(R.id.imageHinhAnh);
        camera = (ImageButton) findViewById(R.id.imageButtonCamera);
        folder = (ImageButton) findViewById(R.id.imageButtonFolder);
        them = (Button) findViewById(R.id.buttonThem);
        huy=  (Button) findViewById(R.id.buttonHuy);


        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                ActivityCompat.requestPermissions(MainActivity2.this,
                        new String[]{Manifest.permission.CAMERA},REQUEST_CODE_CAMERA);
                startActivityForResult(intent,REQUEST_CODE_CAMERA);
            }
        });
        folder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_FOLDER);

            }
        });
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chuyen img view tu bitmap sang byte[]
                BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable(); // lay du lieu hinh anh tu img
                Bitmap bitmap = bitmapDrawable.getBitmap(); // chuyen du lieu tu img sang bitmap
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream(); // khoi tao bien byteArray de luu anh keu byte[]
                bitmap.compress(Bitmap.CompressFormat.PNG,100 /*so cang nho cang chat luong*/,byteArray); // chuyen doi kieu tu bitmap sang kieu byte[] dong thoi gan cho byteArray
                byte[] hinhanh = byteArray.toByteArray(); //chuyen du lieu sang file hinh anh
                MainActivity.database.insertDoVat(
                        ten.getText().toString().trim(),
                        mota.getText().toString().trim(),
                        hinhanh
                );
                Toast.makeText(MainActivity2.this,"Da them hinh anh",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity2.this,MainActivity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_CODE_CAMERA && resultCode==RESULT_OK && data!=null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            img.setImageBitmap(bitmap);
        }
        if(requestCode== REQUEST_CODE_FOLDER && resultCode==RESULT_OK && data!=null){
            Uri uri= data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}