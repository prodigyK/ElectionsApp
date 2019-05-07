package com.prodigy.fondbase.service;

import com.prodigy.fondbase.model.Street;

import java.util.List;

public interface StreetService {

    List<String> searchByStreetName(String street);

    List<Street> getAllByCity(int cityId);

    List<Street> getByDistrict(int districtId);
}
