package com.azcltd.android.test.usichenko.cities.view.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.azcltd.android.test.usichenko.cities.R;
import com.azcltd.android.test.usichenko.cities.databinding.FragmentDetailsBinding;
import com.azcltd.android.test.usichenko.cities.service.models.City;
import com.squareup.picasso.Picasso;

public class DetailsFragment extends Fragment {

    private static final String KEY_CITY = "key_city";

    public static DetailsFragment newInstance(City city) {
        DetailsFragment fragment = new DetailsFragment();

        Bundle args = new Bundle();
        args.putParcelable(KEY_CITY, city);
        fragment.setArguments(args);

        return fragment;
    }

    private FragmentDetailsBinding mBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_details, container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null) {
            City city = getArguments().getParcelable(KEY_CITY);
            fillViews(city);
        }
    }

    private void fillViews(City city) {
        mBinding.name.setText(city.name);
        mBinding.description.setText(city.description);

        Picasso.with(getContext())
                .load(city.imageUrl)
                .placeholder(R.drawable.ic_placeholder)
                .into(mBinding.preview);
    }
}
