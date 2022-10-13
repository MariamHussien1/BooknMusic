package com.example.booksnmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class BookLayout extends AppCompatActivity {
    TextView Title,descr,pagen,year,auth,link;
    ImageView image;
    Handler mainHandler;
    ProgressDialog progressDialog;
    Button save;
    ProgramHelper saveBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_layout);

        image=(ImageView) findViewById(R.id.bookCover);
        auth=(TextView) findViewById(R.id.Author);
        year=(TextView) findViewById(R.id.publish);
        pagen=(TextView) findViewById(R.id.Pagenum);
        descr=(TextView) findViewById(R.id.descreption1);
        Title=(TextView) findViewById(R.id.BookTitle);
        link=(TextView) findViewById(R.id.booklink);
        save=(Button) findViewById(R.id.button3);
        mainHandler = new Handler();
        saveBook= new ProgramHelper(this);

        Intent intent = getIntent();
        String user=intent.getExtras().getString("user");
        String bookn=intent.getExtras().getString("BookTitle");
        String bookdesc=intent.getExtras().getString("descrep");
        String bookpage=intent.getExtras().getString("Pagenum");
        String bookpub=intent.getExtras().getString("publish");
        String bookauth=intent.getExtras().getString("Author");
        String booklink=intent.getExtras().getString("link");

        String url=intent.getExtras().getString("cover");
        Title.setText(bookn);
        descr.setText(bookdesc);
        pagen.setText(bookpage);
        year.setText(bookpub);
        auth.setText(bookauth);
        link.setText(booklink);

        new FetchImage(url).start();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = saveBook.addSavedBook(user, bookn, bookdesc, bookpage, bookpub, bookauth, booklink, url);
                if (check == true) {
                    Toast.makeText(getApplicationContext(), "Book Saved Successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    class FetchImage extends Thread {

        String URL;
        Bitmap bitmap;

        FetchImage(String URL){
            this.URL = URL;
        }
        @Override
        public void run(){
            mainHandler.post(new Runnable() {
                @Override
                public void run() {

                    progressDialog = new ProgressDialog(BookLayout.this);
                    progressDialog.setMessage("Loading....");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                }
            });

            InputStream inputStream;
            try {
                inputStream = new URL(URL).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                        image.setImageBitmap(bitmap);
                    }
                }
            });
        }
    }
}