package com.prodigy.fondbase.dao.commission;


import com.prodigy.fondbase.model.commission.ElectionCandidate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class ElectionCandidateDaoImpl implements ElectionCandidateDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public ElectionCandidate save(ElectionCandidate candidate) {
        if (candidate.isNew()) {
            em.persist(candidate);
            return candidate;
        } else {
            return em.merge(candidate);
        }

    }

    @Override
    public List<ElectionCandidate> getAll() {
        return em.createQuery("SELECT e FROM ElectionCandidate e ORDER BY e.name ASC")
                .getResultList();
    }

    @Override
    public ElectionCandidate get(int id) {
        return em.find(ElectionCandidate.class, id);
    }

    @Override
    public List<ElectionCandidate> getTechCandidates(int id) {
        return em.createQuery("SELECT e FROM ElectionCandidate e WHERE e.top=false AND e.id!=?1 AND ((e.ourTechCand=true AND e.techOfParent=?1) OR e.ourTechCand=false)")
                .setParameter(1, id)
                .getResultList();
    }

    @Override
    @Transactional
    public boolean deleteAllByElection(int electionId) {
        return em.createQuery("DELETE FROM ElectionCandidate ec WHERE ec.election.id=?1")
                .setParameter(1, electionId)
                .executeUpdate()!=0;
    }
}
