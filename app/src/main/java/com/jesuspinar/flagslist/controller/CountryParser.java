package com.jesuspinar.flagslist.controller;

import android.content.Context;
import android.util.Log;

import com.jesuspinar.flagslist.R;
import com.jesuspinar.flagslist.model.Country;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class CountryParser {

    private Country[] countries;
    private final InputStream countriesFile;

    public CountryParser(Context c) {
        this.countriesFile = c.getResources().openRawResource(R.raw.countries);
    }

    public boolean parse() {

        boolean parsed = false;
        countries = null;

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            //Checks if the XML is valid
            Document dom = builder.parse(countriesFile);
            //Increases cpu usage
            Element root = dom.getDocumentElement();
            NodeList items = root.getElementsByTagName("country");

            countries = new Country[items.getLength()];

            for (int i = 0; i < items.getLength(); i++) {
                Node item = items.item(i);

                String countryCode = item.getAttributes().getNamedItem("countryCode").getNodeValue();
                String countryName = item.getAttributes().getNamedItem("countryName").getNodeValue();
                String countryCapital = item.getAttributes().getNamedItem("capital").getNodeValue();
                String countryPopulation = String.valueOf(Long.parseLong(item.getAttributes().getNamedItem("population").getNodeValue()));

                countries[i] = new Country(countryCode, countryName,countryCapital,countryPopulation);
            }
            parsed = true;

        } catch (ParserConfigurationException pce) {
            Log.e("CountryParser", "ParserConfigurationException: "+pce.getLocalizedMessage());
        } catch (Exception e) {
            Log.e("CountryParser", "Unknown Exception: "+e.getLocalizedMessage());
        }
        return parsed;
    }

    public Country[] getCountries() {
        return this.countries;
    }
}
