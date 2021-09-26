package com.example.nox;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewCards extends AppCompatActivity {

    private RecyclerView RecyclerView;
    private FirebaseFirestore db;
    private CardAdapter adapter;
    private List<model>list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cards);


        RecyclerView = findViewById(R.id.recyclerview); //recyclerview id name
        RecyclerView.setHasFixedSize(true);
        RecyclerView.setLayoutManager(new LinearLayoutManager(this));


        db= FirebaseFirestore.getInstance();
        list = new ArrayList<>();
        adapter = new CardAdapter(this , list);
        RecyclerView.setAdapter(adapter);

        ViewCard(); //showcard






    }
//********************************************************** view the card *************************************************************

    public void ViewCard(){ //showcard

        db.collection("CreditCardSave").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        for (DocumentSnapshot snapshot : task.getResult()){

                            model model = new model(snapshot.getString("id") , snapshot.getString("cardName") , snapshot.getString("CNumber") , snapshot.getString("Expdate"), snapshot.getString("CVV"));
                            list.add(model);
                        }
                        adapter.notifyDataSetChanged();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ViewCards.this, "Oops ... something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}