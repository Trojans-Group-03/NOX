package com.example.nox;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Movie;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Details extends AppCompatActivity {
    Button button_3;
    ImageView imageview;
    EditText et_1, et_2, et_3, et_4;
    Button button2;

    DatabaseReference adddataRef;

    private int REQUEST_STORAGE = 111;
    private int REQUEST_FILE = 222;
    private int requestCode;
    private Uri uri;
    private String stringPath;
    private Intent iData;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        button_3 = findViewById(R.id.button_3);
        imageview = findViewById(R.id.imageview);


        et_1 = findViewById(R.id.et_1);
        et_2 = findViewById(R.id.et_2);
        et_3 = findViewById(R.id.et_3);
        et_4 = findViewById(R.id.et_4);
        button2 = findViewById(R.id.button2);


        adddataRef = FirebaseDatabase.getInstance().getReference().child("Person");

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insertPersonData();


                button_3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(Details.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_STORAGE);

                        } else {
                            SelectImage();
                        }
                    }
                });

                imageview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (checkReadStrongAllowed()) {

//                    int idPosition = (arrayList.get(position)).getnid();

                            File imgFile = new File(stringPath);
                            if (imgFile.exists()) {

                                Uri uri;
                                uri = FileProvider.getUriForFile(Details.this, Details.this.getPackageName() + "," + BuildConfig.APPLICATION_ID + ".provider", imgFile);

                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_VIEW);
                                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                intent.setDataAndType(uri, "image/*");
                                Details.this.startActivity(intent);

                            } else {
                                ActivityCompat.requestPermissions((Activity) Details.this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, REQUEST_STORAGE);
                            }
                        }
                    }
                });
            }

            private void SelectImage() {

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_FILE);
            }

            @Override
            public void onActivityReenter(int resultCode, Intent data) {
                super.onActivityReenter(resultCode, data);

                if (requestCode == REQUEST_FILE && resultCode == RESULT_OK) {

                    if (data != null) {
                        uri = data.getData();
                        iData = data;

                        getStringPath(uri);


                        try {
                            InputStream inputStream = getContentResolver().openInputStream(uri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            imageview.setImageBitmap(bitmap);

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }

            private String getStringPath(Uri myUri) {
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(myUri, filePathColumn, null, null, null);

                if (cursor == null) {
                    stringPath = myUri.getPath();
                } else {
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    stringPath = cursor.getString(columnIndex);
                    cursor.close();
                }
                return stringPath;

            }

            public boolean checkReadStrongAllowed() {
                if (Build.VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(Details.this, "android .permission.WRITE_EXTERNAL_STORAGE") == 0) {
                    return true;
                }
                return false;
            }


            private void insertPersonData() {

                String name = et_1.getText().toString().trim();
                String email = et_2.getText().toString().trim();

                Movie movie = new Movie(MovieName, Duration, ReleasedDate, Description);


                adddataRef.push().setValue(movie);
                Toast.makeText(Details.this, "Data Inserted", Toast.LENGTH_SHORT).show();
            }
        });}}