package com.amazon.aka.lssh.flickrdemo.controller;

/**
 * Created by lssh on 7/24/17.
 */

public interface PhotoPresenter {
    void searchImage(String keywords);

    void showImageInShowActivity(String url);
}