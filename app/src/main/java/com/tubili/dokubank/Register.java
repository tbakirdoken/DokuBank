package com.tubili.dokubank;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tubili.dokubank.Model.User;

import java.util.HashMap;


public class Register extends AppCompatActivity {
    EditText txtUsername,txtName,txtSurname,txtEmail,txtPassword,txtTelephone;
    Button btnRegister;
    TextView txtLogin;

    private DatabaseReference mDatabase;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference table_user;
    private FirebaseAuth mAuth;
    private ProgressDialog mRegProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mRegProgress = new ProgressDialog(this);

        txtUsername = findViewById(R.id.editTextUsername);
        txtName =findViewById(R.id.editTextFirstName);
        txtSurname = findViewById(R.id.editTextSurName);
        txtEmail = findViewById(R.id.editTextEmail);
        txtPassword = findViewById(R.id.editTextPassword);
        txtTelephone = findViewById(R.id.editTextTelephone);
        txtLogin = findViewById(R.id.textViewLogin);

        btnRegister = findViewById(R.id.buttonRegister);

        //Firebase Init
        firebaseDatabase = FirebaseDatabase.getInstance();
        table_user = firebaseDatabase.getReference("User");
        Log.i("user",table_user.getKey());
        Log.i("user",table_user.getDatabase().toString());

        mAuth = FirebaseAuth.getInstance();


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = txtUsername.getText().toString();
                String name = txtName.getText().toString();
                String surName = txtSurname.getText().toString();
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                String telephone = txtTelephone.getText().toString();

                if (!userName.matches("") && !name.matches("") &&
                        !surName.matches("") && !email.matches("") &&
                        !password.matches("") && !telephone.matches("") ){

                    mRegProgress.setTitle("Kayıt Oluşturuluyor");
                    mRegProgress.setMessage("Hesabınız oluşturulurken lütfen bekleyiniz");
                    mRegProgress.setCanceledOnTouchOutside(false);
                    mRegProgress.show();
                    register_user(email, password);

                }

                else if (userName.matches("")){
                    Toast.makeText(Register.this, "Lütfen kullanıcı adınızı giriniz", Toast.LENGTH_SHORT).show();
                }

                else if (name.matches("")){
                    Toast.makeText(Register.this, "Lütfen isminizi giriniz", Toast.LENGTH_SHORT).show();
                }

                else if (surName.matches("")){
                    Toast.makeText(Register.this, "Lütfen soyisminizi giriniz", Toast.LENGTH_SHORT).show();
                }

                else if (email.matches("")){
                    Toast.makeText(Register.this, "Lütfen e-posta adresinizi giriniz", Toast.LENGTH_SHORT).show();
                }

                else if (password.matches("")){
                    Toast.makeText(Register.this, "Lütfen parolanızı giriniz", Toast.LENGTH_SHORT).show();
                }

                else if (telephone.matches("")){
                    Toast.makeText(Register.this, "Lütfen telefon numaranızı giriniz", Toast.LENGTH_SHORT).show();
                }


            }
        });
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void register_user(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                            String uid = current_user.getUid();

                            mDatabase = FirebaseDatabase.getInstance().getReference().child("User").child(uid);

                            HashMap<String, String> userMap = new HashMap<>();
                            userMap.put("username", txtUsername.getText().toString());
                            userMap.put("name", txtName.getText().toString());
                            userMap.put("surname", txtSurname.getText().toString());
                            userMap.put("phone", txtTelephone.getText().toString());
                            userMap.put("image", "default");
                            userMap.put("thumb_image", "default");

                            mDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        mRegProgress.dismiss();
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(Register.this, "Kullanıcı başarı ile oluşturuldu!", Toast.LENGTH_SHORT).show();
                                        Intent mainIntent = new Intent(Register.this, Profile.class);
                                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(mainIntent);
                                        finish();
                                    } else {
                                        Toast.makeText(Register.this, "Parola en az 8 karakter olmalıdır. Hesap oluşturulamadı. Daha sonra tekrar deneyiniz.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        } else {
                            mRegProgress.hide();
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Register.this, "Parola en az 8 karakter olmalıdır. Hesap oluşturulamadı. Daha sonra tekrar deneyiniz.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
