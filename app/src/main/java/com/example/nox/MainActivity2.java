package com.example.nox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {

    TextInputLayout regName,regEmail,regPassword;
    Button regbtn,reglogin;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        regName =findViewById(R.id.username);
        regEmail =findViewById(R.id.email);
        regPassword = findViewById(R.id.password);
        regbtn = findViewById(R.id.signUp);
        reglogin = findViewById(R.id.reglogin);

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();

            }
        });
    }
}