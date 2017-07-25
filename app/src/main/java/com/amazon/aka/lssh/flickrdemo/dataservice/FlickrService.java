package com.amazon.aka.lssh.flickrdemo.dataservice;

import com.amazon.aka.lssh.flickrdemo.model.PhotoContainer;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lssh on 7/22/17.
 */

public interface FlickrService {
    @GET("services/rest?method=flickr.photos.search")
    Observable<PhotoContainer> searchPhotos(@Query("text") String query);
}
