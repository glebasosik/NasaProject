package com.example.nasaproject.API;


import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NasaApiService {

    @GET("natural/all")
    Single<List<DateDTO>> getDates();

    @GET("natural/{date}")
    Single<List<PhotoDTO>> getPhotoDTO(@Path("date") String date);
}
