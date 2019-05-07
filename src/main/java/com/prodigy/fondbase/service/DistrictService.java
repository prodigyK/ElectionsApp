package com.prodigy.fondbase.service;

import com.prodigy.fondbase.model.District;

import java.util.List;

public interface DistrictService {

    District get(int id);

    District save(District district);

    void delete(int id);

    List<District> getAll();

    List<District> getAllByCityId(int cityId);
}
