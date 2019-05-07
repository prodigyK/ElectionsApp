package com.prodigy.fondbase.dao.security;

import com.prodigy.fondbase.model.security.Group;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class GroupDaoImpl implements GroupDao {

    @PersistenceContext
    protected EntityManager em;

    @Override
    @Transactional
    public Group save(Group group) {
        if (group.isNew()) {
            em.persist(group);
            return group;
        } else {
            return em.merge(group);
        }
    }

    @Override
    public Group get(int id) {
        return em.find(Group.class, id);
    }

    @Override
    public List<Group> getAll() {
        return em.createQuery("SELECT g FROM Group g")
                .getResultList();
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createQuery("DELETE FROM Group g WHERE g.id=?1")
                .setParameter(1, id)
                .executeUpdate() != 0;

    }
}
