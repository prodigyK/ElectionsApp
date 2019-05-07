package com.prodigy.fondbase.service.impl;

import com.prodigy.fondbase.model.Street;
import com.prodigy.fondbase.service.AbstractRootService;
import com.prodigy.fondbase.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class StreetServiceImpl extends AbstractRootService implements StreetService {


    @Override
    public List<String> searchByStreetName(String street) {
        return streetDao.searchByStreetName(street);
    }

    @Override
    public List<Street> getAllByCity(int cityId) {
        return streetDao.getAllByCity(cityId);
    }

    @Override
    public List<Street> getByDistrict(int districtId) {
        return null;
    }
}
