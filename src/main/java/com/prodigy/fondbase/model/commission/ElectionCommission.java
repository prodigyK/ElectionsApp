package com.prodigy.fondbase.model.commission;

import com.prodigy.fondbase.model.AbstractNamedEntity;

import javax.persistence.*;

@Entity
@Table(name = "bf_election_commission")
public class ElectionCommission extends AbstractNamedEntity{

    @Column(name = "number")
    private int number;

    @Column(name = "enabled")
    private boolean enabled = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private ElectionCommission parent;

    public ElectionCommission() {
    }

    public ElectionCommission(Integer id, String name, int number, boolean enabled, ElectionCommission parent) {
        super(id, name);
        this.number = number;
        this.enabled = enabled;
        this.parent = parent;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public ElectionCommission getParent() {
        return parent;
    }

    public void setParent(ElectionCommission parent) {
        this.parent = parent;
    }
}
