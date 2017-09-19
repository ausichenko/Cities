package com.azcltd.android.test.usichenko.cities.view.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.azcltd.android.test.usichenko.cities.R;
import com.azcltd.android.test.usichenko.cities.databinding.CityListItemBinding;
import com.azcltd.android.test.usichenko.cities.service.models.City;
import com.azcltd.android.test.usichenko.cities.view.callback.CityClickCallback;
import com.azcltd.android.test.usichenko.cities.viewmodel.ImageViewModel;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    private List<City> mCityList;
    private CityClickCallback mCityClickCallback;

    public CityAdapter(CityClickCallback cityClickCallback) {
        mCityClickCallback = cityClickCallback;
    }

    public void setCityList(final List<City> cityList) {
        mCityList = cityList;
    }

    @Override
    public CityAdapter.CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CityListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.city_list_item,
                        parent, false);

        binding.setCallback(mCityClickCallback);

        return new CityViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CityAdapter.CityViewHolder holder, int position) {
        City city = mCityList.get(position);
        holder.binding.setCity(city);
        holder.binding.setImageViewModel(new ImageViewModel(city.imageUrl));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mCityList != null ? mCityList.size() : 0;
    }

    static class CityViewHolder extends RecyclerView.ViewHolder {

        final CityListItemBinding binding;

        public CityViewHolder(CityListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
