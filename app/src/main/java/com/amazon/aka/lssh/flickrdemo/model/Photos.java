package com.amazon.aka.lssh.flickrdemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lssh on 7/22/17.
 */

public class Photos {
    @SerializedName("photo")
    List<Photo> mPhotoList;

    public List<Photo> getPhotoList() {
        return mPhotoList;
    }

    public void setPhotoList(List<Photo> mPhotoList) {
        this.mPhotoList = mPhotoList;
    }
}
