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
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class SavedBooks extends AppCompatActivity implements recyclerview_interface{
    ProgramHelper saveBook;
    ArrayList bname, author, desc, page, pubyear, link,cover;
    RecyclerView recyclerView;
    SavedBookAdapter myadapterSBook;
    Intent layout;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_books);

        saveBook= new ProgramHelper(this);

        bname = new ArrayList<>();
        author = new ArrayList<>();
        desc = new ArrayList<>();
        page = new ArrayList<>();
        pubyear = new ArrayList<>();
        link = new ArrayList<>();
        cover = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerViewSave);
        myadapterSBook = new SavedBookAdapter(this,bname, author, desc, page, pubyear, link,cover,this);
        recyclerView.setAdapter(myadapterSBook);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        layout= new Intent(this,SavedBookLayout.class);
        Intent intent = getIntent();
        user=intent.getExtras().getString("user");
        displaydata();
    }
    private void displaydata(){
        Cursor c=saveBook.DisplaySavedbooks(user);
        if(c.getCount()==0){
            Toast.makeText(getApplicationContext(),"No Books Found", Toast.LENGTH_LONG).show();
        }
        else{
            while(c.moveToNext()){
                bname.add(c.getString(1));
                author.add(c.getString(2));
                page.add(c.getString(3));
                pubyear.add(c.getString(4));
                desc.add(c.getString(5));
                link.add(c.getString(6));
                cover.add(c.getString(7));
            }
        }
    }

    @Override
    public void OnItemClick(int position) {
        layout.putExtra("BookTitle",bname.get(position).toString());
        layout.putExtra("descrep",desc.get(position).toString());
        layout.putExtra("Pagenum",page.get(position).toString());
        layout.putExtra("publish",pubyear.get(position).toString());
        layout.putExtra("Author",author.get(position).toString());
        layout.putExtra("cover",cover.get(position).toString());
        layout.putExtra("link",link.get(position).toString());
        startActivity(layout);
    }
}