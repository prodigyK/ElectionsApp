package com.prodigy.fondbase.dao.logging;

import com.prodigy.fondbase.model.logging.LoggingMain;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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

    @Override
    public List<LoggingMain> getAllForToday() {
        LocalDateTime from = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0));
        LocalDateTime to = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59));
        return em.createQuery("SELECT m FROM LoggingMain m WHERE m.timeChange BETWEEN ?1 AND ?2 ORDER BY m.timeChange DESC")
                .setParameter(1, from)
                .setParameter(2, to)
                .getResultList();
    }

    @Override
    public List<LoggingMain> getByUserBetweenDates(int userId, LocalDateTime from, LocalDateTime to) {
        return em.createQuery("SELECT log FROM LoggingMain log WHERE log.user.id=?1 AND log.timeChange BETWEEN ?2 AND ?3 ORDER BY log.timeChange DESC")
                .setParameter(1, userId)
                .setParameter(2, from)
                .setParameter(3, to)
                .getResultList();
    }

    @Override
    public List<LoggingMain> getBetweenDates(LocalDateTime from, LocalDateTime to) {
        return em.createQuery("SELECT log FROM LoggingMain log WHERE log.timeChange BETWEEN ?2 AND ?3 ORDER BY log.timeChange DESC")
                .setParameter(2, from)
                .setParameter(3, to)
                .getResultList();
    }
}
