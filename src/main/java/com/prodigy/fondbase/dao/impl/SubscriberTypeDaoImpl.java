package com.prodigy.fondbase.dao.impl;

import com.prodigy.fondbase.dao.SubscriberTypeDao;
import com.prodigy.fondbase.model.SubscriberType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional(readOnly = true)
public class SubscriberTypeDaoImpl implements SubscriberTypeDao {


    @PersistenceContext
    private EntityManager em;

    @Override
    public SubscriberType get(int id) {
        try{
            return (SubscriberType) em.createQuery("SELECT s FROM SubscriberType s WHERE s.id=?1")
                    .setParameter(1, id)
                    .getSingleResult();
        }catch (Exception e){
            return null;
        }
    }
}
