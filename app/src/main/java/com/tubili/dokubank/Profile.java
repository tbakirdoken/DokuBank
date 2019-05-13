package com.tubili.dokubank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;

import com.tubili.dokubank.Adapter.ProfileAdapter;
import com.tubili.dokubank.Model.ProfileModel;

import java.util.ArrayList;

public class Profile extends AppCompatActivity implements ProfileAdapter.OnItemListener{

    private ProfileAdapter profileAdapter;
    private RecyclerView recyclerview;
    private ArrayList<ProfileModel> profileModelArrayList;

    Integer inbox[]={R.mipmap.ic_settings, R.mipmap.ic_notification_settings, R.mipmap.ic_settings, R.mipmap.ic_settings};
    Integer arrow[]={R.mipmap.ic_right_arrow, R.mipmap.ic_right_arrow, R.mipmap.ic_right_arrow, R.mipmap.ic_right_arrow};
    String txttrades[]={"Profili Düzenle", "Bildirim Ayarları", "Profili Düzenle", "Başka Bişey Yap"};
    String txthistory[]={"İsminizi, soyisminizi, yaşınızı değiştirin", "Almak istediğiniz bildirim tiplerini seçin", "İsminizi, soyisminizi, yaşınızı değiştirin", "Ivır zıvır"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        recyclerview = findViewById(R.id.recycler1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Profile.this);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());

        profileModelArrayList = new ArrayList<>();

        for (int i = 0; i < inbox.length; i++) {
            ProfileModel view = new ProfileModel(inbox[i],arrow[i],txttrades[i],txthistory[i]);
            profileModelArrayList.add(view);
        }
        profileAdapter = new ProfileAdapter(Profile.this,profileModelArrayList, this);
        recyclerview.setAdapter(profileAdapter);
    }

    @Override
    public void onItemClick(int position) {
        if (position == 0){
            Intent intent = new Intent(this, UpdateProfileActivity.class);
            startActivity(intent);
        }
        else if (position == 1){
            Intent intent = new Intent(this, NotificationSettingsActivity.class);
            startActivity(intent);
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Action Bar içinde kullanılacak menü öğelerini inflate edelim
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottomappbar_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }*/
}
