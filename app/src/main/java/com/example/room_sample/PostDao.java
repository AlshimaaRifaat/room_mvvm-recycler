package com.example.room_sample;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;



import java.util.List;

@Dao
public interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Posts> posts);

    @Query("SELECT DISTINCT * FROM post")
    LiveData<List<Posts>>  getAllPosts();

    @Query("DELETE FROM post")
    void deleteAll();
}
