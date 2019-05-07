package com.prodigy.fondbase.dao;

import com.prodigy.fondbase.model.AbstractBaseEntity;
import com.prodigy.fondbase.model.AbstractNamedEntity;

import java.util.List;

public interface EntityDao<T extends AbstractBaseEntity>{

    T save(T entity);

    boolean delete(Class<T> clazz, int id);

    T get(Class<T> clazz, int id);

    List<T> getAll(Class<T> clazz);

    <T extends AbstractNamedEntity> T getByName(Class<T> clazz, String name);
}
