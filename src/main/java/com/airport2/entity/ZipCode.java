package com.airport2.entity;

import javax.persistence.*;


@Entity
@Table(name = "zip_code")
public class ZipCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    public ZipCode() {
    }


    public ZipCode(int id, String country, String city) {
        this.id = id;
        this.country = country;
        this.city = city;
    }

    public ZipCode(int id, String postalCode, String country, String city) {
        this.id = id;
        this.postalCode = postalCode;
        this.country = country;
        this.city = city;
    }

    public ZipCode(String postalCode, String country, String city) {
        this.postalCode = postalCode;
        this.country = country;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() { return postalCode; }

    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    @Override
    public String toString() {
        return "ZipCode{" +
                "id=" + id +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
