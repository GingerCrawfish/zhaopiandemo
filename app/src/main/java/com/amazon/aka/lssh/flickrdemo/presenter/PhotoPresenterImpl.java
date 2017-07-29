package com.amazon.aka.lssh.flickrdemo.presenter;

import com.amazon.aka.lssh.flickrdemo.model.PhotoContainer;
import com.amazon.aka.lssh.flickrdemo.dataservice.ApiService;
import com.amazon.aka.lssh.flickrdemo.view.MainView;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lssh on 7/22/17.
 */

public class PhotoPresenterImpl implements PhotoPresenter {
    private MainView mView;

    public PhotoPresenterImpl(MainView mainView) {
        this.mView = mainView;
    }

    @Override
    public void searchImage(String keywords) {
        ApiService.getInstance().getFlickrService().searchPhotos(keywords)
                .subscribe(new Observer<PhotoContainer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(PhotoContainer photoContainer) {
                        if (photoContainer != null && photoContainer.getPhotos() != null) {
                            mView.setPhotos(photoContainer.getPhotos().getPhotoList());
                        }
                    }
                });

    }
}
