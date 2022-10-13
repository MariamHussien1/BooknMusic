package com.example.booksnmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MySignUp extends AppCompatActivity {
 EditText usr,maile,pswd;
Button finish;
final ProgramHelper DbHelp=new ProgramHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sign_up);
        usr=(EditText) findViewById(R.id.user);
        maile=(EditText) findViewById(R.id.mymail);
        pswd=(EditText) findViewById(R.id.mypass);
        finish=(Button)findViewById(R.id.finish);
        Intent userHome=new Intent(MySignUp.this,UserMain.class);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DbHelp.signUpUser(usr.getText().toString(),maile.getText().toString(),pswd.getText().toString());
                boolean y= DbHelp.signUpUser(usr.getText().toString(),maile.getText().toString(),pswd.getText().toString());
                if(y) {
                    userHome.putExtra("user", usr.getText().toString());
                    startActivity(userHome);
                }else Toast.makeText(getApplicationContext(), "sign up Failed!", Toast.LENGTH_LONG).show();
            }
        });
    }
}