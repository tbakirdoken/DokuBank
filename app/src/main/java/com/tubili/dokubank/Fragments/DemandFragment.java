package com.tubili.dokubank.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tubili.dokubank.Adapter.DemandAdapter;
import com.tubili.dokubank.Adapter.ProfileAdapter;
import com.tubili.dokubank.Common;
import com.tubili.dokubank.Login;
import com.tubili.dokubank.Model.Demand;
import com.tubili.dokubank.Model.ProfileModel;
import com.tubili.dokubank.Model.User;
import com.tubili.dokubank.NotificationSettingsActivity;
import com.tubili.dokubank.Profile;
import com.tubili.dokubank.R;
import com.tubili.dokubank.UpdateProfileActivity;

import java.util.ArrayList;

import io.paperdb.Paper;

import static com.tubili.dokubank.Common.CLIENT;
import static com.tubili.dokubank.Common.USER_NAME;
import static com.tubili.dokubank.Common.USER_PASSWORD;
import static com.tubili.dokubank.Common.USER_USERNAME;

public class DemandFragment extends Fragment {

    // TODO: Talepler veritabanından çekilecek
    // TODO: ArrayList'e atılacak
    // TODO: Click event'ini oluştur DemandAdapter.java

    private DemandAdapter demandAdapter;
    private RecyclerView recyclerview;
    private ArrayList<Demand> demandModelArrayList;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference table_demands;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_demands,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerview = view.findViewById(R.id.recycler_demands);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());

        demandModelArrayList = new ArrayList<>();

        firebaseDatabase = FirebaseDatabase.getInstance();
        table_demands = firebaseDatabase.getReference("Demand");

        table_demands.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    Demand demand = ds.getValue(Demand.class);
                    demand.setDemandId(ds.getKey().toString());
                    demandModelArrayList.add(demand);
                }

                demandAdapter = new DemandAdapter(getContext(), demandModelArrayList);
                recyclerview.setAdapter(demandAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}