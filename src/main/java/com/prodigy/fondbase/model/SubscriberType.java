package com.prodigy.fondbase.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bf_subscriber_type")
public class SubscriberType extends AbstractNamedEntity{

/*
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "bf_subscriber_subscriber_type",
            joinColumns = {@JoinColumn(name = "type_id")},
            inverseJoinColumns = {@JoinColumn(name = "subscriber_id")}
    )
*/
    @ManyToMany(mappedBy = "subscriberTypes", fetch = FetchType.LAZY)
    private List<Subscriber> subscribers = new ArrayList<>();

    public SubscriberType() {
    }

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }
}
