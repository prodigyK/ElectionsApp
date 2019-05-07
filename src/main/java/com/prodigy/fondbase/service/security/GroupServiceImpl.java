package com.prodigy.fondbase.service.security;

import com.prodigy.fondbase.dao.security.GroupDao;
import com.prodigy.fondbase.model.security.Group;
import com.prodigy.fondbase.model.security.Module;
import com.prodigy.fondbase.to.ModuleTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GroupServiceImpl implements GroupService {

    private GroupDao dao;

    @Autowired
    private ModuleService moduleService;

    @Autowired
    public GroupServiceImpl(GroupDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public Group save(Group group) {
        if(group.isNew()){
            List<Module> modules = group.getModules();
            group.setModules(null);
            Group created = dao.save(group);
            created.setModules(modules);
            return dao.save(created);
        }
        return dao.save(group);
    }

    @Override
    public Group get(int id) {
        return dao.get(id);
    }

    @Override
    public List<Group> getAll() {
        return dao.getAll();
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return dao.delete(id);
    }

    @Override
    @Transactional
    public void setModules(List<Module> modules, int id) {
        get(id).setModules(modules);
    }

    @Override
    public List<Module> getModules(int id) {
        return get(id).getModules();
    }

    @Override
    @Transactional
    public void enable(int id, boolean enabled) {
        Group group = dao.get(id);
        group.setEnabled(enabled);
    }

    @Override
    public List<ModuleTo> getAllToByGroupId(int groupId) {

        Group group = dao.get(groupId);
        List<ModuleTo> allModules = moduleService.getAllTo();
        List<Module> modules = group.getModules();
        for(Module module : modules){
            for(ModuleTo moduleTo : allModules){
                if(module.getId() == moduleTo.getId()){
                    moduleTo.setEnabledInGroup(true);
                    break;
                }
            }

        }

        return allModules;
    }
}
