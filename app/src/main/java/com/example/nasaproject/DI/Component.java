package com.example.nasaproject.DI;

import com.example.nasaproject.API.NasaApiService;
import com.example.nasaproject.MVP.Repository;

import javax.inject.Singleton;

@Singleton
@dagger.Component(modules = {RestApiModule.class, AppModule.class})
public interface Component {

    NasaApiService getNasaApiService();

    Repository getRepository();
}
