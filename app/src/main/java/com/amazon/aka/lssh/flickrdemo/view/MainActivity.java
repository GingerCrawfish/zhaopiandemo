package com.amazon.aka.lssh.flickrdemo.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.amazon.aka.lssh.flickrdemo.R;
import com.amazon.aka.lssh.flickrdemo.controller.ModelViewBridge;
import com.amazon.aka.lssh.flickrdemo.model.Photo;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ModelViewBridge.ModelViewInteraction {

    private EditText editText;
    private Button button;
    private RecyclerView recyclerView;

    private PhotoAdapter photoAdapter;
    private ModelViewBridge modelViewBridge;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.modelViewBridge = new ModelViewBridge(this);

        editText = findViewById(R.id.search_box);
        button = findViewById(R.id.search_button);
        recyclerView = this.findViewById(R.id.recycler_view);

        photoAdapter = new PhotoAdapter(this);
        recyclerView.setAdapter(photoAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
                modelViewBridge.searchImage(editText.getText().toString());
            }
        });

    }

    @Override
    public void setPhotos(List<Photo> photoList) {
        if (photoAdapter != null) {
            photoAdapter.setPhotos(photoList);
        }
    }

    private void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
