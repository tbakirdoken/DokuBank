package com.tubili.dokubank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnRegister, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);

    }
    public void onClick(View v) {
        if(v.getId() == R.id.btnRegister)
        {
//            SignUp
            Intent intent = new Intent(MainActivity.this, Register.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.btnLogin)
        {
//          Login
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
            finish();
        }
    }
}
