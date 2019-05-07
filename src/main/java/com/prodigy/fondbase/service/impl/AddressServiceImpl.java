package com.prodigy.fondbase.service.impl;


import com.prodigy.fondbase.model.Address;
import com.prodigy.fondbase.service.AbstractRootService;
import com.prodigy.fondbase.service.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AddressServiceImpl extends AbstractRootService implements AddressService{

    @Override
    public Address get(int id) {
        return (Address) addressDao.get(Address.class, id);
    }

    @Override
    public Address save(Address address) {
        return (Address) addressDao.save(address);
    }

    @Override
    public void delete(int id) {
        addressDao.delete(Address.class, id);
    }

    @Override
    public List<Address> getBySubscriberId(int subscriberId) {
        return addressDao.getBySubscriberId(subscriberId);
    }
}
