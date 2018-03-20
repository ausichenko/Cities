package com.azcltd.android.test.usichenko.cities.view.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.azcltd.android.test.usichenko.cities.R;
import com.azcltd.android.test.usichenko.cities.databinding.FragmentCitiesBinding;
import com.azcltd.android.test.usichenko.cities.service.models.City;
import com.azcltd.android.test.usichenko.cities.view.adapters.CityAdapter;
import com.azcltd.android.test.usichenko.cities.view.callback.OnCityClickListener;
import com.azcltd.android.test.usichenko.cities.viewmodel.CityListViewModel;

import java.util.ArrayList;
import java.util.List;

public class CityListFragment extends Fragment {

    private CityAdapter mCityAdapter;
    private FragmentCitiesBinding mBinding;

    private OnCityClickListener mCityClickListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCityClickListener = (OnCityClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnCityClickListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cities, container, false);

        mCityAdapter = new CityAdapter(mCityClickListener);
        mBinding.cityList.setAdapter(mCityAdapter);
        mBinding.setIsLoading(true);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final CityListViewModel viewModel = ViewModelProviders.of(this).get(CityListViewModel.class);

        mBinding.setCallback(() -> reload(viewModel));

        observeViewModel(viewModel);
    }

    private void observeViewModel(CityListViewModel viewModel) {
        viewModel.getCitiesObservable().observe(this, cities -> {
            mBinding.setIsLoading(false);
            if (cities != null) {
                mBinding.setIsSuccess(true);
                if (cities.getCities() != null && !cities.getCities().isEmpty()) {
                    mBinding.setIsEmpty(false);
                    mCityAdapter.setCities(cities.getCities());
                } else {
                    mBinding.setIsEmpty(true);
                }
            } else {
                mBinding.setIsSuccess(false);
            }
        });
    }

    private void reload(CityListViewModel viewModel) {
        viewModel.getCitiesObservable().removeObservers(CityListFragment.this);
        viewModel.initObservable();
        mBinding.setIsLoading(true);
        observeViewModel(viewModel);
    }
}
