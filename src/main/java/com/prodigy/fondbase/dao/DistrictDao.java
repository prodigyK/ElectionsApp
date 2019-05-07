package com.prodigy.fondbase.dao;

import com.prodigy.fondbase.model.District;

import java.util.List;

public interface DistrictDao<T extends District> extends EntityDao {

    List<District> getAllByCityId(int cityId);
}
