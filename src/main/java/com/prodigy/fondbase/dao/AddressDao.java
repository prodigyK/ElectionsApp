package com.prodigy.fondbase.dao;

import com.prodigy.fondbase.model.Address;
import com.prodigy.fondbase.model.Subscriber;

import java.util.List;

public interface AddressDao<T extends Address> extends EntityDao{

    List<Address> find(Address address);

    List<Address> getBySubscriberId(int subscriberId);
}
