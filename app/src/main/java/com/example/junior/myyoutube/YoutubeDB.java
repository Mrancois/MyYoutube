package com.example.junior.myyoutube;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Junior on 17/07/2016.
 */
public class YoutubeDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "YoutubeDB";
    public static final String TABLE_NAME = "favorites";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE = "imageurl";
    public static final String VIDEO = "videourl";


    public YoutubeDB(Context context) {
        super(context, "YoutubeDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(" + ID + " text primary key, " + NAME + " text, " + DESCRIPTION + " text, " + IMAGE + " text, " + VIDEO + " text)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public void insererFav(Video video){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID, video.getId());
        contentValues.put(NAME, video.getName());
        contentValues.put(DESCRIPTION, video.getDescription());
        contentValues.put(IMAGE, video.getImageUrl());
        contentValues.put(VIDEO, video.getVideoUrl());
        db.insert(TABLE_NAME, null, contentValues);
    }

    public boolean deleteFav(Video video){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, ID + "="+video.getId(),null)>0;
    }

    public boolean isFav(Video video){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from " + TABLE_NAME + " where id = '" + video.getId() + "'", null);
        int num = res.getCount();
        res.close();
        return num > 0;
    }

    public ArrayList<Video> getFav(){
        ArrayList<Video> lesVideos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from " + TABLE_NAME, null);
        res.moveToFirst();

        while(!res.isAfterLast()){

            Video video = new Video(res.getString(res.getColumnIndex(ID)),res.getString(res.getColumnIndex(NAME)),res.getString(res.getColumnIndex(DESCRIPTION)),res.getString(res.getColumnIndex(IMAGE)),res.getString(res.getColumnIndex(VIDEO)));
            lesVideos.add(video);
            res.moveToNext();
        }
        res.close();
        return lesVideos;
    }

}
