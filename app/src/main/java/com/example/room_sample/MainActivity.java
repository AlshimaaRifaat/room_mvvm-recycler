package com.example.room_sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private PostViewModal postViewModal;

    private List<Posts> getPostsList;
    private PostAdapter postAdapter;
    private RecyclerView recyclerView;
    private Repository repository;
    private static  final String DATA_URL="http://www.codingwithjks.tech/data.php/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        repository=new Repository(getApplication());
        getPostsList=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        postViewModal=new ViewModelProvider(this).get(PostViewModal.class);

        postAdapter=new PostAdapter(this,getPostsList);
        makeRequest();
        postViewModal.getAllPosts().observe(this, new Observer<List<Posts>>() {
            @Override
            public void onChanged(List<Posts> posts) {
                recyclerView.setAdapter(postAdapter);
                postAdapter.getAllDatas(posts);

                Log.d("main", "onChanged: "+posts);
            }
        });


    }


    public void makeRequest() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(DATA_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api=retrofit.create(Api.class);
        Call<List<Posts>> call=api.getPosts();
        call.enqueue(new retrofit2.Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if(response.isSuccessful()) {
                    repository.insert(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                Log.d("main", "onFailure: "+t.getMessage());
            }
        });

    }


}