package com.prodigy.fondbase.service;

import com.prodigy.fondbase.model.City;
import java.util.List;

public interface CityService {

    City get(int id);

    City save(City city);

    void delete(int id);

    List<City> getAll();

    List<City> getAllByRegionId(int regionId);
}
