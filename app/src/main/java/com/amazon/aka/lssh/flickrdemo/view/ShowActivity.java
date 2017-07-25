package com.amazon.aka.lssh.flickrdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.amazon.aka.lssh.flickrdemo.R;
import com.amazon.aka.lssh.flickrdemo.utils.PhotoUtils;
import com.github.chrisbanes.photoview.PhotoView;

/**
 * Created by lssh on 7/22/17.
 */

public class ShowActivity extends AppCompatActivity {
    private PhotoView photoView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        photoView = findViewById(R.id.photo_view);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        PhotoUtils.loadPhoto(photoView, url);
    }
}
