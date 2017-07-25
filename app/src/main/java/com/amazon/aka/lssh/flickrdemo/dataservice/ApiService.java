package com.amazon.aka.lssh.flickrdemo.dataservice;

import com.amazon.aka.lssh.flickrdemo.utils.HttpLogger;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lssh on 7/22/17.
 */

public class ApiService {
    private static ApiService sApiService = null;

    private FlickrService mFlickrService;
    private Retrofit retrofit;

    private ApiService() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(mInterceptor);
        OkHttpClient client = builder.addNetworkInterceptor(logInterceptor).build();

        RxJavaCallAdapterFactory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();



        retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.flickr.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .client(client)
                    .build();
    }

    public static ApiService getInstance() {
        if (sApiService == null) {
            sApiService = new ApiService();
        }

        return sApiService;
    }

    public FlickrService getFlickrService() {
        if (mFlickrService == null) {
            mFlickrService = retrofit.create(FlickrService.class);
        }

        return mFlickrService;
    }

    private final Interceptor mInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            HttpUrl url = request.url().newBuilder()
                    .addQueryParameter("api_key", "276b1fd955f462bb4faa7ccfa28f13e9")
                    .addQueryParameter("format", "json")
                    .addQueryParameter("nojsoncallback", "1")
                    .build();

            request = request.newBuilder().url(url).build();

            return chain.proceed(request);
        }
    };
}
