package com.amazon.aka.lssh.flickrdemo.dataservice;

import com.amazon.aka.lssh.flickrdemo.BuildConfig;
import com.amazon.aka.lssh.flickrdemo.utils.HttpLogger;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lssh on 7/22/17.
 */

public class ApiService {
    private volatile static ApiService sApiService;

    private FlickrService mFlickrService;
    private OkHttpClient mClient;
    private Retrofit mRetrofit;

    private ApiService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.flickr.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getHttpClient())
                .build();
    }

    public static ApiService getInstance() {
        if (sApiService == null) {
            synchronized (ApiService.class) {
                if (sApiService == null) {
                    sApiService = new ApiService();
                }
            }
        }
        return sApiService;
    }

    public FlickrService getFlickrService() {
        if (mFlickrService == null) {
            final FlickrService service = mRetrofit.create(FlickrService.class);
            final ServiceInvoker invoker = new ServiceInvoker(service);
            mFlickrService = (FlickrService) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{FlickrService.class}, invoker);
        }

        return mFlickrService;
    }

    private OkHttpClient getHttpClient() {
        if (mClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            builder.connectTimeout(30, TimeUnit.SECONDS);
            builder.readTimeout(30, TimeUnit.SECONDS);
            builder.writeTimeout(30, TimeUnit.SECONDS);

            builder.interceptors().add(mInterceptor);
            if (BuildConfig.DEBUG) {
                builder.addNetworkInterceptor(new HttpLoggingInterceptor(new HttpLogger()).setLevel(HttpLoggingInterceptor.Level.BODY));
            }

            mClient = builder.build();
        }
        return mClient;
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

    private static class ServiceInvoker implements InvocationHandler {

        private final FlickrService mService;

        private ServiceInvoker(FlickrService service) {
            mService = service;
        }

        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            Object result = method.invoke(mService, objects);
            if (result instanceof Observable) {
                Observable observable = (Observable) result;
                return observable
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
            return result;
        }
    }
}
