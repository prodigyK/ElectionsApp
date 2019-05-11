package com.prodigy.fondbase.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bf_subscriber_type")
public class SubscriberType extends AbstractNamedEntity{

    public static final int STORONNIK_TYPE = 1000;

    @ManyToMany(mappedBy = "subscriberTypes", fetch = FetchType.LAZY)
    private List<Subscriber> subscribers = new ArrayList<>();

    public SubscriberType() {
    }

    public SubscriberType(Integer id, String name) {
        super(id, name);
    }

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }
}
