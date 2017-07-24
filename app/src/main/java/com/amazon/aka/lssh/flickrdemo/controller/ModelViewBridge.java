package com.amazon.aka.lssh.flickrdemo.controller;

import com.amazon.aka.lssh.flickrdemo.model.Photo;
import com.amazon.aka.lssh.flickrdemo.model.PhotoContainer;
import com.amazon.aka.lssh.flickrdemo.service.ApiService;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lssh on 7/22/17.
 */

public class ModelViewBridge {
    private ModelViewInteraction mInteraction;

    public ModelViewBridge(ModelViewInteraction interaction) {
        this.mInteraction = interaction;
    }

    public void searchImage(String keywords) {
        ApiService.getInstance().getFlickrService().searchPhotos(keywords)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PhotoContainer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO no-im
                    }

                    @Override
                    public void onNext(PhotoContainer photoContainer) {
                        if (photoContainer != null && photoContainer.getPhotos() != null) {
                            mInteraction.setPhotos(photoContainer.getPhotos().getPhotoList());
                        }
                    }
                });

    }

    public interface  ModelViewInteraction {
        void setPhotos(List<Photo> photoList);
    }
}
