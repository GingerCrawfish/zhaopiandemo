package com.amazon.aka.lssh.flickrdemo.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.amazon.aka.lssh.flickrdemo.presenter.PhotoPresenterImpl;
import com.amazon.aka.lssh.flickrdemo.utils.IntentUtils;
import com.amazon.aka.lssh.flickrdemo.utils.PhotoUtils;
import com.amazon.aka.lssh.flickrdemo.R;
import com.amazon.aka.lssh.flickrdemo.model.Photo;

import java.util.List;

/**
 * Created by lssh on 7/22/17.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private List<Photo> mPhotoList;

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageView imageView = (ImageView) LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent,false);
        return new PhotoViewHolder(imageView);
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

            final String url = PhotoUtils.getPhotoUrl(mPhotoList.get(position));
            PhotoUtils.loadPhoto((ImageView) holder.itemView, url);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.getContext().startActivity(IntentUtils.getShowViewIntent(view.getContext(), url));
                }
            });
        }
    }

    public void setPhotos(List<Photo> photos) {
        this.mPhotoList = photos;
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {

        public PhotoViewHolder(final ImageView imageView) {
            super(imageView);
        }
    }
}
