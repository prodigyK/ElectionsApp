package com.prodigy.fondbase.dao.security;

import com.prodigy.fondbase.model.security.MenuCategory;

import java.util.List;

public interface MenuCategoryDao {

    MenuCategory save(MenuCategory item);

    MenuCategory get(int id);

    List<MenuCategory> getAll();

    List<MenuCategory> getAllParents();

    List<MenuCategory> getByParent(int parent);

    boolean delete(int id);

    List<MenuCategory> getByUserId(int id);

    MenuCategory getByModuleId(int id);

}
