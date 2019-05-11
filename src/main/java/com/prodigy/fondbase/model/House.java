package com.prodigy.fondbase.model;

import javax.persistence.*;

@Entity
@Table(name = "bf_house")
public class House extends AbstractBaseEntity {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "street_id")
    private Street street;

    @Column(name = "house")
    private String houseNumber;

    @Column(name = "corps")
    private String corps;

    @Column(name = "corps_letter")
    private String letter;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "district_id")
    private District district;

    public House() {
    }

    public House(House house) {
        super(house.getId());
        this.street = new Street(house.getStreet());
        this.houseNumber = house.getHouseNumber();
        this.corps = house.getCorps();
        this.letter = house.getLetter();
    }

    public House(Street street, String houseNumber, String corps, String letter) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.corps = corps;
        this.letter = letter;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCorps() {
        return corps;
    }

    public void setCorps(String corps) {
        this.corps = corps;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return "House{" +
                "street=" + street +
                ", houseNumber='" + houseNumber + '\'' +
                ", corps='" + corps + '\'' +
                ", letter='" + letter + '\'' +
                '}';
    }
}
