package com.example.nasaproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nasaproject.API.PhotoDTO;
import com.example.nasaproject.Adapters.AdapterPhotoList;
import com.example.nasaproject.MVP.Contract;
import com.example.nasaproject.MVP.Presenters.PresenterListPhoto;

import java.util.List;

public class PhotoListActivity extends AppCompatActivity implements Contract.MvpViewListPhoto {

    private static final String EXTRA_DATE = "PhotoListActivity.EXTRA_DATE";

    RecyclerView recyclerView;
    AdapterPhotoList adapterPhotoList;
    PresenterListPhoto presenterListPhoto;

    public static void start(Context caller, String date) {
        Intent intent = new Intent(caller, PhotoListActivity.class);
        intent.putExtra(EXTRA_DATE, date);
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(R.string.choose_time);

        recyclerView = findViewById(R.id.recyclerview);
        adapterPhotoList = new AdapterPhotoList();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterPhotoList);

        presenterListPhoto = new PresenterListPhoto(this, getIntent().getStringExtra(EXTRA_DATE));
        presenterListPhoto.Subscribe();

    }

    @Override
    public void showText(List<PhotoDTO> photoDTOS) {
        adapterPhotoList.SetData(photoDTOS);
    }

    @Override
    public void showError() {
        Toast.makeText(PhotoListActivity.this, R.string.error, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterListPhoto.unSubscribe();
    }
}