package com.prodigy.fondbase.dao.impl;

import com.prodigy.fondbase.dao.EntityDao;
import com.prodigy.fondbase.model.AbstractBaseEntity;
import com.prodigy.fondbase.model.AbstractNamedEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Transactional(readOnly = true)
public class EntityDaoImpl implements EntityDao {

    private static final Logger log = LoggerFactory.getLogger(EntityDaoImpl.class);

    @PersistenceContext
    protected EntityManager em;

    @Override
    @Transactional
    public AbstractBaseEntity save(AbstractBaseEntity entity) {
        log.debug("save entity to db {}", entity.toString());

        if (entity.isNew()) {
            em.persist(entity);
            return entity;
        } else {
            return em.merge(entity);
        }
    }

    @Override
    @Transactional
    public boolean delete(Class clazz, int id) {
        log.debug("delete entity with id={}", id);
        log.debug("clazz: {}", clazz.getName());

        String query = "DELETE FROM " + clazz.getSimpleName() + " entity WHERE entity.id=:id";
        return em.createQuery(query)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public AbstractBaseEntity get(Class clazz, int id) {
        log.debug("get entity with id={}", id);
        log.debug("clazz: {}", clazz.getName());

        String query = "SELECT entity FROM " + clazz.getSimpleName() + " entity WHERE entity.id=:id";
        return (AbstractBaseEntity) em.createQuery(query)
                                        .setParameter("id", id)
                                        .getSingleResult();
    }

    @Override
    public List<AbstractBaseEntity> getAll(Class clazz) {
        log.debug("get all entities for {}", clazz.getName());

        String query = "SELECT entity FROM " + clazz.getSimpleName() + " entity";
        return em.createQuery(query)
                .getResultList();
    }

    @Override
    public AbstractNamedEntity getByName(Class clazz, String name) {
        log.debug("get entity by name {}", name);
        log.debug("clazz: {}", clazz.getName());

        String query = "SELECT entity FROM " + clazz.getSimpleName() + " entity WHERE entity.name=:name";
        List<AbstractNamedEntity> results = (List<AbstractNamedEntity>) em.createQuery(query)
                .setParameter("name", name)
                .getResultList();

        return (results.size() == 0) ? null : results.get(0);

    }
}
