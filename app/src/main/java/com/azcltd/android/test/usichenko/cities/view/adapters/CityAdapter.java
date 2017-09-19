package com.azcltd.android.test.usichenko.cities.view.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.azcltd.android.test.usichenko.cities.R;
import com.azcltd.android.test.usichenko.cities.databinding.CityListItemBinding;
import com.azcltd.android.test.usichenko.cities.service.models.City;
import com.azcltd.android.test.usichenko.cities.view.callback.CityClickCallback;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    private List<City> mCityList;
    private CityClickCallback mCityClickCallback;

    public CityAdapter(CityClickCallback cityClickCallback) {
        mCityClickCallback = cityClickCallback;
    }

    public void setCityList(final List<City> cityList) {
        if (mCityList == null) {
            mCityList = cityList;
            notifyItemRangeInserted(0, cityList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mCityList.size();
                }

                @Override
                public int getNewListSize() {
                    return cityList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return CityAdapter.this.mCityList.get(oldItemPosition).id ==
                            cityList.get(newItemPosition).id;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    City project = cityList.get(newItemPosition);
                    City old = cityList.get(oldItemPosition);
                    return project.id == old.id;
                }
            });
            mCityList = cityList;
            result.dispatchUpdatesTo(this);
        }
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
        holder.binding.setCity(mCityList.get(position));
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
