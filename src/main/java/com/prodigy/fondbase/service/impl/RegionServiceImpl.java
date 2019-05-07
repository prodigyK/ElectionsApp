package com.prodigy.fondbase.service.impl;

import com.prodigy.fondbase.model.Region;
import com.prodigy.fondbase.service.AbstractRootService;
import com.prodigy.fondbase.service.RegionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RegionServiceImpl extends AbstractRootService implements RegionService{

    @Override
    public Region get(int id) {
        return (Region) regionDao.get(Region.class, id);
    }

    @Override
    public Region save(Region region) {
        return (Region) regionDao.save(region);
    }

    @Override
    public List<Region> getAll() {
        return regionDao.getAll(Region.class);
    }

    @Override
    public void delete(int id) {
        regionDao.delete(Region.class, id);
    }
}
