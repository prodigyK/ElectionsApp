package com.prodigy.fondbase.service;

import com.prodigy.fondbase.model.Address;

import java.util.List;

public interface AddressService {

    Address get(int id);

    Address save(Address address);

    void delete(int id);

    List<Address> getBySubscriberId(int subscriberId);
}
