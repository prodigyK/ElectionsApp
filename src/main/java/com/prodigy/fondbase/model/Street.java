package com.prodigy.fondbase.model;

import javax.persistence.*;

@Entity
@Table(name = "bf_street")
public class Street extends AbstractNamedEntity {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;

    public Street() {
    }

    public Street(Integer id, String name) {
        super(id, name);
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
