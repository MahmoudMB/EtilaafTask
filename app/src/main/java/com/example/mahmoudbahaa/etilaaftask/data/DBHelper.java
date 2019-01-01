package com.example.mahmoudbahaa.etilaaftask.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.mahmoudbahaa.etilaaftask.models.Story;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by MahmoudBahaa on 31/12/2018.
 */

public class DBHelper  extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "data.db";

    private Context context;
    private static final int DATABASE_VERSION = 2;

    SQLiteDatabase sql;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String SQL_CREATE_STORIES_TABLE =  "CREATE TABLE " + contract.Stories.TABLE_NAME + " ("
                + contract.Stories.COLUMN_STORIES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + contract.Stories.COLUMN_STORIES_Title + " TEXT, "
                + contract.Stories.COLUMN_STORIES_Url + " TEXT, "
                + contract.Stories.COLUMN_STORIES_UrlToImage + " TEXT, "
                + contract.Stories.COLUMN_STORIES_PublishedAt + " TEXT, "
                + contract.Stories.COLUMN_STORIES_Content + " TEXT);";


        String SQL_CREATE_PHOTOS_TABLE =  "CREATE TABLE " + contract.Photos.TABLE_NAME + " ("
                + contract.Photos.COLUMN_STORIES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + contract.Photos.COLUMN_STORIES_Title + " TEXT, "
                + contract.Photos.COLUMN_STORIES_Url + " TEXT, "
                + contract.Photos.COLUMN_STORIES_UrlToImage + " TEXT, "
                + contract.Photos.COLUMN_STORIES_PublishedAt + " TEXT, "
                + contract.Photos.COLUMN_STORIES_Content + " TEXT);";




        db.execSQL(SQL_CREATE_STORIES_TABLE);

        db.execSQL(SQL_CREATE_PHOTOS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public ArrayList<Story> getStories(){
        ArrayList<Story> stories = new  ArrayList<>();
        sql = getReadableDatabase();
        String query = "select * from " + contract.Stories.TABLE_NAME;

        Cursor cursor = sql.rawQuery(query,null);

        if (cursor!=null) {
            cursor.moveToFirst();

Log.v("cursorccc",cursor.getCount()+"");
            while (!cursor.isAfterLast())
            {


                Story story = new Story();

                story.setId(cursor.getInt(0));
                story.setTitle(cursor.getString(1));

story.setUrl(cursor.getString(2));
story.setUrlToImage(cursor.getString(3));
story.setContent(cursor.getString(5));



                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

                format.setTimeZone(TimeZone.getTimeZone("UTC"));
                try {


                    Date date = format.parse(cursor.getString(4));
                    Log.v("date1",cursor.getString(4));
                    story.setPublishedAt(date);


                } catch (ParseException e) {
                    e.printStackTrace();
                }

                stories.add(story);
                cursor.moveToNext();
            }



        }

        sql.close();
        return stories;

    }

    public ArrayList<Story> getPhotos(){
        ArrayList<Story> stories = new  ArrayList<>();
        sql = getReadableDatabase();
        String query = "select * from " + contract.Photos.TABLE_NAME;

        Cursor cursor = sql.rawQuery(query,null);

        if (cursor!=null) {
            cursor.moveToFirst();


            while (!cursor.isAfterLast())
            {



                Story story = new Story();

                story.setId(cursor.getInt(0));
                story.setTitle(cursor.getString(1));
                story.setUrl(cursor.getString(2));
                story.setUrlToImage(cursor.getString(3));
                story.setContent(cursor.getString(5));



                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

                format.setTimeZone(TimeZone.getTimeZone("UTC"));
                try {


                    Date date = format.parse(cursor.getString(4));
                    story.setPublishedAt(date);


                } catch (ParseException e) {
                    e.printStackTrace();
                }

                stories.add(story);
                cursor.moveToNext();
            }



        }

        sql.close();
        return stories;

    }


    public void likeStory(Story story)
    {

        sql = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(contract.Stories.COLUMN_STORIES_Title,story.getTitle());
        values.put(contract.Stories.COLUMN_STORIES_Url,story.getUrl());
        values.put(contract.Stories.COLUMN_STORIES_UrlToImage,story.getUrlToImage());
        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        values.put(contract.Stories.COLUMN_STORIES_PublishedAt,outputFormat.format(story.getPublishedAt()));


        values.put(contract.Stories.COLUMN_STORIES_Content,story.getContent());



        sql.insert(contract.Stories.TABLE_NAME,null,values);



    }

    public void likePhoto(Story story)
    {

        sql = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(contract.Photos.COLUMN_STORIES_Title,story.getTitle());
        values.put(contract.Photos.COLUMN_STORIES_Url,story.getUrl());
        values.put(contract.Photos.COLUMN_STORIES_UrlToImage,story.getUrlToImage());
        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        values.put(contract.Photos.COLUMN_STORIES_PublishedAt,outputFormat.format(story.getPublishedAt()));


        values.put(contract.Photos.COLUMN_STORIES_Content,story.getContent());







        sql.insert(contract.Photos.TABLE_NAME,null,values);



    }



    public void unLikeStory(Story story){
        sql = getWritableDatabase();


        String[] args = {story.getTitle()};


        String query = "delete from "  + contract.Stories.TABLE_NAME + " where "+ contract.Stories.COLUMN_STORIES_Title + "= ?";
            sql.execSQL( query ,args);


        sql.close();
    }


    public void unLikePhoto(Story story){
        sql = getWritableDatabase();


        String[] args = {story.getTitle()};


        String query = "delete from "  + contract.Photos.TABLE_NAME + " where "+ contract.Stories.COLUMN_STORIES_Title + "= ?";
        sql.execSQL( query ,args);


        sql.close();

    }


    public boolean CheckForStory(Story story)

    {
        boolean r = false;
        sql = getReadableDatabase();

        String query = "select * from " + contract.Stories.TABLE_NAME + " where " + contract.Stories.COLUMN_STORIES_Title + " = "  + '"'+story.getTitle()+'"';

        Cursor cursor = sql.rawQuery(query,null);

        Log.v("cursoncount",cursor.getCount()+"");

        if (cursor.getCount()!=0) {
            cursor.moveToFirst();

            sql.close();
            return true;
        }


        sql.close();
        return false;

    }



    public boolean CheckForPhoto(Story story)

    {
        boolean r = false;
        sql = getReadableDatabase();

        String query = "select * from " + contract.Photos.TABLE_NAME + " where " + contract.Photos.COLUMN_STORIES_Title + " = "  + '"'+story.getTitle()+'"';

        Cursor cursor = sql.rawQuery(query,null);



        if (cursor.getCount()!=0) {
            cursor.moveToFirst();

            sql.close();
            return true;
        }


        sql.close();
        return false;

    }






}
