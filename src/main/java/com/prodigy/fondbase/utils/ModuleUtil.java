package com.prodigy.fondbase.utils;

import com.prodigy.fondbase.model.security.MenuCategory;
import com.prodigy.fondbase.model.security.Module;
import com.prodigy.fondbase.to.ModuleTo;

import java.util.List;

public class ModuleUtil {

    public static ModuleTo createToFromEntity(Module module){
        return new ModuleTo(module.getId(), module.getName(), module.getDescription(),
                            module.getReference(), module.isEnabled(), module.isVisible(),
                            module.getPermission());
    }

    public static Module createEntityFromTo(ModuleTo moduleTo){
        return new Module(moduleTo.getId(), moduleTo.getName(), moduleTo.getDescription(),
                            moduleTo.getReference(), moduleTo.getPermission());
    }

}
