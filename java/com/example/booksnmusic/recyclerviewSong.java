package com.example.booksnmusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class recyclerviewSong extends AppCompatActivity implements recyclerview_interface {
    RecyclerView recyclerView1;
    ArrayList<String> sname, singer, duration, gerne, relyear, slink, scover;
    ProgramHelper songsHelper;
    SongAdapter myadapterSong;
    Intent layout;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_song);
        songsHelper = new ProgramHelper(this);
        sname = new ArrayList<>();
        singer = new ArrayList<>();
        duration = new ArrayList<>();
        gerne = new ArrayList<>();
        relyear = new ArrayList<>();
        slink = new ArrayList<>();
        scover = new ArrayList<>();
        recyclerView1 = findViewById(R.id.recyclerView112);
        layout= new Intent(this,SongLayout.class);
        myadapterSong = new SongAdapter(this,sname, singer, duration, gerne, relyear, slink,scover,this);
        recyclerView1.setAdapter(myadapterSong);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        user=intent.getExtras().getString("user");
        displaySdata();
    }
    private void displaySdata() {
        Cursor c=songsHelper.Displaysongs();
        if(c.getCount()==0){
            Toast.makeText(getApplicationContext(),"No Songs Available", Toast.LENGTH_LONG).show();
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
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.songmenu,menu);
        MenuItem item=menu.findItem(R.id.ssearchbtn);
        SearchView searchview = (SearchView) item.getActionView();
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                myadapterSong.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void OnItemClick(int position) {
        layout.putExtra("user", user);
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