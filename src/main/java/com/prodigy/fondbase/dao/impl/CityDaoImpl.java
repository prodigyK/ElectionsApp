package com.prodigy.fondbase.dao.impl;

import com.prodigy.fondbase.dao.CityDao;
import com.prodigy.fondbase.dao.EntityDao;
import com.prodigy.fondbase.model.City;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class CityDaoImpl extends EntityDaoImpl implements CityDao {

    @Override
    public List<City> getAllByRegionId(int regionId) {
        return em.createQuery("SELECT c FROM City c WHERE c.region.id=?1")
                .setParameter(1, regionId)
                .getResultList();
    }
}
