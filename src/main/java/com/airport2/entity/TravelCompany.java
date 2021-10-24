package com.airport2.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "travel_company")
public class TravelCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "travelCompany")
    private List<TripToPassengerToTravelCompanyRel> tripToPassengerToTravelCompanyRels;

    public TravelCompany() {
    }

    public TravelCompany(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public TravelCompany(int id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public TravelCompany(int id, String name, Address address, List<TripToPassengerToTravelCompanyRel> tripToPassengerToTravelCompanyRels) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.tripToPassengerToTravelCompanyRels = tripToPassengerToTravelCompanyRels;
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

    public List<TripToPassengerToTravelCompanyRel> getTripToPassengerToTravelCompanyRels() {
        return tripToPassengerToTravelCompanyRels;
    }

    public void setTripToPassengerToTravelCompanyRels(List<TripToPassengerToTravelCompanyRel> tripToPassengerToTravelCompanyRels) {
        this.tripToPassengerToTravelCompanyRels = tripToPassengerToTravelCompanyRels;
    }

    @Override
    public String toString() {
        return "TravelCompany{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", tripToPassengerToTravelCompanyRels=" + tripToPassengerToTravelCompanyRels +
                '}';
    }
}
