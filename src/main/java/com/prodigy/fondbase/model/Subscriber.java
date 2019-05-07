package com.prodigy.fondbase.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bf_subscriber")
public class Subscriber extends AbstractBaseEntity {

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "middlename")
    private String middlename;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "subscribe_day")
    private LocalDate subscribeDay;

    @Column(name = "status")
    private String status;

    @Column(name = "iin")
    private String iin;

    @Column(name = "passport")
    private String passport;

    @Column(name = "date_of_issue")
    private LocalDate dateOfIssue;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "subscriber", fetch = FetchType.LAZY)
    private List<Address> addresses = new ArrayList<>();

    @OneToOne(mappedBy = "subscriber", fetch = FetchType.LAZY)
    private Phone phone;

//    @ManyToMany(mappedBy = "subscribers", fetch = FetchType.LAZY)
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "bf_subscriber_subscriber_type",
            joinColumns = {@JoinColumn(name = "subscriber_id")},
            inverseJoinColumns = {@JoinColumn(name = "type_id")}
    )
    private List<SubscriberType> subscriberTypes = new ArrayList<>();

    public Subscriber() {
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public LocalDate getSubscribeDay() {
        return subscribeDay;
    }

    public void setSubscribeDay(LocalDate subscribeDay) {
        this.subscribeDay = subscribeDay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addressRegistration) {
        this.addresses = addressRegistration;
    }

    public String getIin() {
        return iin;
    }

    public void setIin(String iin) {
        this.iin = iin;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<SubscriberType> getSubscriberTypes() {
        return subscriberTypes;
    }

    public void setSubscriberTypes(List<SubscriberType> subscriberTypes) {
        this.subscriberTypes = subscriberTypes;
    }

}

