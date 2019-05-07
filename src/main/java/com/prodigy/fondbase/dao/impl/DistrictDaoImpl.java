package com.prodigy.fondbase.dao.impl;

import com.prodigy.fondbase.dao.DistrictDao;
import com.prodigy.fondbase.model.District;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class DistrictDaoImpl extends EntityDaoImpl implements DistrictDao {

    @Override
    public List<District> getAllByCityId(int cityId) {
        return em.createQuery("SELECT d FROM District d WHERE d.city.id=?1")
                .setParameter(1, cityId)
                .getResultList();
    }
}
