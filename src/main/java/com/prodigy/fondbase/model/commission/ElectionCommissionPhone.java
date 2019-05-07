package com.prodigy.fondbase.model.commission;

import com.prodigy.fondbase.model.AbstractNamedEntity;

import javax.persistence.*;

@Entity
@Table(name = "bf_election_commission_phone")
public class ElectionCommissionPhone extends AbstractNamedEntity {

    @Column(name = "phone")
    private String phone;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "commission_id")
    private ElectionCommission commission;

    public ElectionCommissionPhone() {
    }

    public ElectionCommissionPhone(Integer id, String name, String phone, ElectionCommission commission) {
        super(id, name);
        this.phone = phone;
        this.commission = commission;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ElectionCommission getCommission() {
        return commission;
    }

    public void setCommission(ElectionCommission commission) {
        this.commission = commission;
    }
}
