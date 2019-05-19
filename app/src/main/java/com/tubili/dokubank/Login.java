
package com.tubili.dokubank;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tubili.dokubank.Model.User;

import io.paperdb.Paper;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.tubili.dokubank.Common.CLIENT;
import static com.tubili.dokubank.Common.SERVER;
import static com.tubili.dokubank.Common.USER_NAME;
import static com.tubili.dokubank.Common.USER_PASSWORD;
import static com.tubili.dokubank.Common.USER_PHONE;
import static com.tubili.dokubank.Common.USER_USERNAME;

public class Login extends AppCompatActivity {

    // TODO: Username alamıyoruz currentuser ile kontrol.

    Button btnLogin;
    EditText txtusername, txtpassword;
    TextView txtRegister, txtForgotPass;
    CheckBox rememberMe;
    FirebaseAuth auth;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference table_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Paper.init(this);

        auth = FirebaseAuth.getInstance();


        //database init
        firebaseDatabase = FirebaseDatabase.getInstance();
        table_user = firebaseDatabase.getReference("Users");
        //Log.i("users",table_user.getRoot().toString());


        btnLogin = findViewById(R.id.buttonLogin);
        txtusername = findViewById(R.id.editTextUsername);
        txtpassword = findViewById(R.id.editTextPassword);
        txtRegister = findViewById(R.id.textViewRegister);
        txtForgotPass = findViewById(R.id.textViewForgetPassword);

        txtForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, ResetPasswordActivity.class));

            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String txt_email = txtusername.getText().toString();
                String txt_password = txtpassword.getText().toString();

                if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)) {
                    Toast.makeText(Login.this, "All fileds are required", Toast.LENGTH_SHORT).show();
                } else {
                    final ProgressDialog progressDialog = new ProgressDialog(Login.this);
                    progressDialog.setMessage("Lütfen bekleyin...");
                    progressDialog.show();

                    auth.signInWithEmailAndPassword(txt_email, txt_password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        progressDialog.dismiss();
                                        Intent intent = new Intent(Login.this, Profile.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(Login.this, "Authentication failed!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
                /*table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //Checking User avail
                        if(dataSnapshot.child(txtusername.getText().toString()).exists())
                        {
                            //Get User data
                            progressDialog.dismiss();
                            User user = dataSnapshot.child(txtusername.getText().toString()).getValue(User.class);
                            user.setUsername(txtusername.getText().toString());
                            Log.i("snap-user",user.toString());

                            assert user != null;
                            if (user.getPassword().equals(txtpassword.getText().toString()))
                            {
                                //remember me
                                if(rememberMe.isChecked())
                                {
                                    Paper.book(CLIENT).write(USER_USERNAME, txtusername.getText().toString());
                                    Paper.book(CLIENT).write(USER_PASSWORD, txtpassword.getText().toString());
                                    Paper.book(CLIENT).write(USER_NAME, user.getName());
                                }

                                //user.setUsername(txtusername.getText().toString());
                                Intent intent = new Intent(Login.this, Profile.class);
                                Common.currentUser = user;
                                Log.i("snap-current-user",Common.currentUser.toString());
                                Toast.makeText(Login.this, Common.currentUser.getUsername(), Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                                finish();
                            } else
                            {
                                Toast.makeText(Login.this, "Giriş başarısız!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            progressDialog.dismiss();
                            Toast.makeText(Login.this, "Kullanıcı bulunamadı!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }*/

}
