package com.amazon.aka.lssh.flickrdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.amazon.aka.lssh.flickrdemo.R;
import com.bumptech.glide.Glide;

/**
 * Created by lssh on 7/22/17.
 */

public class ShowActivity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        imageView = findViewById(R.id.detail_image);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        Glide.with(imageView.getContext()).load(url).centerCrop().crossFade().into(imageView);
    }
}
