package com.prodigy.fondbase.utils;

import com.prodigy.fondbase.model.Address;
import com.prodigy.fondbase.model.LivingPlace;
import com.prodigy.fondbase.model.Subscriber;
import com.prodigy.fondbase.model.SubscriberType;
import com.prodigy.fondbase.service.AddressService;
import com.prodigy.fondbase.to.SubscriberTo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SubscriberUtil {

    @Autowired
    private static AddressService addressService;


    public static SubscriberTo convertEntity(Subscriber subscriber) {
        SubscriberTo to = new SubscriberTo();

        to.setId(subscriber.getId());
        to.setLastname(subscriber.getLastname());
        to.setFirstname(subscriber.getFirstname());
        to.setBirthday(subscriber.getBirthday());
        to.setMiddlename(subscriber.getMiddlename());
        to.setIin(subscriber.getIin());
        to.setPassport(subscriber.getPassport());
        to.setDateOfIssue(subscriber.getDateOfIssue());
        to.setPhone(subscriber.getPhone().getCellPhone());
        to.setEmail(subscriber.getEmail());
        List<Address> addresses = addressService.getBySubscriberId(subscriber.getId());
        for (Address address : addresses) {
            if (address.getLivingPlace() == LivingPlace.REGISTRATION_AND_RESIDENCE_PLACE) {
                to.setLivingForRegistration(true);
                to.setAddressRegistration(address);
            } else if (address.getLivingPlace() == LivingPlace.REGISTRATION_PLACE) {
                to.setAddressRegistration(address);
            } else if (address.getLivingPlace() == LivingPlace.RESIDENCE_PLACE) {
                to.setAddressResidential(address);
            }
        }
/*
        for(SubscriberType type : subscriber.getSubscriberTypes()){
            if (type.getId() == 1000){
                to.setStoronnik(true);
            }
        }
*/

        return to;
    }

    public static Subscriber convertTo(SubscriberTo to) {
        Subscriber subscriber = new Subscriber();


        return subscriber;
    }
}
