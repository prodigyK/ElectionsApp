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

    private final int MAX_RESULT = 40;

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

/*
    @Override
    public List<Subscriber> searchPeople(Subscriber subscriber, List list) {
        return null;
    }
*/

    @Override
    public List<Subscriber> searchPeople(Subscriber subscriber, String street) {

        Address address = subscriber.getAddresses().get(0);

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Subscriber> criteria = builder.createQuery(Subscriber.class);
        Root<Subscriber> ipRoot = criteria.from(Subscriber.class);

        Join<Subscriber, Address> addressJoin = ipRoot.join("addresses", JoinType.LEFT);
        Join<House, Address> houseJoin = addressJoin.join("house", JoinType.LEFT);
        Join<Street, House> streetJoin = houseJoin.join("street", JoinType.LEFT);

        List<Predicate> andPredicates = new ArrayList<Predicate>();
        List<Predicate> orPredicates = new ArrayList<Predicate>();
        if (subscriber.getIin() != null) {
            andPredicates.add(builder.and(builder.like(ipRoot.<String>get("iin"), "" + subscriber.getIin() + "%")));
        }
        if (subscriber.getPassport() != null) {
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

                if (street != null && !"".equals(street)) {
//                    orPredicates.add(builder.or(builder.equal(houseJoin.get("street"), street)));
                    orPredicates.add(builder.and(builder.like(streetJoin.<String>get("name"), street + "%")));
                }
//                andPredicates.add(builder.and(builder.like(streetJoin.<String>get("name"), "%" + address.getHouse().getStreet().getName() + "%")));
                if (address.getHouse().getHouseNumber() != null && !"".equals(address.getHouse().getHouseNumber())) {
                    andPredicates.add(builder.and(builder.like(houseJoin.<String>get("houseNumber"), address.getHouse().getHouseNumber())));
                }
            }

        }

        CriteriaQuery<Subscriber> select = criteria.select(ipRoot);

        Predicate and = builder.and(andPredicates.toArray(new Predicate[andPredicates.size()]));
        Predicate or = builder.and(orPredicates.toArray(new Predicate[orPredicates.size()]));

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(and);
        if (or.getExpressions().size() > 0) {
            predicates.add(or);
        }

        criteria.where(predicates.toArray(new Predicate[predicates.size()]));
        criteria.orderBy(builder.asc(ipRoot.get("lastname")), builder.asc(ipRoot.get("firstname")));

        TypedQuery<Subscriber> typedQuery = em.createQuery(criteria);

        List<Subscriber> result = typedQuery.setMaxResults(MAX_RESULT).getResultList();

        return result;

    }

    @Override
    public List<String> searchByLastname(String lastname) {
        lastname = lastname.replace("'", "''");
        return em.createQuery("SELECT DISTINCT CONCAT(s.lastname, ' ', s.firstname) FROM Subscriber s WHERE s.lastname LIKE '" + lastname + "%'")
                .setMaxResults(10)
                .getResultList();
    }

    @Override
    public List<String> searchByFirstname(String firstname) {
        firstname = firstname.replace("'", "''");
        return em.createQuery("SELECT DISTINCT s.firstname FROM Subscriber s WHERE s.firstname LIKE '" + firstname + "%'")
                .setMaxResults(10)
                .getResultList();
    }

    @Override
    public List<String> searchByMiddlename(String middlename) {
        middlename = middlename.replace("'", "''");
        return em.createQuery("SELECT DISTINCT s.middlename FROM Subscriber s WHERE s.middlename LIKE '" + middlename + "%'")
                .setMaxResults(10)
                .getResultList();
    }


}
