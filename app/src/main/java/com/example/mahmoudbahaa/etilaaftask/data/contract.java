package com.example.mahmoudbahaa.etilaaftask.data;

/**
 * Created by MahmoudBahaa on 31/12/2018.
 */

public class contract {

    public contract() {

    }



    public static final class Stories{


        public final static String TABLE_NAME = "Stories";
        public final static String COLUMN_STORIES_ID= "ID"; //0
        public final static String COLUMN_STORIES_Title= "Title"; //1
        public final static String COLUMN_STORIES_Url="Url";  //2
        public final static String COLUMN_STORIES_UrlToImage = "UrlToImage"; //3
        public final static String COLUMN_STORIES_PublishedAt= "PublishedAt"; //4
        public final static String COLUMN_STORIES_Content= "Content"; //5

    }

    public static final class Photos{


        public final static String TABLE_NAME = "Photos";
        public final static String COLUMN_STORIES_ID= "ID";
        public final static String COLUMN_STORIES_Title= "Title"; //0
        public final static String COLUMN_STORIES_Url="Url";  //1
        public final static String COLUMN_STORIES_UrlToImage = "UrlToImage"; //2
        public final static String COLUMN_STORIES_PublishedAt= "PublishedAt"; //3
        public final static String COLUMN_STORIES_Content= "Content"; //4

    }


}
