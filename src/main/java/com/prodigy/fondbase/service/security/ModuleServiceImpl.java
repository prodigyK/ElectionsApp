package com.prodigy.fondbase.service.security;

import com.prodigy.fondbase.dao.security.ModuleDao;
import com.prodigy.fondbase.model.security.Group;
import com.prodigy.fondbase.model.security.MenuCategory;
import com.prodigy.fondbase.model.security.Module;
import com.prodigy.fondbase.to.ModuleTo;
import com.prodigy.fondbase.utils.ModuleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private MenuCategoryService menuCategoryService;

    private ModuleDao dao;

    @Autowired
    public ModuleServiceImpl(ModuleDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public Module save(Module module) {
        return dao.save(module);
    }

    @Override
    public Module get(int id) {
        return dao.get(id);
    }

    @Override
    public List<Module> getAll() {
        return dao.getAll();
    }

    @Override
    @Transactional
    public void enable(int id, boolean enabled) {
        Module module = dao.get(id);
        module.setEnabled(enabled);
    }

    @Override
    @Transactional
    public void visible(int id, boolean visible) {
        Module module = dao.get(id);
        module.setVisible(visible);
    }

    @Override
    public MenuCategory getChildMenu(int id) {
        return menuCategoryService.getByModuleId(id);
    }

    @Override
    public MenuCategory getParentMenu(int id) {
        return getChildMenu(id).getParent();
    }

    @Override
    public List<ModuleTo> getAllTo() {
        List<Module> modules = dao.getAll();

        return createToFromList(modules);
    }

    @Override
    public List<ModuleTo> createToFromList(List<Module> modules){
        List<ModuleTo> modulesTo = new ArrayList<>();
        for(Module module : modules){
            int id = module.getId();
            MenuCategory childMenu = getChildMenu(id);
            String child = (childMenu != null) ? childMenu.getName() : "";
            String parent = (childMenu != null) ? childMenu.getParent().getName() : "";
            ModuleTo moduleTo = ModuleUtil.createToFromEntity(module);
            moduleTo.setMenuChild(child);
            moduleTo.setMenuParent(parent);
            modulesTo.add(moduleTo);
        }
        return modulesTo;
    }

    @Override
    public List<Module> createListFromTo(List<ModuleTo> modulesTo) {
        List<Module> modules = new ArrayList<>();
        for (ModuleTo moduleTo : modulesTo){
            Module module = ModuleUtil.createEntityFromTo(moduleTo);
            modules.add(module);
        }
        return modules;
    }
}
