package com.amazon.aka.lssh.flickrdemo.view;

import android.view.View;

import com.amazon.aka.lssh.flickrdemo.model.Photo;

import java.util.List;

/**
 * Created by lssh on 7/24/17.
 */

public interface MainView {
    void hideKeyboard(View view);

    void setPhotos(List<Photo> photoList);
}
