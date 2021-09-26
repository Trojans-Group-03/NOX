package com.example.nox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class SaveCard extends AppCompatActivity {

    private EditText NameOnCard;
    private EditText CardNumber;
    private View ExpDate;
    private View Cvv;
    private Button SaveBtn, ViewBtn;
    private FirebaseFirestore db;
    private String updateNameOnCard, updateCardNumber , updateExpDate , updateCvv, updateId ;

    @SuppressLint("WrongViewCast")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_card);

        NameOnCard = findViewById(R.id.Name_On_Card); //text id
        CardNumber = findViewById(R.id.Ccard_Number); //text id
        ExpDate = findViewById(R.id.Exp_Date); //text id
        Cvv = findViewById(R.id.Cvv_number); //text id
        SaveBtn = findViewById(R.id.SaveBtn); //button id
        ViewBtn = findViewById(R.id.ViewBtn); //button id

        db= FirebaseFirestore.getInstance();



        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            SaveBtn.setText("Update");
            updateNameOnCard = bundle.getString("updateNameOnCard");
            updateCardNumber = bundle.getString("updateCardNumber");
            updateExpDate = bundle.getString("updateExpDate");
            updateCvv = bundle.getString("updateCvv");
            updateId = bundle.getString("updateId");

            NameOnCard.setText(updateNameOnCard);
            CardNumber.setText(updateCardNumber);

            /**
             ExpDate.getText(updateExpDate);
             Cvv.setText(updateCvv);
             */




        }else{
            SaveBtn.setText("Save");
        }


//******************* view button click and go to the view card class ***************************

        ViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SaveCard.this ,ViewCards.class) );

            }
        });




//******************* save button ****************************************************************

        SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String CardName = NameOnCard.getText().toString();
                String CNumber = CardNumber.getText().toString();
                /**
                 String Expdate = updateId.getText().toString();
                 String CVV = ExpDate.getText().toString();

                 */


                /**
                 Bundle bundle1 = getIntent().getExtras();
                 if (bundle1 !=null){
                 String id = updateId;
                 updateToFireStore(id , CNumber, CardName , Expdate);
                 }else{
                 String id = UUID.randomUUID().toString();
                 saveToFireStore (id , CardName , CNumber, Expdate , CVV );
                 }

                 private void updateToFireStore(String id , String CardName, String desc){}

                 */










            }
        });
    }

//***************************************************** save the card ****************************************************************


    private void saveToFireStore(String id , String CardName , String CNumber ,String Expdate , String CVV){

        if (!CardName.isEmpty() && !CNumber.isEmpty() && !Expdate.isEmpty() && !CVV.isEmpty()){

            HashMap<String , Object> map = new HashMap<>();
            map.put("id" , id);
            map.put("CardName" , CardName);
            map.put(" CNumber" , CNumber);
            map.put("Expdate" , Expdate);
            map.put("CVV" , CVV);

            db.collection("CreditCardSave").document(id).set(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(SaveCard.this, "Card Saved !!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SaveCard.this, "Save Failed !!", Toast.LENGTH_SHORT).show();
                }
            });
        }

        else
            Toast.makeText(this, "Empty Fields not Allowed", Toast.LENGTH_SHORT).show();


    }


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_card);
    }
}