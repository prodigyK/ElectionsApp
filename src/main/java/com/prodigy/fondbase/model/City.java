package com.prodigy.fondbase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bf_city")
public class City extends AbstractNamedEntity {

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private List<District> districts;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "region_id")
    private Region region;

    public City() {
    }

    public City(Integer id, String name) {
        super(id, name);
    }

    public List<District> getDistricts() {
        return districts;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                '}';
    }
}
