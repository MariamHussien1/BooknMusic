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

public class SavedSongLayout extends AppCompatActivity {
    TextView sTitle,duration,gerne,relyear,singer,slink;
    ImageView simage;
    Handler mainHandler;
    ProgressDialog progressDialog;
    ProgramHelper saveSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_song_layout);

        simage=(ImageView) findViewById(R.id.SsongCover);
        singer=(TextView) findViewById(R.id.SSinger);
        relyear=(TextView) findViewById(R.id.Sreleasee);
        gerne=(TextView) findViewById(R.id.SGerne);
        duration=(TextView) findViewById(R.id.Sduration);
        sTitle=(TextView) findViewById(R.id.SSongTitle);
        slink=(TextView) findViewById(R.id.Ssonglinkk);
        mainHandler = new Handler();
        saveSong= new ProgramHelper(this);

        Intent intent = getIntent();
        String songn=intent.getExtras().getString("SongTitle");
        String songdur=intent.getExtras().getString("duration");
        String gernee=intent.getExtras().getString("gerne");
        String songrel=intent.getExtras().getString("release");
        String ssinger=intent.getExtras().getString("singer");
        String songlink=intent.getExtras().getString("songlink");
        String url=intent.getExtras().getString("songcover");

        sTitle.setText(songn);
        duration.setText(songdur);
        gerne.setText(gernee);
        relyear.setText(songrel);
        singer.setText(ssinger);
        slink.setText(songlink);
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

                    progressDialog = new ProgressDialog(SavedSongLayout.this);
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
                        simage.setImageBitmap(bitmap);
                    }
                }
            });
        }
    }
}