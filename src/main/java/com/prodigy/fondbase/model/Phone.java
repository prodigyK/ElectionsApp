package com.prodigy.fondbase.model;


import javax.persistence.*;

@Entity
@Table(name = "bf_phone")
public class Phone extends AbstractBaseEntity{

    @Column(name = "cellPhone")
    private String cellPhone;

    @Column(name = "home_phone")
    private String homePhone;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subscriber_id")
    private Subscriber subscriber;

    public Phone() {
    }

    public Phone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public Phone(String cellPhone, String homePhone) {
        this.cellPhone = cellPhone;
        this.homePhone = homePhone;
    }

    public Phone(String cellPhone, String homePhone, Subscriber subscriber) {
        this.cellPhone = cellPhone;
        this.homePhone = homePhone;
        this.subscriber = subscriber;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }
}
