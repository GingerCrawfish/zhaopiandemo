package com.amazon.aka.lssh.flickrdemo.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.amazon.aka.lssh.flickrdemo.R;
import com.amazon.aka.lssh.flickrdemo.model.Photo;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by lssh on 7/22/17.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {
    static final String flickrUrl = "https://farm%1$s.staticflickr.com/%2$s/%3$s_%4$s.jpg";

    private List<Photo> photoList;

    private Activity mActivity;

    public PhotoAdapter(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageView image = (ImageView) LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent,false);
        PhotoViewHolder viewHolder = new PhotoViewHolder(image);

        return viewHolder;
    }

    @Override
    public int getItemCount() {
        if (photoList == null) {
            return 0;
        }

        return photoList.size();
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String url = (String) view.getTag();
            Log.i("tag", url);

            Intent intent = new Intent(mActivity, ShowActivity.class);
            intent.putExtra("url", url);
            mActivity.startActivity(intent);
        }
    };

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        if (photoList != null || position < photoList.size()) {
            ImageView imageView = holder.mImageView;
            final String url = getPhotoUrl(photoList.get(position));

            Glide.with(imageView.getContext()).load(url).centerCrop().crossFade().into(imageView);

            imageView.setTag(url);
            imageView.setOnClickListener(mClickListener);
        }
    }

    private String getPhotoUrl(Photo photo) {
        if (photo != null) {
            return String.format(flickrUrl, photo.getFarm(), photo.getServer(), photo.getId(), photo.getSecret());
        }

        return null;
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        public PhotoViewHolder(final ImageView imageView) {
            super(imageView);
            mImageView = imageView;
        }

    }

    public void setPhotos(List<Photo> photos) {
        this.photoList = photos;
        notifyDataSetChanged();
    }
}
