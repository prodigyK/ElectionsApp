package com.prodigy.fondbase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "bf_address")
public class Address extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    private Area area;

    @Column(name = "sector")
    private String sector;

    @Column(name = "mail_index")
    private String mailIndex;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "region_id")
    private Region region;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "district_id")
    private District district;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "house_id")
    private House house;

    @Column(name = "flat")
    private String flat;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "living_place")
    private LivingPlace livingPlace = LivingPlace.REGISTRATION_AND_RESIDENCE_PLACE;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "subscriber_id")
    private Subscriber subscriber;

    public Address() {
    }

    public Address(Address address) {
        super(address.getId());
        this.area = address.getArea();
        this.sector = address.getSector();
        this.mailIndex = address.getMailIndex();
        this.region = new Region(address.getRegion());
        this.city = new City(address.getCity());
        this.district = new District(address.getDistrict());
        this.house = new House(address.getHouse());
        this.flat = address.getFlat();
        this.livingPlace = address.getLivingPlace();
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getMailIndex() {
        return mailIndex;
    }

    public void setMailIndex(String mailIndex) {
        this.mailIndex = mailIndex;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public LivingPlace getLivingPlace() {
        return livingPlace;
    }

    public void setLivingPlace(LivingPlace livingPlace) {
        this.livingPlace = livingPlace;
    }

    @Override
    public String toString() {
        return "Address{" +
                "area=" + area +
                ", sector='" + sector + '\'' +
                ", mailIndex='" + mailIndex + '\'' +
                ", region=" + region +
                ", city=" + city +
                ", district=" + district +
                ", house=" + house +
                ", flat='" + flat + '\'' +
                ", subscriber=" + subscriber +
                '}';
    }
}
