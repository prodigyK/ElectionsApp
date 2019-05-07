package com.prodigy.fondbase.dao.security;

import com.prodigy.fondbase.model.security.Module;

import java.util.List;

public interface ModuleDao {

    Module save(Module module);

    Module get(int id);

    List<Module> getAll();

    boolean delete(int id);

    List<Module> getAllByGroupId(int id);

}
