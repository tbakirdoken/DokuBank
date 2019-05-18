package com.tubili.dokubank.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andremion.counterfab.CounterFab;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tubili.dokubank.Adapter.ProfileAdapter;
import com.tubili.dokubank.ChatActivity;
import com.tubili.dokubank.Model.Chat;
import com.tubili.dokubank.Model.ProfileModel;
import com.tubili.dokubank.NotificationSettingsActivity;
import com.tubili.dokubank.Profile;
import com.tubili.dokubank.R;
import com.tubili.dokubank.UpdateProfileActivity;

import java.util.ArrayList;

public class ProfileFragment extends Fragment implements ProfileAdapter.OnItemListener {

    private ProfileAdapter profileAdapter;
    private RecyclerView recyclerview;
    private ArrayList<ProfileModel> profileModelArrayList;
    CounterFab fabMessage;
    DatabaseReference reference;
    FirebaseUser firebaseUser;

    Integer inbox[]={R.mipmap.ic_settings, R.mipmap.ic_notification_settings};
    Integer arrow[]={R.mipmap.ic_right_arrow, R.mipmap.ic_right_arrow};
    String txttrades[]={"Profili Düzenle", "Bildirim Ayarları"};
    String txthistory[]={"İsminizi, soyisminizi, yaşınızı değiştirin", "Almak istediğiniz bildirim tiplerini seçin"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fabMessage = view.findViewById(R.id.fabMessages);
        recyclerview = view.findViewById(R.id.recycler1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());

        fabMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                startActivity(intent);
            }
        });

        profileModelArrayList = new ArrayList<>();

        for (int i = 0; i < inbox.length; i++) {
            ProfileModel pview = new ProfileModel(inbox[i], arrow[i], txttrades[i], txthistory[i]);
            profileModelArrayList.add(pview);
        }
        profileAdapter = new ProfileAdapter(getContext(), profileModelArrayList, this);
        recyclerview.setAdapter(profileAdapter);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int unread = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Chat chat = snapshot.getValue(Chat.class);
                    if (chat.getReceiver().equals(firebaseUser.getUid()) && !chat.isIsseen()) {
                        unread++;
                    }
                }
                fabMessage.setCount(unread);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onItemClick(int position) {
        if (position == 0){
            Intent intent = new Intent(getActivity(), UpdateProfileActivity.class);
            startActivity(intent);
        }
        else if (position == 1){
            Intent intent = new Intent(getActivity(), NotificationSettingsActivity.class);
            startActivity(intent);
        }
    }
}