package com.example.nasaproject.DI;

import com.example.nasaproject.MVP.Repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Singleton
    @Provides
    public Repository repository() {
        return new Repository();
    }
}
