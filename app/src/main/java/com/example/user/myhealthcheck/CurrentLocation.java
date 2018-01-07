package com.example.user.myhealthcheck;



public class CurrentLocation  {

    private String StateName,CityName,CountryName,Address,url,line ;

    public CurrentLocation (String StateName,String CityName,String CountryName,String Address,String url,String line) {
        this.StateName = StateName;
        this.CityName = CityName;
        this.CountryName = CountryName;
        this.Address = Address;
        this.url = url;
        this.line = line;

    }

    public String getStateName() {
        return StateName;
    }

    public String getCityName() {
        return  CityName;
    }

    public String getCountryName() {
        return CountryName;
    }

    public String getAddress() {
        return Address;
    }

    public String geturl() {
        return url;
    }

    public String getline() {
        return line;
    }

}