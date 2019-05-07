package com.prodigy.fondbase.dao.security;

import com.prodigy.fondbase.model.security.MenuCategory;
import com.prodigy.fondbase.utils.exception.NotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class MenuCategoryDaoImpl implements MenuCategoryDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public MenuCategory save(MenuCategory item) {
        if (item.isNew()) {
            em.persist(item);
            return item;
        } else {
            return em.merge(item);
        }
    }

    @Override
    public MenuCategory get(int id) {
        return em.find(MenuCategory.class, id);
    }

    @Override
    public List<MenuCategory> getAll() {
        return em.createQuery("SELECT m FROM MenuCategory m ORDER BY m.ordered")
                .getResultList();
    }

    @Override
    public List<MenuCategory> getAllParents() {
        return em.createQuery("SELECT m FROM MenuCategory m WHERE m.parent is NULL ORDER BY m.ordered")
                .getResultList();
    }

    @Override
    public List<MenuCategory> getByParent(int parent) {
        return em.createQuery("SELECT m FROM MenuCategory m WHERE m.parent=:parent")
                .setParameter("parent", parent)
                .getResultList();
    }

    @Override
    public boolean delete(int id) {
        return em.createQuery("DELETE FROM MenuCategory m WHERE m.id=?1")
                .setParameter(1, id)
                .executeUpdate() != 0;
    }

    @Override
    public List<MenuCategory> getByUserId(int id) {
        return null;
    }

    @Override
    public MenuCategory getByModuleId(int id) {
        try{
            return (MenuCategory) em.createQuery("SELECT m FROM MenuCategory m WHERE m.module.id=?1")
                    .setParameter(1, id)
                    .getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }
}
