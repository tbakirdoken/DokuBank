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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tubili.dokubank.Model.User;


public class Register extends AppCompatActivity {
    EditText txtUsername,txtName,txtSurname,txtEmail,txtPassword,txtTelephone;
    Button btnRegister;
    TextView txtLogin;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference table_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //final ProgressDialog progressDialog = new ProgressDialog(Register.this);
                //progressDialog.setMessage("Lütfen bekleyin...");
                //progressDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(txtUsername.getText().toString()).exists())
                        {
                            //progressDialog.dismiss();
                            Toast.makeText(Register.this, "Böyle bir kullanıcı zaten var!", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                           // progressDialog.dismiss();
                            User user = new User(txtName.getText().toString(), txtPassword.getText().toString()
                                                 ,txtEmail.getText().toString(),txtSurname.getText().toString()
                                                 ,txtTelephone.getText().toString());
                            table_user.child(txtUsername.getText().toString()).setValue(user);
                            Toast.makeText(Register.this, "Kullanıcı başarı ile oluşturuldu!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Register.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

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
}
