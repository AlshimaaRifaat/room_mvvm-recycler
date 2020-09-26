package com.example.room_sample;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "post",indices = @Index(value = {"id"},unique = true))
public class Posts {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "postId")
    private int postId;

    @SerializedName("id")
    @ColumnInfo(name = "id")
    private int id ;

    @SerializedName("name")
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("image")
    @ColumnInfo(name = "image")
    private String image;

    @SerializedName("age")
    @ColumnInfo(name = "age")
    private int age;

    public Posts(int id, String name, String image, int age) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.age = age;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Posts{" +
                "postId=" + postId +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", age=" + age +
                '}';
    }

    public Posts() {
    }
}
