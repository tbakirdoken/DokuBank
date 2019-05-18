package com.tubili.dokubank;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.tubili.dokubank.Adapter.ProfileAdapter;
import com.tubili.dokubank.Fragments.AddNewFragment;
import com.tubili.dokubank.Fragments.DemandFragment;
import com.tubili.dokubank.Fragments.NewsFragment;
import com.tubili.dokubank.Fragments.ProfileFragment;
import com.tubili.dokubank.Fragments.SearchFragment;
import com.tubili.dokubank.Model.ProfileModel;

import java.util.ArrayList;

public class Profile extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.getMenu().getItem(4).setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,new ProfileFragment()).commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;
            switch (menuItem.getItemId()) {
                case R.id.app_bar_gönderiler:
                    selectedFragment = new DemandFragment();
                    break;
                case R.id.app_bar_ara:
                    selectedFragment = new SearchFragment();
                    break;
                case R.id.app_bar_haberler:
                    selectedFragment = new NewsFragment();
                    break;
                case R.id.app_bar_talep_ekle:
                    selectedFragment = new AddNewFragment();
                    break;
                case R.id.app_bar_profil:
                    selectedFragment = new ProfileFragment();
                    break;

            }
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
