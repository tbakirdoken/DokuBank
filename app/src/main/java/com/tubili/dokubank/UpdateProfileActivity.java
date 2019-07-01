package com.tubili.dokubank;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tubili.dokubank.Model.Demand;

public class UpdateProfileActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference userReference;

    Button btnUpdate;
    EditText etUserName, etName, etSurname,etEmail,etPassword, etPhone;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_prof);
        btnUpdate = findViewById(R.id.btnUpdate);

        etUserName = findViewById(R.id.etUsername);
        etName = findViewById(R.id.etName);
        etSurname = findViewById(R.id.etSurname);
        etPhone = findViewById(R.id.etPhone);

        etUserName.setHint("Kullanıcı Adınız: "+Common.currentUser.getUsername());
        etName.setHint("Adınız: "+Common.currentUser.getName());
        etSurname.setHint("Soyadınız: "+ Common.currentUser.getSurname());
        etPhone.setHint("Telefon Numaranız: "+ Common.currentUser.getPhone());

        firebaseDatabase = FirebaseDatabase.getInstance();
        userReference = firebaseDatabase.getReference("Users");

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUserName.getText().toString();
                String name = etName.getText().toString();
                String surName = etSurname.getText().toString();
                String phone = etPhone.getText().toString();


                if (!username.matches("") && !name.matches("") &&
                        !surName.matches("") && !phone.matches("")
                        ){

                    progressDialog = new ProgressDialog(UpdateProfileActivity.this);
                    progressDialog.setMessage("Lütfen bekleyin...");
                    progressDialog.show();

                    userReference.child(Common.currentUser.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            dataSnapshot.getRef().child("username").setValue(etUserName.getText().toString().trim());
                            dataSnapshot.getRef().child("name").setValue(etName.getText().toString().trim());
                            dataSnapshot.getRef().child("surname").setValue(etSurname.getText().toString().trim());
                            dataSnapshot.getRef().child("phone").setValue(etPhone.getText().toString().trim());


                            Toast.makeText(UpdateProfileActivity.this, "Bilgileriniz başarıyla güncellendi", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });



                }

                else if (name.matches("")){
                    Toast.makeText(UpdateProfileActivity.this, "Lütfen isminizi giriniz", Toast.LENGTH_SHORT).show();
                }

                else if (surName.matches("")){
                    Toast.makeText(UpdateProfileActivity.this, "Lütfen soyadınızı giriniz", Toast.LENGTH_SHORT).show();
                }

                else if (username.matches("")){
                    Toast.makeText(UpdateProfileActivity.this, "Lütfen kullanıcı adı giriniz", Toast.LENGTH_SHORT).show();
                }

                else if (phone.matches("")){
                    Toast.makeText(UpdateProfileActivity.this, "Lütfen telefon numarası giriniz", Toast.LENGTH_SHORT).show();
                }


            }
        });


        //Toast.makeText(UpdateProfileActivity.this, Common.currentUser.getName(), Toast.LENGTH_SHORT).show();


    }
}
