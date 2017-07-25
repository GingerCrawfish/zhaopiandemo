package com.amazon.aka.lssh.flickrdemo.utils;

import android.widget.ImageView;

import com.amazon.aka.lssh.flickrdemo.model.Photo;
import com.bumptech.glide.Glide;

/**
 * Created by lssh on 7/24/17.
 */

public class PhotoUtils {
    static final String flickrUrl = "https://farm%1$s.staticflickr.com/%2$s/%3$s_%4$s.jpg";

    public static String getPhotoUrl(Photo photo) {
        if (photo != null) {
            return String.format(flickrUrl, photo.getFarm(), photo.getServer(), photo.getId(), photo.getSecret());
        }

        return null;
    }

    public static void loadPhoto(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).centerCrop().crossFade().into(imageView);
    }

}
