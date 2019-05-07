package com.prodigy.fondbase.dao.security;

import com.prodigy.fondbase.model.security.Group;

import java.util.List;

public interface GroupDao {

    Group save(Group group);

    Group get(int id);

    List<Group> getAll();

    boolean delete(int id);
}
