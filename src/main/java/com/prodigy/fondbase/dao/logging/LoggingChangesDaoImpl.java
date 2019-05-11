package com.prodigy.fondbase.dao.logging;

import com.prodigy.fondbase.model.logging.LoggingChanges;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional(readOnly = true)
public class LoggingChangesDaoImpl implements LoggingChangesDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public LoggingChanges get(int id) {
        return em.find(LoggingChanges.class, id);
    }

    @Override
    @Transactional
    public LoggingChanges save(LoggingChanges changes) {
        if (changes.isNew()) {
            em.persist(changes);
            return changes;
        } else {
            return em.merge(changes);
        }
    }
}
