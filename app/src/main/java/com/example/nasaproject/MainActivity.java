package com.example.nasaproject;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nasaproject.API.DateDTO;
import com.example.nasaproject.Adapters.AdapterDates;
import com.example.nasaproject.MVP.Contract;
import com.example.nasaproject.MVP.Presenters.MainPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Contract.MvpView {

    RecyclerView recyclerView;
    AdapterDates adapterDates;
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(R.string.choose_date);

        recyclerView = findViewById(R.id.recyclerview);
        adapterDates = new AdapterDates();

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterDates);

        mainPresenter = new MainPresenter(this);
        mainPresenter.Subscribe();

    }

    @Override
    public void showText(List<DateDTO> dateDTOS) {
        adapterDates.setDates(dateDTOS);
    }

    @Override
    public void showError() {
        Toast.makeText(MainActivity.this, R.string.error, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.unSubscribe();
    }
}