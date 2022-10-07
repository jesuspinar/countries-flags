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
    static class ViewHolder {
        ImageView ivFlag ;
        TextView tvCountryName;
        TextView tvCountryCapital;
        TextView tvCountryPopulation;
    }

    private final Country[] countries;

    public CountryAdapter(Context context, Country[] countries) {
        super(context, R.layout.listitem_country, countries);
        this.countries = countries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        ViewHolder holder;

        if (convertView == null){
            holder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            item = inflater.inflate(R.layout.listitem_country, null);
            holder.tvCountryName = item.findViewById(R.id.tvCountryName);
            holder.tvCountryCapital = item.findViewById(R.id.tvCaptialName);
            holder.tvCountryPopulation = item.findViewById(R.id.tvPoblationNumb);
            holder.ivFlag = item.findViewById(R.id.ivFlag);

            item.setTag(holder);
        }else{
            holder = (ViewHolder) item.getTag();
        }

        try {
            String flagName = "_"+countries[position].getCode().toLowerCase();
            int resID = getContext().getResources().getIdentifier(flagName, "drawable", getContext().getPackageName());

            if(resID != 0) {
                holder.ivFlag.setImageResource(resID);
            } else {
                resID = getContext().getResources().getIdentifier(
                        "_onu", "drawable", getContext().getPackageName());
                holder.ivFlag.setImageResource(resID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.tvCountryName.setText(countries[position].getCountry());
        holder.tvCountryCapital.setText(countries[position].getCapital());
        holder.tvCountryPopulation.setText(String.valueOf(countries[position].getPopulation()));

        return item;
    }
}