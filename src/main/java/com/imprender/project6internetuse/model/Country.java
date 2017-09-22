package com.imprender.project6internetuse.model;


import javax.persistence.*;

//When hibernate detects this annotation, will map a country object to a row in the db
//A nome element could be specified "(name = "NAME")", but if we don't do it the default will be the class / entry field

@Entity
public class Country {

    //If we include an Id field, any other field wouldn't need the column annotation (they are such as default)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private char id;

    @Column
    private String name;  //maximo length of 32 characters

    @Column
    private double internetUsers;  //(11,8)

    @Column
    private double adultLiteracyRate;  //(11,8)


    public Country() {
    }

    public Country(CountryBuilder countryBuilder) {
        this.name = countryBuilder.name;
        this.adultLiteracyRate = countryBuilder.literatePopulation;
        this.internetUsers = countryBuilder.internetUsers;
    }


    public double getAdultLiteracyRate() {
        return adultLiteracyRate;
    }

    public void setAdultLiteracyRate(int adultLiteracyRate) {
        this.adultLiteracyRate = adultLiteracyRate;
    }

    public double getInternetUsers() {
        return internetUsers;
    }

    public void setInternetUsers(int internetUsers) {
        this.internetUsers = internetUsers;
    }

    public static class CountryBuilder {
        private String name;
        private int literatePopulation;
        private int internetUsers;

        public CountryBuilder (String name) {
            this.name = name;
        }

        public CountryBuilder withPopulation(int population) {
            this.literatePopulation = population;
            return this;
        }

        public CountryBuilder withInternetUsers(int internetUsers) {
            this.internetUsers = internetUsers;
            return this;
        }

        public Country build() {
            return new Country(this);
        }
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", literatePopulation=" + adultLiteracyRate +
                ", internetUsers=" + internetUsers +
                '}';
    }
}
