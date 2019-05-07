package com.prodigy.fondbase.service.security;

import com.prodigy.fondbase.model.security.MenuCategory;
import com.prodigy.fondbase.to.MenuTo;

import java.util.List;

public interface MenuCategoryService {

    MenuCategory save(MenuCategory item);

    MenuCategory get(int id);

    MenuTo getTo(int id);

    List<MenuCategory> getAll();

    List<MenuTo> getAllTo();

    List<MenuTo> getAllParentsTo();

    List<MenuCategory> getByParent(int parent);

    boolean delete(int id);

    MenuCategory getByModuleId(int id);

}
