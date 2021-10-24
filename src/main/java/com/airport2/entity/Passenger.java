package com.airport2.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "passenger")
public class Passenger {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "passenger")
    private List<TripToPassengerToTravelCompanyRel> tripToPassengerToTravelCompanyRels;





    public Passenger() {
    }

    public Passenger(String name, String phone, Address address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public Passenger(int id, String name, String phone, Address address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public Passenger(int id, String name, String phone, Address address, List<TripToPassengerToTravelCompanyRel> tripToPassengerToTravelCompanyRels) {
        this.id = id;
        this.name = name;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        return "Passenger{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address=" + address +
                '}';
    }
}