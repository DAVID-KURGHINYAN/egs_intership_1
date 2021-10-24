package com.newAirport.entity;

import java.time.LocalDate;

public class Company {

    private int id;
    private String name;
    private Address address;
    private LocalDate foundDate;

    public Company() {
    }

    public Company(int id, String name, Address address, LocalDate foundDate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.foundDate = foundDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(LocalDate foundDate) {
        this.foundDate = foundDate;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", foundDate=" + foundDate +
                '}';
    }
}
