package com.example.booksnmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UserMain extends AppCompatActivity {

    Button expBooks,expSongs,logout,saved;
    Intent songs,addexp,savedsb,logoutt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        expBooks=(Button)findViewById(R.id.bookb);
        expSongs=(Button)findViewById(R.id.songb);
        logout=(Button) findViewById(R.id.logout);
        saved=(Button) findViewById(R.id.saved);

        addexp= new Intent(UserMain.this,ExploreBooks.class);
        songs = new Intent(UserMain.this,ExploreSongs.class);
        savedsb = new Intent(UserMain.this,savedBookSongs.class);
        logoutt= new Intent(UserMain.this,MainActivity.class);

        Intent intent = getIntent();
        String user=intent.getExtras().getString("user");

        expBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addexp.putExtra("user", user);
                startActivity(addexp);
            }
        });

       expSongs.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               songs.putExtra("user", user);
               startActivity(songs);
           }
       });

       logout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(logoutt);
           }
       });

       saved.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               savedsb.putExtra("user", user);
               startActivity(savedsb);
           }
       });
    }
}