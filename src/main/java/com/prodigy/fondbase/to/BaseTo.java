package com.prodigy.fondbase.to;

public abstract class BaseTo {
    protected Integer id;

    public BaseTo() {
    }

    boolean isNew() {
        return getId() == null;
    }

    public BaseTo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
