package com.example.nox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {

    TextInputLayout regFirstname,regLastname,regNIC,regEmail,regPassword;
    Button regbtn,reglogin;
    int userId = 001;

   DatabaseReference dbref;
   Users user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        regFirstname = findViewById(R.id.firstname);
        regLastname = findViewById(R.id.lastname);
        regNIC = findViewById(R.id.nic);
        regEmail = findViewById(R.id.email);
        regPassword = findViewById(R.id.password);
        regbtn = findViewById(R.id.signUp);
        reglogin = findViewById(R.id.reglogin);


        user = new Users();
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbref = FirebaseDatabase.getInstance().getReference().child("Users");

                try {
                    if(TextUtils.isEmpty(regFirstname.getEditText().getText().toString()))
                        Toast.makeText(getApplicationContext(),"please enter a name",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(regLastname.getEditText().getText().toString()))
                        Toast.makeText(getApplicationContext(),"please enter a email",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(regNIC.getEditText().getText().toString()))
                        Toast.makeText(getApplicationContext(),"please enter a email",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(regEmail.getEditText().getText().toString()))
                        Toast.makeText(getApplicationContext(),"please enter a email",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(regPassword.getEditText().getText().toString()))
                        Toast.makeText(getApplicationContext(),"please enter a password",Toast.LENGTH_SHORT).show();

                    else{
                        user.setFirstname(regFirstname.getEditText().getText().toString().trim());
                        user.setLastname(regLastname.getEditText().getText().toString().trim());
                        user.setNic(regNIC.getEditText().getText().toString().trim());
                        user.setEmail(regEmail.getEditText().getText().toString().trim());
                        user.setPassword(regPassword.getEditText().getText().toString().trim());

                       userId++;
                        int id1=userId;

                        String setEmail = regEmail.getEditText().getText().toString().trim();

                        dbref.child(setEmail).setValue(user);

                        Toast.makeText(getApplicationContext(),"Data saved successfully",Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(MainActivity2.this,login.class));
                    }

                }

                catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(),"Invalid ",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}