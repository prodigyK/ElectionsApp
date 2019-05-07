package com.prodigy.fondbase.dao.impl;

import com.prodigy.fondbase.dao.AddressDao;
import com.prodigy.fondbase.model.Address;
import com.prodigy.fondbase.model.Subscriber;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class AddressDaoImpl extends EntityDaoImpl implements AddressDao {

    @Override
    public List<Address> find(Address address) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Address> criteria = builder.createQuery(Address.class);
        Root<Address> ipRoot = criteria.from(Address.class);
        criteria.select(ipRoot);

        List<Predicate> andPredicates = new ArrayList<Predicate>();

        andPredicates.add(builder.and(builder.equal(ipRoot.get("house"), address.getHouse())));
        andPredicates.add(builder.and(builder.equal(ipRoot.get("flat"), address.getFlat())));

        criteria.where(andPredicates.toArray(new Predicate[andPredicates.size()]));

        return em.createQuery(criteria).getResultList();

    }

    @Override
    public List<Address> getBySubscriberId(int subscriberId) {
        return em.createQuery("SELECT a FROM Address a WHERE a.subscriber.id=?1")
                .setParameter(1, subscriberId)
                .getResultList();
    }
}
