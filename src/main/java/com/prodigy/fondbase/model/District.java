package com.prodigy.fondbase.model;

import javax.persistence.*;

@Entity
@Table(name = "bf_district")
public class District extends AbstractNamedEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;

    public District() {
    }

    public District(Integer id, String name) {
        super(id, name);
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "District{" +
                "name='" + name + '\'' +
                '}';
    }
}
