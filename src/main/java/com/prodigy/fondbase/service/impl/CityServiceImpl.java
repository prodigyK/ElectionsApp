package com.prodigy.fondbase.service.impl;

import com.prodigy.fondbase.model.City;
import com.prodigy.fondbase.service.AbstractRootService;
import com.prodigy.fondbase.service.CityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CityServiceImpl extends AbstractRootService implements CityService {

    @Override
    public City get(int id) {
        return (City) cityDao.get(City.class, id);
    }

    @Override
    public City save(City city) {
        return (City) cityDao.save(city);
    }

    @Override
    public void delete(int id) {
        cityDao.delete(City.class, id);
    }

    @Override
    public List<City> getAll() {
        return cityDao.getAll(City.class);
    }

    @Override
    public List<City> getAllByRegionId(int regionId) {
        return cityDao.getAllByRegionId(regionId);
    }
}
