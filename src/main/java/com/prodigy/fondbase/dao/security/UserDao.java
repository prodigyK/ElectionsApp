package com.prodigy.fondbase.dao.security;

import com.prodigy.fondbase.model.security.User;

import java.util.List;

public interface UserDao {

    User save(User user);

    User get(int id);

    User getByLogin(String login);

    boolean delete(int id);

    List<User> getAll();
}
