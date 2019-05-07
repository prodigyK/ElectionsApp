package com.prodigy.fondbase.service.security;

import com.prodigy.fondbase.model.security.MenuCategory;
import com.prodigy.fondbase.model.security.Module;
import com.prodigy.fondbase.to.ModuleTo;

import java.util.List;

public interface ModuleService {

    Module save(Module module);

    Module get(int id);

    List<Module> getAll();

    void enable(int id, boolean enabled);

    void visible(int id, boolean visible);

    MenuCategory getChildMenu(int id);

    MenuCategory getParentMenu(int id);

    List<ModuleTo> getAllTo();

    List<ModuleTo> createToFromList(List<Module> modules);

    List<Module> createListFromTo(List<ModuleTo> modulesTo);


}
