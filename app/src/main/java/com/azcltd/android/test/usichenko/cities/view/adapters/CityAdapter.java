package com.azcltd.android.test.usichenko.cities.view.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.azcltd.android.test.usichenko.cities.R;
import com.azcltd.android.test.usichenko.cities.databinding.ItemCityBinding;
import com.azcltd.android.test.usichenko.cities.service.models.City;
import com.azcltd.android.test.usichenko.cities.view.callbacks.OnCityClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    private List<City> mCities = new ArrayList<>();
    private OnCityClickListener mCityClickListener;

    public CityAdapter(OnCityClickListener clickListener) {
        mCityClickListener = clickListener;
    }

    public void setCities(final List<City> cities) {
        mCities.clear();
        mCities.addAll(cities);
    }

    @NonNull
    @Override
    public CityAdapter.CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCityBinding binding = DataBindingUtil.inflate(LayoutInflater.from(
                parent.getContext()), R.layout.item_city, parent, false);

        return new CityViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapter.CityViewHolder holder, int position) {
        City city = mCities.get(position);
        holder.bind(city, mCityClickListener);
    }

    @Override
    public int getItemCount() {
        return mCities != null ? mCities.size() : 0;
    }

    static class CityViewHolder extends RecyclerView.ViewHolder {

        private ItemCityBinding mBinding;

        CityViewHolder(ItemCityBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        void bind(final City city, final OnCityClickListener listener) {
            mBinding.name.setText(city.name);

            Picasso.with(mBinding.preview.getContext())
                    .load(city.imageUrl)
                    .placeholder(R.drawable.ic_placeholder)
                    .into(mBinding.preview);

            mBinding.card.setOnClickListener(v -> listener.onCityClick(city));

            mBinding.executePendingBindings();
        }
    }
}
