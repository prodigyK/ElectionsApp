package com.prodigy.fondbase.service.impl;

import com.prodigy.fondbase.model.District;
import com.prodigy.fondbase.service.AbstractRootService;
import com.prodigy.fondbase.service.DistrictService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DistrictServiceImpl extends AbstractRootService implements DistrictService {

    @Override
    public District get(int id) {
        return (District) districtDao.get(District.class, id);
    }

    @Override
    public District save(District district) {
        return (District) districtDao.save(district);
    }

    @Override
    public void delete(int id) {
        districtDao.delete(District.class, id);
    }

    @Override
    public List<District> getAll() {
        return districtDao.getAll(District.class);
    }

    @Override
    public List<District> getAllByCityId(int cityId) {
        return districtDao.getAllByCityId(cityId);
    }
}
