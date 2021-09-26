package com.example.nox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity4 extends AppCompatActivity {


    TextInputLayout regFirstname,regLastname,regNIC,regEmail,regPassword;
    Button regupdate,regdelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        regFirstname = findViewById(R.id.firstname);
        regLastname = findViewById(R.id.lastname);
        regNIC = findViewById(R.id.nic);
        regPassword = findViewById(R.id.password);

        regEmail = findViewById(R.id.email);

        regupdate = findViewById(R.id.update);
        regdelete = findViewById(R.id.delete);


        regdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setButtonDelete();
            }
        });


        regupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setButtonUpdate();
            }
        });
    }

    public void setButtonDelete(){

        String tempEmail = regEmail.getEditText().getText().toString();
        final DatabaseReference[] readRef = {FirebaseDatabase.getInstance().getReference().child("Users").child(tempEmail)};

        readRef[0].addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()){

                    readRef[0] = FirebaseDatabase.getInstance().getReference().child("Users").child(tempEmail);
                    readRef[0].removeValue();
                    Toast.makeText(getApplicationContext(), "Data deleted Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity4.this, MainActivity4.class));

                }

                else
                    Toast.makeText(getApplicationContext(), "No Source to delete", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
    }
    public void setButtonUpdate(){
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Users");

        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String tempEmail = regEmail.getEditText().getText().toString();
                Users updateUser = new Users();
                if (dataSnapshot.hasChild(tempEmail)){

                    try{

                        updateUser.setFirstname(regFirstname.getEditText().getText().toString().trim());
                        updateUser.setLastname(regLastname.getEditText().getText().toString().trim());
                        updateUser.setNic(regNIC.getEditText().getText().toString().trim());
                        updateUser.setPassword(regPassword.getEditText().getText().toString().trim());
                        updateUser.setEmail(regEmail.getEditText().getText().toString().trim());

                        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Users").child("tempEmail");
                        dbRef.setValue(updateUser);



                        Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity4.this, MainActivity4.class));

                    }

                    catch (NumberFormatException e){

                        Toast.makeText(getApplicationContext(), "Invalid Data", Toast.LENGTH_SHORT).show();

                    }

                }

                else {
                    Toast.makeText(getApplicationContext(), "No Source to update", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}