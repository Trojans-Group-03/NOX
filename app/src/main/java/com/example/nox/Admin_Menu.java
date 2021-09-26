package com.example.nox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Admin_Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);
    }


    public void open_add_a_movie_details(View view){
        startActivity(new Intent(this,Details.class));

    }


    public void open_manage(View view){
        Intent intent= new Intent(this,Manage.class);
    }


    public void open_view_bookings(View view){
        startActivity(new Intent(this,view_booking.class));

    }


    public void open_password(View view){
        startActivity(new Intent(this,password.class));

    }
}
