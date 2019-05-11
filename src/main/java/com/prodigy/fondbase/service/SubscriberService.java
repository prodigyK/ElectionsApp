package com.prodigy.fondbase.service;

import com.prodigy.fondbase.model.Subscriber;
import com.prodigy.fondbase.to.SearchSubscriberTo;
import com.prodigy.fondbase.to.SubscriberTo;

import java.util.List;

public interface SubscriberService{

    List<Subscriber> searchPeople(SearchSubscriberTo searchSubscriberTo);

    List<String> searchByLastname(String lastname);

    List<String> searchByFirstname(String firstname);

    List<String> searchByMiddlename(String middlename);

    Subscriber get(int id);

    SubscriberTo getTo(int id);

    List<SearchSubscriberTo> convertSearchEntityList(List<Subscriber> subscriberList);

    SearchSubscriberTo convertSearchEntity(Subscriber subscriber);

    SubscriberTo convertEntity(Subscriber subscriber);

    Subscriber convertToAndSave(SubscriberTo to);

    Subscriber save(Subscriber subscriber);

    Subscriber addTo(SubscriberTo subscriberTo);

}
