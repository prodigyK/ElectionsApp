package com.prodigy.fondbase.dao.logging;

import com.prodigy.fondbase.model.logging.LoggingMain;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional(readOnly = true)
public class LoggingMainDaoImpl implements LoggingMainDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public LoggingMain get(int id) {
        return em.find(LoggingMain.class, id);
    }

    @Override
    @Transactional
    public LoggingMain save(LoggingMain main) {
        if (main.isNew()) {
            em.persist(main);
            return main;
        } else {
            return em.merge(main);
        }
    }
}
