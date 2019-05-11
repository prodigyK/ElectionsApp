package com.prodigy.fondbase.dao.logging;

import com.prodigy.fondbase.model.logging.LoggingType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional(readOnly = true)
public class LoggingTypeDaoImpl implements LoggingTypeDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public LoggingType get(int id) {
        return em.find(LoggingType.class, id);
    }

    @Override
    @Transactional
    public LoggingType save(LoggingType type) {
        if (type.isNew()) {
            em.persist(type);
            return type;
        } else {
            return em.merge(type);
        }
    }
}
