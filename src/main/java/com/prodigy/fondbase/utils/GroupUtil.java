package com.prodigy.fondbase.utils;

import com.prodigy.fondbase.model.security.Group;
import com.prodigy.fondbase.model.security.Module;
import com.prodigy.fondbase.to.GroupTo;
import com.prodigy.fondbase.to.ModuleTo;

import java.util.ArrayList;
import java.util.List;

public class GroupUtil {

    public static GroupTo createToFromEntity(Group group){

        GroupTo groupTo = new GroupTo(group.getId(), group.getName(), group.getDescription());

        List<ModuleTo> moduleTos;

        return null;
    }

    public static Group createEntityFromTo(GroupTo groupTo){

        Group group = new Group(groupTo.getId(), groupTo.getName(), groupTo.getDescription());
        List<ModuleTo> modulesTo = groupTo.getModulesTo();
        List<Module> modules = new ArrayList<>();

        if(modulesTo != null){
            for(ModuleTo moduleTo : modulesTo){
                Module module = ModuleUtil.createEntityFromTo(moduleTo);
                modules.add(module);
            }
        }
        group.setModules(modules);

        return group;
    }
}
