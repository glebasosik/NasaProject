package com.example.nasaproject.MVP;

import com.example.nasaproject.API.DateDTO;
import com.example.nasaproject.API.PhotoDTO;
import com.example.nasaproject.DI.Component;
import com.example.nasaproject.DI.DaggerComponent;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class Repository implements Contract.Repository {

    Component component = DaggerComponent.builder().build();

    @Override
    public Single<List<DateDTO>> loadDates() {
        return component.getNasaApiService().getDates();
    }

    @Override
    public Single<List<PhotoDTO>> loadListPhotos(String date) {
        return component.getNasaApiService().getPhotoDTO(date);
    }
}
