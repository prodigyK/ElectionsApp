package com.prodigy.fondbase.utils;

import com.prodigy.fondbase.model.security.MenuCategory;
import com.prodigy.fondbase.to.MenuTo;

public class MenuUtil {

    public static MenuTo createToFromEntity(MenuCategory menu){

        return new MenuTo(menu.getId(), menu.getName(), menu.getDescription(), menu.getParent(), menu.getModule());
    }

    public static MenuCategory createEntityFromTo(MenuTo menuTo){

        return new MenuCategory(menuTo.getId(), menuTo.getName(), menuTo.getDescription(),
                menuTo.getParent().getId()!=null ? menuTo.getParent() : null,
                menuTo.getModule().getId()!=null ? menuTo.getModule() : null);
    }
}
