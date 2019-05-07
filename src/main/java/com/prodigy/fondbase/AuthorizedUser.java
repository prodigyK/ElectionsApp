package com.prodigy.fondbase;

import com.prodigy.fondbase.model.security.Group;
import com.prodigy.fondbase.model.security.MenuCategory;
import com.prodigy.fondbase.model.security.Module;
import com.prodigy.fondbase.model.security.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    private User user;

    private Group group;

    private List<Module> modules;

    private List<MenuCategory> menu;

    public AuthorizedUser(User user) {
        super(user.getLogin(), user.getPassword(), user.isEnabled(), true, true, true, user.getGroup().getModules());
        this.user = user;
        this.group = user.getGroup();
        this.modules = group.getModules();
    }

    public AuthorizedUser(User user, List<Module> modules, List<MenuCategory> menu) {
        super(user.getLogin(), user.getPassword(), user.isEnabled(), true, true, true, modules);
        this.user = user;
        this.group = user.getGroup();
        this.modules = modules;
        this.menu = menu;
    }

    public User getUser() {
        return user;
    }

    public List<Module> getModules() {
        return modules;
    }

    public Group getGroup() {
        return group;
    }

    public List<MenuCategory> getMenu() {
        return menu;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (Module privilege : modules) {
            authorities.add(new SimpleGrantedAuthority(privilege.getPermission()));
        }
        return authorities;
//        return super.getAuthorities();
    }


}