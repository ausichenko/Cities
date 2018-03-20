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
import com.azcltd.android.test.usichenko.cities.viewmodel.ImageViewModel;

public class DetailsFragment extends Fragment {

    private static final String KEY_CITY = "key_city";

    private FragmentDetailsBinding mBinding;

    public static DetailsFragment newInstance(City city) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();

        args.putParcelable(KEY_CITY, city);
        fragment.setArguments(args);

        return fragment;
    }

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

        City city = getArguments().getParcelable(KEY_CITY);
        mBinding.setImageViewModel(new ImageViewModel(city.imageUrl));
        mBinding.setCity(city);
    }
}
