package com.jesuspinar.flagslist.model;

public class Country {
    private String code;
    private String country;
    private String capital;
    private String population;

    public Country(String code, String country, String capital, String population) {
        this.code = code;
        this.country = country;
        this.capital = capital;
        this.population = population;
    }

    public String getCode() {
        return code;
    }

    public String getCountry() {
        return country;
    }

    public String getCapital() {
        return capital;
    }

    public String getPopulation() {
        return population;
    }
}
