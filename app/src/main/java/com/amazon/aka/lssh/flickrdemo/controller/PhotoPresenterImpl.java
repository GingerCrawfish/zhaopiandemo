package com.amazon.aka.lssh.flickrdemo.controller;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.amazon.aka.lssh.flickrdemo.model.PhotoContainer;
import com.amazon.aka.lssh.flickrdemo.dataservice.ApiService;
import com.amazon.aka.lssh.flickrdemo.view.MainActivity;
import com.amazon.aka.lssh.flickrdemo.view.MainView;
import com.amazon.aka.lssh.flickrdemo.view.ShowActivity;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lssh on 7/22/17.
 */

public class PhotoPresenterImpl implements PhotoPresenter {
    private MainActivity mActivity;

    public PhotoPresenterImpl(MainActivity mainActivity) {
        this.mActivity = mainActivity;
    }

    @Override
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
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(PhotoContainer photoContainer) {
                        if (photoContainer != null && photoContainer.getPhotos() != null) {
                            mActivity.setPhotos(photoContainer.getPhotos().getPhotoList());
                        }
                    }
                });

    }

    @Override
    public void showImageInShowActivity(String url) {
        Intent intent = new Intent(mActivity, ShowActivity.class);
        intent.putExtra("url", url);
        mActivity.startActivity(intent);
    }
}
