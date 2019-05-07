package com.prodigy.fondbase.dao.impl;

import com.prodigy.fondbase.dao.SubscriberDao;
import com.prodigy.fondbase.model.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class SubscriberDaoImpl<T extends AbstractBaseEntity> extends EntityDaoImpl implements SubscriberDao {

    @Override
    @Transactional
    public Subscriber save(Subscriber subscriber) {
        if (subscriber.isNew()) {
            em.persist(subscriber);
            return subscriber;
        } else {
            return em.merge(subscriber);
        }
    }

    @Override
    public List<String> getLastnames() {
        return em.createQuery("SELECT DISTINCT s.lastname FROM Subscriber s ORDER BY s.lastname ASC")
                .getResultList();
    }

    @Override
    public List<String> getFirstnames() {
        return em.createQuery("SELECT DISTINCT s.firstname FROM Subscriber s ORDER BY s.firstname ASC")
                .getResultList();
    }

    @Override
    public List<Subscriber> searchPeople(Subscriber subscriber) {

        Address address = subscriber.getAddresses().get(0);

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Subscriber> criteria = builder.createQuery(Subscriber.class);
        Root<Subscriber> ipRoot = criteria.from(Subscriber.class);

        Join<Subscriber, Address> addressJoin = ipRoot.join("addresses", JoinType.LEFT);
        Join<House, Address> houseJoin = addressJoin.join("house", JoinType.LEFT);
        Join<Street, House> streetJoin = houseJoin.join("street", JoinType.INNER);

        List<Predicate> andPredicates = new ArrayList<Predicate>();
        if(subscriber.getIin() != null){
            andPredicates.add(builder.and(builder.like(ipRoot.<String>get("iin"), "" + subscriber.getIin() + "%")));
        }
        if(subscriber.getPassport() != null){
            andPredicates.add(builder.and(builder.like(ipRoot.<String>get("passport"), "" + subscriber.getPassport() + "%")));
        }
        if (subscriber.getLastname() != null) {
            andPredicates.add(builder.and(builder.like(ipRoot.<String>get("lastname"), "" + subscriber.getLastname() + "%")));
        }
        if (subscriber.getFirstname() != null) {
            andPredicates.add(builder.and(builder.like(ipRoot.<String>get("firstname"), "" + subscriber.getFirstname() + "%")));
        }
        if (subscriber.getMiddlename() != null) {
            andPredicates.add(builder.and(builder.like(ipRoot.<String>get("middlename"), "" + subscriber.getMiddlename() + "%")));
        }

        if (address != null) {
            if (address.getRegion() != null) {
                andPredicates.add(builder.and(builder.equal(addressJoin.get("region"), address.getRegion())));
            }
            if (address.getCity() != null) {
                andPredicates.add(builder.and(builder.equal(addressJoin.get("city"), address.getCity())));
            }
            if (address.getDistrict() != null) {
                andPredicates.add(builder.and(builder.equal(addressJoin.get("district"), address.getDistrict())));
            }
            if (address.getHouse() != null) {
                andPredicates.add(builder.and(builder.like(streetJoin.<String>get("name"), "%"+address.getHouse().getStreet().getName() + "%")));
            }
        }

        CriteriaQuery<Subscriber> select = criteria.select(ipRoot);

        Predicate predicate = builder.and(andPredicates.toArray(new Predicate[andPredicates.size()]));

        criteria.where(predicate);
        TypedQuery<Subscriber> typedQuery = em.createQuery(criteria);

        List<Subscriber> result = typedQuery.setMaxResults(20).getResultList();

        return result;

    }

    @Override
    public List<String> searchByLastname(String lastname) {

        return em.createQuery("SELECT DISTINCT CONCAT(s.lastname, ' ', s.firstname) FROM Subscriber s WHERE s.lastname LIKE '" + lastname + "%'")
                .setMaxResults(10)
                .getResultList();
    }

    @Override
    public List<String> searchByFirstname(String firstname) {
        return em.createQuery("SELECT DISTINCT s.firstname FROM Subscriber s WHERE s.firstname LIKE '" + firstname + "%'")
                .setMaxResults(10)
                .getResultList();
    }

    @Override
    public List<String> searchByMiddlename(String middlename) {
        return em.createQuery("SELECT DISTINCT s.middlename FROM Subscriber s WHERE s.middlename LIKE '" + middlename + "%'")
                .setMaxResults(10)
                .getResultList();
    }
}
