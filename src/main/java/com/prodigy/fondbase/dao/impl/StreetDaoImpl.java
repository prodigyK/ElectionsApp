package com.prodigy.fondbase.dao.impl;

import com.prodigy.fondbase.dao.StreetDao;
import com.prodigy.fondbase.model.Street;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class StreetDaoImpl extends EntityDaoImpl implements StreetDao {

    @Override
    public Street getByName(String street) {

        street = street.replace("'", "''");
        String query = "SELECT s FROM Street s WHERE s.name LIKE '" + street + "%'";
        Street result = null;
        try {
            result = (Street) em.createQuery(query).setMaxResults(1).getSingleResult();
        } catch (Exception e){
            return null;
        }

        return result;
    }

    @Override
    public List<Street> getAllByName(String street) {
        street = street.replace("'", "''");
        String query = "SELECT s FROM Street s WHERE s.name LIKE '" + street + "%'";
        List<Street> result = new ArrayList<>();
        try {
            result = em.createQuery(query).getResultList();
        } catch (Exception e){
            return null;
        }

        return result;
    }

    @Override
    public List<String> searchByStreetName(String street) {
        return em.createQuery("SELECT DISTINCT s.name FROM Street s WHERE s.name LIKE '" + street + "%'")
                .setMaxResults(10)
                .getResultList();
    }

    @Override
    public List<Street> getAllByCity(int cityId) {
        return em.createQuery("SELECT s FROM Street s WHERE s.city.id=?1 ORDER BY s.name ASC")
                .setParameter(1, cityId)
                .getResultList();
    }

    @Override
    public List<Street> getByDistrict(int districtId) {
//        return em.createQuery("SELECT s FROM Street s WHERE ");
        return null;
    }
}
