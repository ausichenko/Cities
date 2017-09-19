package com.azcltd.android.test.usichenko.cities.viewmodel;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.azcltd.android.test.usichenko.cities.R;
import com.squareup.picasso.Picasso;

import static com.azcltd.android.test.usichenko.cities.service.repo.AzoftService.HTTP_AZOFT_URL;

public class DetailsViewModel {

    private String mImageUrl;

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getImageUrl() {
        // The URL will usually come from a model (i.e Profile)
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
