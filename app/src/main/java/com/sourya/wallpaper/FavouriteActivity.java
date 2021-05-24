package com.sourya.wallpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sourya.wallpaper.adapter.FavouriteAdapter;
import com.sourya.wallpaper.model.WallpaperModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FavouriteActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    List<WallpaperModel> bookmarkList;
    Gson gson;

    FavouriteAdapter adapter;

    public static final String BOOKMARK_PREF = "bookmarkPrefs";
    public static final String BOOKMARK_LIST = "bookmarkList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        init();

        getImages();


        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new FavouriteAdapter(bookmarkList, this);

        recyclerView.setAdapter(adapter);

        onDateHandle();

    }

    private void onDateHandle(){

        adapter.OnImageRemoved(new FavouriteAdapter.OnImageRemoved() {
            @Override
            public void onImageRemoved(int position) {
                bookmarkList.remove(position);

                adapter.notifyDataSetChanged();
            }
        });

    }

    private void storeImage() {

        String json = gson.toJson(bookmarkList);

        editor = preferences.edit();
        editor.putString(BOOKMARK_LIST, json);
        editor.apply();
    }

    @Override
    protected void onPause() {
        super.onPause();
        storeImage();
    }

    private void init() {

        recyclerView = findViewById(R.id.recyclerView);

        preferences = getSharedPreferences(BOOKMARK_PREF, MODE_PRIVATE);

        gson = new Gson();

    }

    private void getImages() {
        String json = preferences.getString(BOOKMARK_LIST, "");

        Type type = new TypeToken<List<WallpaperModel>>() {
        }.getType();

        bookmarkList = gson.fromJson(json, type);
        if (bookmarkList == null) {
            bookmarkList = new ArrayList<>();
        }

    }

}