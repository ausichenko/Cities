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
import com.azcltd.android.test.usichenko.cities.view.adapters.CityAdapter;
import com.azcltd.android.test.usichenko.cities.view.callbacks.OnCityClickListener;
import com.azcltd.android.test.usichenko.cities.viewmodel.CityListViewModel;

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

        mBinding.refreshLayout.setColorSchemeResources(R.color.accent);

        mCityAdapter = new CityAdapter(mCityClickListener);
        mBinding.recyclerView.setAdapter(mCityAdapter);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final CityListViewModel viewModel = ViewModelProviders.of(this).get(CityListViewModel.class);

        mBinding.refreshLayout.setOnRefreshListener(() -> reload(viewModel));

        observeViewModel(viewModel);
    }

    private void reload(CityListViewModel viewModel) {
        mBinding.progressLayout.setVisibility(View.VISIBLE);
        viewModel.getCitiesObservable().removeObservers(CityListFragment.this);
        viewModel.initObservable();
        observeViewModel(viewModel);
    }

    private void observeViewModel(CityListViewModel viewModel) {
        mBinding.progressLayout.setVisibility(View.VISIBLE);
        viewModel.getCitiesObservable().observe(this, cities -> {
            mBinding.progressLayout.setVisibility(View.GONE);
            if (mBinding.refreshLayout.isRefreshing()) {
                mBinding.refreshLayout.setRefreshing(false);
            }
            if (cities != null) {
                if (cities.getCities() != null && !cities.getCities().isEmpty()) {
                    mBinding.emptyLayout.setVisibility(View.GONE);
                    mCityAdapter.setCities(cities.getCities());
                    mCityAdapter.notifyDataSetChanged();
                } else {
                    mBinding.emptyLayout.setVisibility(View.VISIBLE);
                }
            } else {
                mBinding.errorLayout.setVisibility(View.VISIBLE);
            }
        });
    }


}
