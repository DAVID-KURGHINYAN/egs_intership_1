package com.airport2.entity;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "air_company")
public class AirCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "found_date")
    private LocalDate foundDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id")
    private Address address;


    public AirCompany() {
    }

    public AirCompany(String name, LocalDate foundDate, Address address) {
        this.name = name;
        this.foundDate = foundDate;
        this.address = address;
    }

    public AirCompany(int id, String name, LocalDate foundDate, Address address) {
        this.id = id;
        this.name = name;
        this.foundDate = foundDate;
        this.address = address;
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

    public LocalDate getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(LocalDate foundDate) {
        this.foundDate = foundDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
