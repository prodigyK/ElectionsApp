package com.prodigy.fondbase.dao.security;

import com.prodigy.fondbase.model.security.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    protected EntityManager em;

    @Override
    @Transactional
    public User save(User user) {
        if (user.isNew()) {
            em.persist(user);
            return user;
        } else {
            return em.merge(user);
        }
    }

    @Override
    public User get(int id) {
        return em.find(User.class, id);
    }

    @Override
    public User getByLogin(String login) {
        return (User) em.createQuery("SELECT u FROM User u WHERE u.login=:login")
                .setParameter("login", login)
                .getSingleResult();
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createQuery("DELETE FROM User u WHERE u.id=?1")
                .setParameter(1, id)
                .executeUpdate() != 0;
    }

    @Override
    public List<User> getAll() {
        return em.createQuery("SELECT u FROM User u")
                .getResultList();
    }
}
