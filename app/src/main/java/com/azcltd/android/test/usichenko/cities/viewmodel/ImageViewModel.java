package com.azcltd.android.test.usichenko.cities.viewmodel;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.azcltd.android.test.usichenko.cities.R;
import com.squareup.picasso.Picasso;

import static com.azcltd.android.test.usichenko.cities.service.repo.AzoftService.HTTP_AZOFT_URL;

public class ImageViewModel {

    private String mImageUrl;

    public ImageViewModel(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getImageUrl() {
        return HTTP_AZOFT_URL.concat(mImageUrl);
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_placeholder)
                .into(view);
    }
}
