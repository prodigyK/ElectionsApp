package com.prodigy.fondbase.to;

import com.prodigy.fondbase.model.Address;
import com.prodigy.fondbase.model.Phone;
import com.prodigy.fondbase.model.SubscriberType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SubscriberTo {

    private Integer id;

    private String lastname;

    private String firstname;

    private String middlename;

    private LocalDate birthday;

    private LocalDate subscribeDay;

    private String status;

    private String iin;

    private String passport;

    private LocalDate dateOfIssue;

    private String email;

    private boolean storonnik;

    private Address addressRegistration;

    private Address addressResidential;

    private boolean livingForRegistration;

    private String phone;

    public SubscriberTo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public boolean isStoronnik() {
        return storonnik;
    }

    public void setStoronnik(boolean storonnik) {
        this.storonnik = storonnik;
    }

    public Address getAddressRegistration() {
        return addressRegistration;
    }

    public void setAddressRegistration(Address addressRegistration) {
        this.addressRegistration = addressRegistration;
    }

    public Address getAddressResidential() {
        return addressResidential;
    }

    public void setAddressResidential(Address addressResidential) {
        this.addressResidential = addressResidential;
    }

    public boolean isLivingForRegistration() {
        return livingForRegistration;
    }

    public void setLivingForRegistration(boolean livingForRegistration) {
        this.livingForRegistration = livingForRegistration;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
