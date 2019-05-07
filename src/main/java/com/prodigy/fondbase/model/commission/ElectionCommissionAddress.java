package com.prodigy.fondbase.model.commission;

import com.prodigy.fondbase.model.*;

import javax.persistence.*;

@Entity
@Table(name = "bf_election_commission_address")
public class ElectionCommissionAddress extends AbstractBaseEntity {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "district_id")
    private District district;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "house_id")
    private House house;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "commission_id")
    private ElectionCommission commission;

    public ElectionCommissionAddress() {
    }

    public ElectionCommissionAddress(Integer id, Region region, City city, District district, House house, ElectionCommission commission) {
        super(id);
        this.region = region;
        this.city = city;
        this.district = district;
        this.house = house;
        this.commission = commission;
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

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public ElectionCommission getCommission() {
        return commission;
    }

    public void setCommission(ElectionCommission commission) {
        this.commission = commission;
    }
}
