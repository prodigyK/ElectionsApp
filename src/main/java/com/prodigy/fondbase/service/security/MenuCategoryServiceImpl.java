package com.prodigy.fondbase.service.security;

import com.prodigy.fondbase.dao.security.MenuCategoryDao;
import com.prodigy.fondbase.model.security.MenuCategory;
import com.prodigy.fondbase.to.MenuTo;
import com.prodigy.fondbase.utils.MenuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MenuCategoryServiceImpl implements MenuCategoryService {

    private MenuCategoryDao dao;

    @Autowired
    public MenuCategoryServiceImpl(MenuCategoryDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public MenuCategory save(MenuCategory item) {
        return dao.save(item);
    }

    @Override
    public MenuCategory get(int id) {
        return dao.get(id);
    }

    @Override
    public List<MenuCategory> getAll() {
        return dao.getAll();
    }

    @Override
    public List<MenuTo> getAllTo() {
        List<MenuCategory> menuCategories = dao.getAll();
        List<MenuTo> menuTos = new ArrayList<>();
        for (MenuCategory item : menuCategories) {
            if(item.getParent() != null){
                continue;
            }
            menuTos.add(MenuUtil.createToFromEntity(item));
            for (MenuCategory itemChild : menuCategories){
                if(item.equals(itemChild.getParent())){
                    menuTos.add(MenuUtil.createToFromEntity(itemChild));
                }
            }
        }
        return menuTos;
    }

    @Override
    public List<MenuTo> getAllParentsTo() {

        List<MenuCategory> menuCategories = dao.getAllParents();
        List<MenuTo> menuTos = new ArrayList<>();

        for(MenuCategory item : menuCategories){
            if(item.getParent() == null){
                MenuTo to = MenuUtil.createToFromEntity(item);
                menuTos.add(to);
            }
        }
        return menuTos;
    }

    @Override
    public List<MenuCategory> getByParent(int parent) {
        return dao.getByParent(parent);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return dao.delete(id);
    }

    @Override
    public MenuCategory getByModuleId(int id) {
        return dao.getByModuleId(id);
    }

    @Override
    public MenuTo getTo(int id) {

        return MenuUtil.createToFromEntity(dao.get(id));
    }
}
