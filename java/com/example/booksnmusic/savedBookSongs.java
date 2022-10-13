package com.example.booksnmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class savedBookSongs extends AppCompatActivity {

    Button books,songs;
    Intent savedbooks,savedsongs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_book_songs);
        books=(Button) findViewById(R.id.button2);
        songs=(Button) findViewById(R.id.button);

        Intent intent = getIntent();
        String user=intent.getExtras().getString("user");

        savedbooks= new Intent(savedBookSongs.this,SavedBooks.class);
        savedsongs= new Intent(savedBookSongs.this,SavedSongs.class);

        books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedbooks.putExtra("user", user);
                startActivity(savedbooks);
            }
        });

        songs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedsongs.putExtra("user", user);
                startActivity(savedsongs);
            }
        });
    }
}