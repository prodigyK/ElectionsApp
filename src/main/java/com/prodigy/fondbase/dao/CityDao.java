package com.prodigy.fondbase.dao;

import com.prodigy.fondbase.model.City;

import java.util.List;

public interface CityDao<T extends City> extends EntityDao {

    List<City> getAllByRegionId(int regionId);
}
