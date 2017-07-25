package com.amazon.aka.lssh.flickrdemo.utils;

import android.util.Log;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by lssh on 7/24/17.
 */

public class HttpLogger implements HttpLoggingInterceptor.Logger {
    @Override
    public void log(String message) {
        Log.d("HttpLogInfo", message);
    }
}
