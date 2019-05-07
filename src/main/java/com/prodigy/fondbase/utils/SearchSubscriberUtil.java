package com.prodigy.fondbase.utils;

import com.prodigy.fondbase.model.Address;
import com.prodigy.fondbase.model.Subscriber;
import com.prodigy.fondbase.service.AddressService;
import com.prodigy.fondbase.to.SearchSubscriberTo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class SearchSubscriberUtil {

    @Autowired
    private AddressService addressService;

    public static List<SearchSubscriberTo> convertEntityList(List<Subscriber> subscriberList){

        List<SearchSubscriberTo> searchSubscriberToList = new ArrayList<>();
        for(Subscriber subscriber : subscriberList){
             searchSubscriberToList.add(convertEntity(subscriber));
        }
        return searchSubscriberToList;
    }

    public static SearchSubscriberTo convertEntity(Subscriber subscriber){

        SearchSubscriberTo to = new SearchSubscriberTo();
        to.setId(subscriber.getId());
        to.setLastname(subscriber.getLastname());
        to.setFirstname(subscriber.getFirstname());
        to.setMiddlename(subscriber.getMiddlename());


        List<Address> addresses = new SearchSubscriberUtil().getAddressService().getBySubscriberId(subscriber.getId());

        to.setRegion(String.valueOf(addresses.get(0).getRegion().getId()));
        to.setCity(String.valueOf(addresses.get(0).getCity().getId()));
        to.setDistrict(addresses.get(0).getDistrict().getName());
        to.setStreet(addresses.get(0).getHouse().getStreet().getName());
        to.setHouse(addresses.get(0).getHouse().getHouseNumber());
        to.setFlat(addresses.get(0).getFlat());

        return to;
    }

    public AddressService getAddressService() {
        return addressService;
    }
}
