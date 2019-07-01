package com.tubili.dokubank;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tubili.dokubank.Adapter.DemandAdapter;
import com.tubili.dokubank.Adapter.MyDemandAdapter;
import com.tubili.dokubank.Model.Demand;

import java.util.ArrayList;

public class MyDemandsActivity extends AppCompatActivity {

    private MyDemandAdapter demandAdapter;
    private RecyclerView recyclerview;
    private ArrayList<Demand> demandModelArrayList;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference table_mydemands;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_demands);

        recyclerview = findViewById(R.id.recycler_mydemands);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MyDemandsActivity.this);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());

        demandModelArrayList = new ArrayList<>();

        String userId = Common.currentUser.getId();
        firebaseDatabase = FirebaseDatabase.getInstance();
        table_mydemands = firebaseDatabase.getReference("Demand");

        table_mydemands.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    Log.i("values",ds.child("userId").getValue().toString());
                    if(Common.currentUser.getId().equals(ds.child("userId").getValue())){
                        Demand demand = ds.getValue(Demand.class);
                        demand.setDemandId(ds.getKey().toString());
                        demandModelArrayList.add(demand);
                    }

                }

                demandAdapter = new MyDemandAdapter(MyDemandsActivity.this, demandModelArrayList);
                recyclerview.setAdapter(demandAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MyDemandsActivity.this, Profile.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
