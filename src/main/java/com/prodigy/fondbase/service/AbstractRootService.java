package com.prodigy.fondbase.service;

import com.prodigy.fondbase.dao.*;
import com.prodigy.fondbase.model.*;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractRootService {

    @Autowired
    protected RegionDao regionDao;

    @Autowired
    protected CityDao cityDao;

    @Autowired
    protected DistrictDao districtDao;

    @Autowired
    protected StreetDao streetDao;

    @Autowired
    protected AddressDao addressDao;

    @Autowired
    protected HouseDao<House> houseDao;

    @Autowired
    protected SubscriberDao subscriberDao;

    @Autowired
    protected AreaDao areaDao;

    @Autowired
    protected PhoneDao phoneDao;

    @Autowired
    protected SubscriberTypeDao subscriberTypeDao;


}
