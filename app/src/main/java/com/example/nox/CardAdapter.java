package com.example.nox;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private ViewCards activity;
    private List<model> cList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public CardAdapter(ViewCards activity , List<model> cList){
        this.activity = activity;
        this.cList = cList;
    }



// *********************************************** update *********************************************************

    public void updateCard(int position){
        model item = cList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("updateId" , item.getId());
        bundle.putString("updateNameOnCard" , item.getCardName());
        bundle.putString("updateCardNumber" , item.getCNumber());
        bundle.putString("updateExpDate" , item.getExpdate());
        bundle.putString("updateCvv" , item.getCVV());
        Intent intent = new Intent(activity , MainActivity.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.item , parent , false);
        return new CardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.NameOnCard.setText(cList.get(position).getCardName());  //item 2 only*********************************************
        holder.CardNumber.setText(cList.get(position).getCNumber());  //item 2 only*********************************************

    }

    @Override
    public int getItemCount() {
        return cList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        TextView NameOnCard , CardNumber;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);


            NameOnCard = itemView.findViewById(R.id.Card_Name);  //item 2 only*********************************************
            CardNumber = itemView.findViewById(R.id.Card_Number); //item 2 only*********************************************

        }



    }





}
