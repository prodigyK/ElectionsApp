package com.prodigy.fondbase.dao;


import com.prodigy.fondbase.model.AbstractBaseEntity;
import com.prodigy.fondbase.model.Subscriber;

import java.util.List;

public interface SubscriberDao<T extends AbstractBaseEntity> extends EntityDao {

    List<String> getLastnames();

    List<String> getFirstnames();

    List<Subscriber> searchPeople(Subscriber subscriber);

    List<String> searchByLastname(String lastname);

    List<String> searchByFirstname(String firstname);

    List<String> searchByMiddlename(String middlename);

    Subscriber save(Subscriber subscriber);
}
