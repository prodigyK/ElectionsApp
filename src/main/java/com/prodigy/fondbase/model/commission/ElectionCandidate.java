package com.prodigy.fondbase.model.commission;

import com.prodigy.fondbase.model.AbstractNamedEntity;

import javax.persistence.*;
import java.awt.*;

@Entity
@Table(name = "bf_election_candidate")
public class ElectionCandidate extends AbstractNamedEntity {

    @Column(name = "left_out")
    private boolean leftOut = false;

    @Column(name = "our_cand")
    private boolean ourCand = false;

    @Column(name = "our_tech_cand")
    private boolean ourTechCand = false;

    @Column(name = "enabled")
    private boolean enabled = true;

    @Column(name = "top")
    private boolean top = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tech_of_parent")
    private ElectionCandidate techOfParent;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "election_id")
    private Election election;

    @Column(name = "ordering")
    private int ordering = 1000;

    @Column(name = "color")
    private String color = "#ffffff";

    public ElectionCandidate() {
    }

    public ElectionCandidate(String name, int ordering) {
        this.name = name;
        this.ordering = ordering;
    }

    public ElectionCandidate(Integer id, String name, boolean leftOut, boolean ourCand, boolean ourTechCand,
                             boolean enabled, boolean top, ElectionCandidate techOfParent, Election election) {
        super(id, name);
        this.leftOut = leftOut;
        this.ourCand = ourCand;
        this.ourTechCand = ourTechCand;
        this.enabled = enabled;
        this.top = top;
        this.techOfParent = techOfParent;
        this.election = election;
    }

    public boolean isLeftOut() {
        return leftOut;
    }

    public void setLeftOut(boolean leftOut) {
        this.leftOut = leftOut;
    }

    public boolean isOurCand() {
        return ourCand;
    }

    public void setOurCand(boolean ourCand) {
        this.ourCand = ourCand;
    }

    public boolean isOurTechCand() {
        return ourTechCand;
    }

    public void setOurTechCand(boolean ourTechCand) {
        this.ourTechCand = ourTechCand;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public ElectionCandidate getTechOfParent() {
        return techOfParent;
    }

    public void setTechOfParent(ElectionCandidate techOfParent) {
        this.techOfParent = techOfParent;
    }

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public int getOrdering() {
        return ordering;
    }

    public void setOrdering(int ordering) {
        this.ordering = ordering;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
