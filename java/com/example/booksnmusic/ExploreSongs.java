package com.example.booksnmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ExploreSongs extends AppCompatActivity {
    Button addd, view1;
    EditText songname, singername, duration, link1, genre, relyear,cover1;
    ProgramHelper songDB;
    Intent recycS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_songs);
        songname=(EditText) findViewById(R.id.songname11);
        singername=(EditText) findViewById(R.id.singerName1);
        duration=(EditText) findViewById(R.id.duration1);
        link1=(EditText) findViewById(R.id.songlink1);
        genre=(EditText) findViewById(R.id.genre1);
        relyear=(EditText) findViewById(R.id.pubyear1);
        cover1=(EditText) findViewById(R.id.songcover1);
        addd=(Button) findViewById(R.id.insert);
        view1=(Button) findViewById(R.id.view1);
        songDB = new ProgramHelper(this);
        recycS=new Intent(ExploreSongs.this,recyclerviewSong.class);

        Intent intent = getIntent();
        String user=intent.getExtras().getString("user");

        addd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtsongname = songname.getText().toString();
                String txtsingername = singername.getText().toString();
                String txtduration = duration.getText().toString();
                String txtsonglink = link1.getText().toString();
                String txtgerne = genre.getText().toString();
                String txtrelyear = relyear.getText().toString();
                String txtsongcover = cover1.getText().toString();
                boolean checkdaata=songDB.addSong(txtsongname,txtsingername,txtduration,txtgerne,txtrelyear,txtsonglink,txtsongcover);
                if(checkdaata==true){
                    Toast.makeText(getApplicationContext(),"Song Added Successfully", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recycS.putExtra("user", user);
                startActivity(recycS);
            }
        });
    }
}