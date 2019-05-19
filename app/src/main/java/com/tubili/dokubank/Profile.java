package com.tubili.dokubank;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tubili.dokubank.Fragments.AddNewFragment;
import com.tubili.dokubank.Fragments.DemandFragment;
import com.tubili.dokubank.Fragments.NewsFragment;
import com.tubili.dokubank.Fragments.ProfileFragment;
import com.tubili.dokubank.Fragments.SearchFragment;
import com.tubili.dokubank.Model.User;

public class Profile extends AppCompatActivity{

    FirebaseUser firebaseUser;
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.logout_menu,menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.i("Deneme -Activite","Acti");

        Toolbar myToolbar = findViewById(R.id.toolbar_profile);
        setSupportActionBar(myToolbar);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.addAuthStateListener(authStateListener);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);
                Common.currentUser = user;
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.getMenu().getItem(4).setChecked(true);
        CountDownTimer countDownTimer = new CountDownTimer(150, 50) {

            public void onTick(long millisUntilFinished) { }

            public void onFinish() {

                bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,new ProfileFragment()).commit();
            }
        };
        countDownTimer.start();


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

            Toolbar myToolbar = findViewById(R.id.toolbar_profile);
            setSupportActionBar(myToolbar);
            return true;
        }
    };
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout_menu:
                firebaseAuth.signOut();
                // User chose the "Settings" item, show the app settings UI...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
    FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            if (firebaseAuth.getCurrentUser() == null){
                //Do anything here which needs to be done after signout is complete
                Intent intent = new Intent(Profile.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            else {
            }
        }
    };

//    public void setActionBarTitle(String title) {
//        getSupportActionBar().setTitle(title);
//    }
}
