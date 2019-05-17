package com.tubili.dokubank.Fragments;

import android.content.Intent;
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

import com.tubili.dokubank.Adapter.DemandAdapter;
import com.tubili.dokubank.Adapter.ProfileAdapter;
import com.tubili.dokubank.Model.Demand;
import com.tubili.dokubank.Model.ProfileModel;
import com.tubili.dokubank.NotificationSettingsActivity;
import com.tubili.dokubank.R;
import com.tubili.dokubank.UpdateProfileActivity;

import java.util.ArrayList;

public class DemandFragment extends Fragment {

    // TODO: Talepler veritabanından çekilecek
    // TODO: ArrayList'e atılacak
    // TODO: Click event'ini oluştur DemandAdapter.java

    private DemandAdapter demandAdapter;
    private RecyclerView recyclerview;
    private ArrayList<Demand> demandModelArrayList;

    Integer inbox[]={R.mipmap.ic_notification_settings};
    Integer arrow[]={R.mipmap.ic_right_arrow};
    String txttrades[]={"Profili Düzenle"};
    String txthistory[]={"İsminizi, soyisminizi, yaşınızı değiştirin"};

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


         /*String name, surname, hospitalName,city, bloodGroup, tissueType, patientAge;
            for (int i = 0; i < inbox.length; i++) {
            Demand demand = new Demand(inbox[i],arrow[i],txttrades[i],txthistory[i]);
            demandModelArrayList.add(demand);
        }
        demandAdapter = new DemandAdapter(getContext(), demandModelArrayList, this);
        recyclerview.setAdapter(demandAdapter);*/
    }

}