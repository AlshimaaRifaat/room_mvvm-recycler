package com.example.room_sample;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;



import java.util.List;

public class PostViewModal extends AndroidViewModel {
    private Repository repository;
    public LiveData<List<Posts>> getAllPosts;

    public PostViewModal(@NonNull Application application) {
        super(application);
        repository=new Repository(application);
        getAllPosts=repository.getAllPosts();
    }

    public void insert(List<Posts> posts){
        repository.insert(posts);
    }

    public LiveData<List<Posts>> getAllPosts()
    {
        return getAllPosts;
    }
}
