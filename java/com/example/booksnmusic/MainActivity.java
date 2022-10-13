package com.example.booksnmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
  Button signup,Login;
  Intent sgn,lgn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signup=(Button)findViewById(R.id.SignUp);
        Login=(Button)findViewById(R.id.login);
        lgn=new Intent(MainActivity.this,MyLogIn.class);
        sgn=new Intent(MainActivity.this,MySignUp.class);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(sgn);
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(lgn);
            }
        });

    }
}