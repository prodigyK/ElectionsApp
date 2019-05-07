package com.prodigy.fondbase.model.commission;

import com.prodigy.fondbase.model.AbstractBaseEntity;
import com.prodigy.fondbase.model.AbstractNamedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "bf_election")
public class Election extends AbstractNamedEntity {

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "election_date")
    private LocalDate electionDate;

    @Column(name = "enabled")
    private boolean enabled = true;

    public Election() {
    }

    public Election(Integer id, String name, String fullName, LocalDate electionDate, boolean enabled) {
        super(id, name);
        this.fullName = fullName;
        this.electionDate = electionDate;
        this.enabled = enabled;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getElectionDate() {
        return electionDate;
    }

    public void setElectionDate(LocalDate electionDate) {
        this.electionDate = electionDate;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
