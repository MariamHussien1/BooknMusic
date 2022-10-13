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

public class recyclerviewBook extends AppCompatActivity implements recyclerview_interface{

    RecyclerView recyclerView;
    ArrayList bname, author, desc, page, pubyear, link,cover;
    ProgramHelper booksHelper;
    BookAdapter myadapterBook;
    Intent layout;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_book);

        booksHelper = new ProgramHelper(this);
        bname = new ArrayList<>();
        author = new ArrayList<>();
        desc = new ArrayList<>();
        page = new ArrayList<>();
        pubyear = new ArrayList<>();
        link = new ArrayList<>();
        cover = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        layout= new Intent(this,BookLayout.class);
        myadapterBook = new BookAdapter(this,bname, author, desc, page, pubyear, link,cover,this);
        recyclerView.setAdapter(myadapterBook);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        user=intent.getExtras().getString("user");
        displaydata();
    }
    private void displaydata(){
        Cursor c=booksHelper.DisplayBooks();
        if(c.getCount()==0){
            Toast.makeText(getApplicationContext(),"No Books Available", Toast.LENGTH_LONG).show();
        }
        else{
            while(c.moveToNext()){
                bname.add(c.getString(1));
                author.add(c.getString(2));
                desc.add(c.getString(5));
                page.add(c.getString(3));
                pubyear.add(c.getString(4));
                link.add(c.getString(6));
                cover.add(c.getString(7));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.bookmenu,menu);
        MenuItem item=menu.findItem(R.id.bsearchbtn);
        SearchView searchview = (SearchView) item.getActionView();
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                myadapterBook.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void OnItemClick(int position) {
        layout.putExtra("user", user);
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