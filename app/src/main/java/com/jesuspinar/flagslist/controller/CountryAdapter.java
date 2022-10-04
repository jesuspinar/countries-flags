package com.jesuspinar.flagslist.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jesuspinar.flagslist.R;
import com.jesuspinar.flagslist.model.Country;

import java.util.List;

public class CountryAdapter extends ArrayAdapter<Country> {

    private final Country[] countries;

    public CountryAdapter(Context context, Country[] countries) {
        super(context, R.layout.listitem_country, countries);
        this.countries = countries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            item = inflater.inflate(R.layout.listitem_country, null);
        }
        ImageView ivFlag = item.findViewById(R.id.ivFlag);

        try {
            String flagName = "_"+countries[position].getCode().toLowerCase();
            int resID = getContext().getResources().getIdentifier(flagName, "drawable", getContext().getPackageName());

            if(resID != 0) {
                ivFlag.setImageResource(resID);
            } else {
                resID = getContext().getResources().getIdentifier(
                        "_onu", "drawable", getContext().getPackageName());
                ivFlag.setImageResource(resID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TextView tvCountryName = item.findViewById(R.id.tvCountryName);
        tvCountryName.setText(countries[position].getCountry());

        TextView tvCountryCapital = item.findViewById(R.id.tvCaptialName);
        tvCountryCapital.setText(countries[position].getCapital());

        TextView tvCountryPopulation = item.findViewById(R.id.tvPoblationNumb);
        tvCountryPopulation.setText(String.valueOf(countries[position].getPopulation()));

        return item;
    }
}
