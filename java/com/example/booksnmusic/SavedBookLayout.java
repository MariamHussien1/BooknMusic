package com.example.booksnmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class SavedBookLayout extends AppCompatActivity {
    TextView Title,descr,pagen,year,auth,link;
    ImageView image;
    Handler mainHandler;
    ProgressDialog progressDialog;
    ProgramHelper saveBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_book_layout);

        image=(ImageView) findViewById(R.id.SbookCover);
        auth=(TextView) findViewById(R.id.SAuthor);
        year=(TextView) findViewById(R.id.Spublish);
        pagen=(TextView) findViewById(R.id.SPagenum);
        descr=(TextView) findViewById(R.id.Sdescreption1);
        Title=(TextView) findViewById(R.id.SBookTitle);
        link=(TextView) findViewById(R.id.Sbooklink);
        mainHandler = new Handler();
        saveBook= new ProgramHelper(this);

        Intent intent = getIntent();
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

                    progressDialog = new ProgressDialog(SavedBookLayout.this);
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