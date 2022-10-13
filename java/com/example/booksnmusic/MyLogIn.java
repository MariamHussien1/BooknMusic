package com.example.booksnmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MyLogIn extends AppCompatActivity {
EditText svdName,svdPass;
Button confirm;
final ProgramHelper dblog=new ProgramHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_log_in);
        svdName=(EditText) findViewById(R.id.usrNm);
        svdPass=(EditText)findViewById(R.id.logpass);
        confirm=(Button)findViewById(R.id.lgn);
        Intent home=new Intent(MyLogIn.this,UserMain.class);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dblog.LoginAuth(svdName.getText().toString())){
                    if (dblog.AuthPassnUser(svdName.getText().toString(),svdPass.getText().toString())){
                        home.putExtra("user", svdName.getText().toString());
                        startActivity(home);
                    }else Toast.makeText(getApplicationContext(),"Wrong Name or Password",Toast.LENGTH_LONG).show();
                }else Toast.makeText(getApplicationContext(),"User Not Found",Toast.LENGTH_LONG).show();


            }
        });
    }
}