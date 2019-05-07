package com.prodigy.fondbase.service.security;

import com.prodigy.fondbase.model.security.Group;
import com.prodigy.fondbase.model.security.Module;
import com.prodigy.fondbase.to.ModuleTo;

import java.util.List;

public interface GroupService {

    Group save(Group group);

    Group get(int id);

    List<Group> getAll();

    boolean delete(int id);

    void setModules(List<Module> modules, int id);

    List<Module> getModules(int id);

    void enable(int id, boolean enabled);

    List<ModuleTo> getAllToByGroupId(int groupId);

}
