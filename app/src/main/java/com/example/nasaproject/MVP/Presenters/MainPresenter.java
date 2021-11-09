package com.example.nasaproject.MVP.Presenters;

import com.example.nasaproject.API.DateDTO;
import com.example.nasaproject.DI.Component;
import com.example.nasaproject.DI.DaggerComponent;
import com.example.nasaproject.MVP.Contract;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainPresenter implements Contract.Presenter {

    Contract.MvpView mvpView;
    Contract.Repository repository;
    CompositeDisposable disposable = new CompositeDisposable();
    Component component = DaggerComponent.builder().build();

    public MainPresenter(Contract.MvpView mvpView) {
        this.mvpView = mvpView;
        this.repository = component.getRepository();
    }

    @Override
    public void Subscribe() {
        disposable.add(repository.loadDates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<DateDTO>>() {
                    @Override
                    public void onSuccess(@NonNull List<DateDTO> dateDTOS) {
                        mvpView.showText(dateDTOS);
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
