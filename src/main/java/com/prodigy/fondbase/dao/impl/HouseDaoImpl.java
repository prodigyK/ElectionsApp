package com.prodigy.fondbase.dao.impl;


import com.prodigy.fondbase.dao.HouseDao;
import com.prodigy.fondbase.model.House;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class HouseDaoImpl extends EntityDaoImpl implements HouseDao{

    @Override
    public House find(House house) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<House> criteria = builder.createQuery(House.class);
        Root<House> ipRoot = criteria.from(House.class);
        criteria.select(ipRoot);

        List<Predicate> andPredicates = new ArrayList<Predicate>();
        andPredicates.add(builder.and(builder.equal(ipRoot.get("street"), house.getStreet())));
        andPredicates.add(builder.and(builder.equal(ipRoot.get("houseNumber"), house.getHouseNumber())));
        andPredicates.add(builder.and(builder.equal(ipRoot.get("corps"), house.getCorps())));
        andPredicates.add(builder.and(builder.equal(ipRoot.get("letter"), house.getLetter())));
        andPredicates.add(builder.and(builder.equal(ipRoot.get("district"), house.getDistrict())));

        Predicate predicate = builder.and(andPredicates.toArray(new Predicate[andPredicates.size()]));

        criteria.where(predicate);
        TypedQuery<House> typedQuery = em.createQuery(criteria);
        List<House> houses = typedQuery.getResultList();

        return (houses.size() > 0) ? houses.get(0) : null;
    }
}
