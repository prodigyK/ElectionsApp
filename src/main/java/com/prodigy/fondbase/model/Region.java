package com.prodigy.fondbase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bf_region")
public class Region extends AbstractNamedEntity {

    @OneToMany(mappedBy = "region", fetch = FetchType.LAZY)
    private List<City> cities;

    public Region() {
    }

    public Region(Region region){
        super(region.getId(), region.getName());
    }

    public Region(Integer id, String name) {
        super(id, name);
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
