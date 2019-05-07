package com.prodigy.fondbase.dao.commission;

import com.prodigy.fondbase.model.commission.Election;
import com.prodigy.fondbase.model.security.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class ElectionDaoImpl implements ElectionDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Election save(Election election) {
        if (election.isNew()) {
            em.persist(election);
            return election;
        } else {
            return em.merge(election);
        }
    }

    @Override
    public Election get(int id) {
        return em.find(Election.class, id);
    }

    @Override
    public List<Election> getAll() {
        return em.createQuery("SELECT e FROM Election e")
                .getResultList();
    }
}
