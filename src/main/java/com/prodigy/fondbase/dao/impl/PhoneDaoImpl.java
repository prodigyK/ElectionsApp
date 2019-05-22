package com.prodigy.fondbase.dao.impl;

import com.prodigy.fondbase.dao.PhoneDao;
import com.prodigy.fondbase.model.Phone;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class PhoneDaoImpl extends EntityDaoImpl implements PhoneDao {

    @Override
    public List<Phone> getAllBySubscriberId(int subscriberId) {
        return em.createQuery("SELECT p FROM Phone p WHERE p.subscriber.id=?1")
                .setParameter(1, subscriberId)
                .getResultList();
    }

    @Override
    public Phone getBySubscriberId(int subscriberId) {
        try {
            return (Phone) em.createQuery("SELECT p FROM Phone p WHERE p.subscriber.id=?1")
                    .setParameter(1, subscriberId)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
