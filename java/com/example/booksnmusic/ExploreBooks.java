package com.example.booksnmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ExploreBooks extends AppCompatActivity {
    Button add, view;
    EditText bookname, authorname, desc, link, numofpages, pubyear,cover;
    final ProgramHelper bookDB=new ProgramHelper(this);
    Intent recycle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_books);
        bookname=(EditText) findViewById(R.id.box1);
        authorname=(EditText) findViewById(R.id.box2);
        desc=(EditText) findViewById(R.id.dsc);
        link=(EditText) findViewById(R.id.link);
        numofpages=(EditText) findViewById(R.id.num);
        pubyear=(EditText) findViewById(R.id.year);
        cover=(EditText) findViewById(R.id.covlink);
        add=(Button) findViewById(R.id.Add);
        view=(Button) findViewById(R.id.view);
        recycle=new Intent(ExploreBooks.this,recyclerviewBook.class);
        Intent intent = getIntent();
        String user=intent.getExtras().getString("user");

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recycle.putExtra("user", user);
                startActivity(recycle);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtbookname = bookname.getText().toString();
                String txtauthorname = authorname.getText().toString();
                String txtdesc = desc.getText().toString();
                String txtlink = link.getText().toString();
                String txtnumofpages = numofpages.getText().toString();
                String txtpubyear = pubyear.getText().toString();
                String txtcover = cover.getText().toString();
                boolean checkdata=bookDB.addBook(txtbookname,txtauthorname,txtnumofpages,txtpubyear,txtdesc,txtlink,txtcover);
                if(checkdata==true){
                    Toast.makeText(getApplicationContext(),"Book Added Successfully", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}