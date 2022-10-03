package com.jesuspinar.flagslist.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.jesuspinar.flagslist.R;
import com.jesuspinar.flagslist.controller.CountryAdapter;
import com.jesuspinar.flagslist.controller.CountryParser;
import com.jesuspinar.flagslist.model.Country;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvCountries = findViewById(R.id.lv_countries);
        CountryParser parser = new CountryParser(this);

        if(parser.parse()) {
            Country[] countries = parser.getCountries();

            CountryAdapter adapter = new CountryAdapter(this, countries);
            lvCountries.setAdapter(adapter);
        } else {
            Toast.makeText(this, "No se pudieron obtener los datos de los países", Toast.LENGTH_SHORT).show();
        }
    }
}