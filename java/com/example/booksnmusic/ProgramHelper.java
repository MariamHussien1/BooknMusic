package com.example.booksnmusic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ProgramHelper extends SQLiteOpenHelper {
    final static String dbName="Books_Songs5";
    SQLiteDatabase Books_Songs5;

    public ProgramHelper(Context context) {
        super(context, dbName, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase Base) {
        Base.execSQL("CREATE TABLE MYusers(ID INTEGER NOT NULL,username TEXT PRIMARY KEY NOT NULL," +
                "emails TEXT NOT NULL, passwords TEXT NOT NULL)");
        Base.execSQL("CREATE TABLE MYbooks(ID INTEGER NOT NULL PRIMARY KEY,bookname TEXT  NOT NULL," +
                "author TEXT NOT NULL, pages TEXT NOT NULL,pubyear TEXT NOT NULL,descrep TEXT NOT NULL,link TEXT,covlink TEXT)");
        Base.execSQL("CREATE TABLE Songs(ID INTEGER NOT NULL PRIMARY KEY,songname TEXT  NOT NULL," +
                "reldate TEXT NOT NULL, singer TEXT NOT NULL,duration TEXT NOT NULL,genre TEXT NOT NULL,links TEXT ,coverLink TEXT NOT NULL)");
        Base.execSQL("CREATE TABLE SavedSong(ID TEXT NOT NULL,songname TEXT , reldate TEXT , singer TEXT ,duration TEXT ,genre TEXT ,links TEXT ,coverLink TEXT)");
        Base.execSQL("CREATE TABLE SavedBook(ID TEXT NOT NULL ,bookname TEXT ,author TEXT , pages TEXT,pubyear TEXT ,descrep TEXT ,link TEXT,covlink TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase Base, int i, int i1) {
       Base.execSQL("DROP TABLE IF EXISTS MYusers");
        Base.execSQL("DROP TABLE IF EXISTS MYbooks");
        Base.execSQL("DROP TABLE IF EXISTS songs");
        Base.execSQL("DROP TABLE IF EXISTS Saved");
    }
    private int iduser=0,idbook=0,idsong=0;
    public boolean signUpUser(String uname, String umail, String passd)
    {  ContentValues rows=new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        iduser=iduser+1;
        boolean ret;
        rows.put("ID",iduser);
        rows.put("username",uname);
        rows.put("emails",umail);
        rows.put("passwords",passd);
        db.insert("MYusers",null,rows);
        long x=db.insert("MYusers",null,rows);
        if(x!=(-1)){
            ret= false;
        }else  ret= true;
        db.close();
        return ret;
    }
    public boolean LoginAuth(String username) {
        Books_Songs5 = this.getWritableDatabase();
        Cursor cursor = Books_Songs5.rawQuery("Select * from MYusers where username = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
    public boolean AuthPassnUser(String username, String password){

        Cursor cursor = Books_Songs5.rawQuery("Select * from MYusers where username = ? and passwords = ?", new String[] {username,password});
        if(cursor.getCount()>0) {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean addBook(String bName,String Author1,String Pages1,String pubYear1,String description1,String link1,String covlink)  {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        idbook=idbook+1;
        values.put("ID", idbook);
        values.put("bookname", bName);
        values.put("author", Author1);
        values.put("pages", Pages1);
        values.put("pubyear", pubYear1);
        values.put("descrep", description1);
        values.put("link", link1);
        values.put("covlink",covlink);
        long result = db.insert("MYbooks", null, values);
        db.close();
        if(result==-1){
            return false;
        }
        else {
            return true;
        }
    }
    public Cursor DisplayBooks(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * from MYbooks",null);
        if(cursor!=null) {cursor.moveToFirst();}
        db.close();
        return cursor;
    }

    public boolean addSong(String sngName,String singr,String durate,String genr,String reledate,String link1,String cover1)  {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        idsong=idsong+1;
        values.put("ID", idsong);
        values.put("songname", sngName);
        values.put("reldate", reledate);
        values.put("singer", singr);
        values.put("duration", durate);
        values.put("genre", genr);
        values.put("links", link1);
        values.put("coverLink", cover1);
        long result = db.insert("Songs", null, values);
        db.close();
        if(result==-1){
            return false;
        }
        else {
            return true;
        }
    }
    public Cursor Displaysongs(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * from Songs",null);
        if(cursor!=null) {cursor.moveToFirst();}
        db.close();
        return cursor;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean addSavedSong(String userid,String sngName,String singr,String durate,String genr,String reledate,String link1,String cover1)  {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        values.put("ID", userid);
        values.put("songname", sngName);
        values.put("reldate", reledate);
        values.put("singer", singr);
        values.put("duration", durate);
        values.put("genre", genr);
        values.put("links", link1);
        values.put("coverLink", cover1);
        long result = db.insert("SavedSong", null, values);
        db.close();
        if(result==-1){
            return false;
        }
        else {
            return true;
        }
    }
    public boolean addSavedBook(String userid,String bName,String Author1,String Pages1,String pubYear1,String description1,String link1,String covlink)  {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        values.put("ID", userid);
        values.put("bookname", bName);
        values.put("author", Author1);
        values.put("pages", Pages1);
        values.put("pubyear", pubYear1);
        values.put("descrep", description1);
        values.put("link", link1);
        values.put("covlink",covlink);
        long result = db.insert("SavedBook", null, values);
        db.close();
        if(result==-1){
            return false;
        }
        else {
            return true;
        }
    }
    public Cursor DisplaySavedsongs(String userid){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * from SavedSong where ID=?",new String[] {userid});
        if(cursor!=null) {cursor.moveToFirst();}
        db.close();
        return cursor;
    }

    public Cursor DisplaySavedbooks(String userid){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * from SavedBook where ID=?",new String[] {userid});
        if(cursor!=null) {cursor.moveToFirst();}
        db.close();
        return cursor;
    }
}
