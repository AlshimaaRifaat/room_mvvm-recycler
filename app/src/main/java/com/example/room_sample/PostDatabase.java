package com.example.room_sample;

import android.content.Context;
import android.os.AsyncTask;


import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;




@Database(entities = {Posts.class},version = 5)
public abstract class PostDatabase extends RoomDatabase {

    private static final String DATABASE_NAME="Post";
    public abstract PostDao postDao();
    public static volatile PostDatabase INSTANCE=null;
    public static PostDatabase getInstance(Context context){
        if(INSTANCE == null){
            synchronized(PostDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, PostDatabase.class, DATABASE_NAME)
                            .addCallback(callback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }

        }
        return INSTANCE;
    }
    public static Callback callback=new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsyn(INSTANCE);

        }
    };

    static  class  PopulateDbAsyn extends AsyncTask<Void,Void,Void>{
        private PostDao postDao;
        public PopulateDbAsyn(PostDatabase postDatabase)
        {
            postDao=postDatabase.postDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            postDao.deleteAll();

            return null;
        }
    }

}