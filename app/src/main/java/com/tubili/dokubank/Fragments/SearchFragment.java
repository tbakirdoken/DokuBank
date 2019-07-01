package com.tubili.dokubank.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tubili.dokubank.Adapter.DemandAdapter;
import com.tubili.dokubank.Model.Demand;
import com.tubili.dokubank.R;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    Spinner spinnerOptions;

    RecyclerView recyclerSearch;
    Button btnTik;
    private DemandAdapter demandAdapter;
    private ArrayList<Demand> demandModelArrayList;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference demands;

    String[] bloodTypeArray = {"ABRh+", "ABRh-", "ARh+", "ARh-", "BRh+", "BRh-", "0Rh+", "0Rh-"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerSearch = RootView.findViewById(R.id.recycler_search);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerSearch.setLayoutManager(layoutManager);
        recyclerSearch.setItemAnimator(new DefaultItemAnimator());

        btnTik = RootView.findViewById(R.id.btnTik);

        firebaseDatabase = FirebaseDatabase.getInstance();
        demands = firebaseDatabase.getReference("Demand");

        spinnerOptions = RootView.findViewById(R.id.spinnerOptions);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, bloodTypeArray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerOptions.setAdapter(adapter);

        demandModelArrayList = new ArrayList<>();

        btnTik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String bloodType = spinnerOptions.getSelectedItem().toString();
                demandModelArrayList.clear();
                demands.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            if (ds.child("bloodGroup").getValue(String.class).contains(bloodType)) {
                                Demand demand = ds.getValue(Demand.class);
                                demandModelArrayList.add(demand);
                            }
                        }

                        if(demandModelArrayList.size() == 0) {
                            Toast.makeText(getContext(), "Bu kan grubuna ait talep bulunamadı.", Toast.LENGTH_SHORT).show();
                        }
                        demandAdapter = new DemandAdapter(getContext(), demandModelArrayList);
                        recyclerSearch.setAdapter(demandAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        return RootView;
    }
}
