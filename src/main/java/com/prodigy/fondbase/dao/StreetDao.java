package com.prodigy.fondbase.dao;

import com.prodigy.fondbase.model.Street;

import java.util.List;

public interface StreetDao<T extends Street> extends EntityDao{

    Street getByName(String street);

    List<String> searchByStreetName(String street);

    List<Street> getAllByCity(int cityId);

    List<Street> getByDistrict(int districtId);
}
