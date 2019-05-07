package com.prodigy.fondbase.model;

import javax.persistence.*;

@Entity
@Table(name = "bf_area")
public class Area extends AbstractNamedEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    public Area() {
    }

    public Area(Integer id, String name) {
        super(id, name);
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
