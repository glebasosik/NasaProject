package com.example.nasaproject.MVP.Presenters;

import com.example.nasaproject.API.PhotoDTO;
import com.example.nasaproject.DI.Component;
import com.example.nasaproject.DI.DaggerComponent;
import com.example.nasaproject.MVP.Contract;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PresenterListPhoto implements Contract.Presenter {

    Contract.MvpViewListPhoto mvpView;
    Contract.Repository repository;
    CompositeDisposable disposable = new CompositeDisposable();
    Component component = DaggerComponent.builder().build();

    String date;

    public PresenterListPhoto(Contract.MvpViewListPhoto mvpView, String date) {
        this.mvpView = mvpView;
        this.repository = component.getRepository();
        this.date = date;
    }

    @Override
    public void Subscribe() {
        disposable.add(repository.loadListPhotos(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<PhotoDTO>>() {
                    @Override
                    public void onSuccess(@NonNull List<PhotoDTO> photoDTOS) {
                        mvpView.showText(photoDTOS);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mvpView.showError();
                    }
                }));

    }

    @Override
    public void unSubscribe() {
        disposable.dispose();
    }

}
