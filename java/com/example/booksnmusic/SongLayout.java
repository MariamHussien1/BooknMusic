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

public class SongLayout extends AppCompatActivity {
    TextView sTitle,duration,gerne,relyear,singer,slink;
    ImageView simage;
    Handler mainHandler;
    ProgressDialog progressDialog;
    Button save;
    ProgramHelper saveSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_layout);
        simage=(ImageView) findViewById(R.id.songCover);
        singer=(TextView) findViewById(R.id.Singer);
        relyear=(TextView) findViewById(R.id.releasee);
        gerne=(TextView) findViewById(R.id.Gerne);
        duration=(TextView) findViewById(R.id.duration);
        sTitle=(TextView) findViewById(R.id.SongTitle);
        slink=(TextView) findViewById(R.id.songlinkk);
        save=(Button) findViewById(R.id.button33);
        mainHandler = new Handler();
        saveSong= new ProgramHelper(this);
        Intent intent = getIntent();
        String user=intent.getExtras().getString("user");
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
        new SongLayout.FetchImage(url).start();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check =saveSong.addSavedSong(user,songn,ssinger,songdur, gernee,songrel,songlink,url);
                if(check==true){
                    Toast.makeText(getApplicationContext(),"Song Saved Successfully", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Failed", Toast.LENGTH_SHORT).show();
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

                    progressDialog = new ProgressDialog(SongLayout.this);
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