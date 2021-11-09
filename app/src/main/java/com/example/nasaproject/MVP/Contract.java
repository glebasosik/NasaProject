package com.example.nasaproject.MVP;

import com.example.nasaproject.API.DateDTO;
import com.example.nasaproject.API.PhotoDTO;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface Contract {

    interface MvpView {
        void showText(List<DateDTO> dateDTOS);

        void showError();
    }

    interface MvpViewListPhoto {
        void showText(List<PhotoDTO> photoDTOS);

        void showError();
    }

    interface Presenter {
        void Subscribe();

        void unSubscribe();
    }

    interface Repository {
        Single<List<DateDTO>> loadDates();

        Single<List<PhotoDTO>> loadListPhotos(String date);
    }
}
