package com.example.nasaproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.piasy.biv.BigImageViewer;
import com.github.piasy.biv.loader.glide.GlideImageLoader;
import com.github.piasy.biv.view.BigImageView;


public class PhotoActivity extends AppCompatActivity {

    private static final String EXTRA_URL = "PhotoActivity.EXTRA_URL";

    public static void start(Context caller, String url) {
        Intent intent = new Intent(caller, PhotoActivity.class);
        intent.putExtra(EXTRA_URL, url);
        caller.startActivity(intent);
        BigImageViewer.initialize(GlideImageLoader.with(caller.getApplicationContext()));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        BigImageView bigImageView = findViewById(R.id.mBigImage);
        bigImageView.showImage(Uri.parse(getIntent().getStringExtra(EXTRA_URL)));

    }
}