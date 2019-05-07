package com.prodigy.fondbase.model;

import javax.persistence.*;

@Entity
@Table(name = "bf_postman")
public class Postman extends AbstractNamedEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    private Area area;

    public Postman() {
    }

    public Postman(Integer id, String name) {
        super(id, name);
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
