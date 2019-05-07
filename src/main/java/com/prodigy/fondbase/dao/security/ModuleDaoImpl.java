package com.prodigy.fondbase.dao.security;

import com.prodigy.fondbase.model.security.Module;
import com.prodigy.fondbase.utils.exception.NotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class ModuleDaoImpl implements ModuleDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Module save(Module module) {
        if (module.isNew()) {
            em.persist(module);
            return module;
        } else {
            return em.merge(module);
        }
    }

    @Override
    public Module get(int id) {
        return em.find(Module.class, id);
    }

    @Override
    public List<Module> getAll() {
        return em.createQuery("SELECT m FROM Module m")
                .getResultList();
    }

    @Override
    @Transactional
    public boolean delete(int id){
        return em.createQuery("DELETE FROM Module m WHERE m.id=?1")
                .setParameter(1, id)
                .executeUpdate() != 0;
    }

    @Override
    public List<Module> getAllByGroupId(int id) {
        return null;
    }
}
