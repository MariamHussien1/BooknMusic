package com.example.booksnmusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class SavedSongs extends AppCompatActivity implements recyclerview_interface{
    RecyclerView recyclerView1;
    ArrayList<String> sname, singer, duration, gerne, relyear, slink, scover;
    ProgramHelper songsHelper;
    SavedSongAdapter myadapterSong;
    Intent layout;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_songs);

        songsHelper = new ProgramHelper(this);
        sname = new ArrayList<>();
        singer = new ArrayList<>();
        duration = new ArrayList<>();
        gerne = new ArrayList<>();
        relyear = new ArrayList<>();
        slink = new ArrayList<>();
        scover = new ArrayList<>();

        recyclerView1 = findViewById(R.id.recyclerViewSaveSong);
        myadapterSong = new SavedSongAdapter(this,sname, singer, duration, gerne, relyear, slink,scover,this);
        recyclerView1.setAdapter(myadapterSong);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        layout= new Intent(this,SavedSongLayout.class);
        Intent intent = getIntent();
        user=intent.getExtras().getString("user");
        displaySdata();
    }
    private void displaySdata() {
        Cursor c=songsHelper.DisplaySavedsongs(user);
        if(c.getCount()==0){
            Toast.makeText(getApplicationContext(),"No Songs Found", Toast.LENGTH_LONG).show();
            return;
        }
        else{
            while(c.moveToNext()){
                sname.add(c.getString(1));
                singer.add(c.getString(3));
                duration.add(c.getString(4));
                gerne.add(c.getString(5));
                relyear.add(c.getString(2));
                slink.add(c.getString(6));
                scover.add(c.getString(7));
            }
        }
    }
    @Override
    public void OnItemClick(int position) {
        layout.putExtra("SongTitle",sname.get(position).toString());
        layout.putExtra("duration",duration.get(position).toString());
        layout.putExtra("gerne",gerne.get(position).toString());
        layout.putExtra("release",relyear.get(position).toString());
        layout.putExtra("singer",singer.get(position).toString());
        layout.putExtra("songcover",scover.get(position).toString());
        layout.putExtra("songlink",slink.get(position).toString());
        startActivity(layout);
    }
}