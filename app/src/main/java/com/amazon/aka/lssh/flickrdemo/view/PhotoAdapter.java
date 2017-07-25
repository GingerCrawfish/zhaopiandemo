package com.amazon.aka.lssh.flickrdemo.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.amazon.aka.lssh.flickrdemo.controller.PhotoPresenter;
import com.amazon.aka.lssh.flickrdemo.controller.PhotoPresenterImpl;
import com.amazon.aka.lssh.flickrdemo.utils.PhotoUtils;
import com.amazon.aka.lssh.flickrdemo.R;
import com.amazon.aka.lssh.flickrdemo.model.Photo;

import java.util.List;

/**
 * Created by lssh on 7/22/17.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private List<Photo> mPhotoList;

    private PhotoPresenterImpl mPhotoPresenterImpl;

    public PhotoAdapter(PhotoPresenterImpl photoPresenterImpl) {
        this.mPhotoPresenterImpl = photoPresenterImpl;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageView image = (ImageView) LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent,false);
        PhotoViewHolder viewHolder = new PhotoViewHolder(image);

        return viewHolder;
    }

    @Override
    public int getItemCount() {
        if (mPhotoList == null) {
            return 0;
        }

        return mPhotoList.size();
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        if (mPhotoList != null || position < mPhotoList.size()) {
            ImageView imageView = holder.mImageView;

            final String url = PhotoUtils.getPhotoUrl(mPhotoList.get(position));
            PhotoUtils.loadPhoto(imageView, url);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPhotoPresenterImpl.showImageInShowActivity(url);
                }
            });
        }
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        public PhotoViewHolder(final ImageView imageView) {
            super(imageView);
            mImageView = imageView;
        }

    }

    public void setPhotos(List<Photo> photos) {
        this.mPhotoList = photos;
        notifyDataSetChanged();
    }
}
