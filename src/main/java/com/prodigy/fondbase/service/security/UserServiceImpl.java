package com.prodigy.fondbase.service.security;

import com.prodigy.fondbase.AuthorizedUser;
import com.prodigy.fondbase.dao.security.UserDao;
import com.prodigy.fondbase.model.security.MenuCategory;
import com.prodigy.fondbase.model.security.Module;
import com.prodigy.fondbase.model.security.User;
import com.prodigy.fondbase.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDao dao;

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private MenuCategoryService menuCategoryService;

    @Autowired
    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public User save(User user) {
        return dao.save(user);
    }

    @Override
    public User get(int id) {
        return dao.get(id);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return dao.delete(id);
    }

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }

    @Override
    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
    }

    @Override
    @Transactional
    public AuthorizedUser loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = dao.getByLogin(login.toLowerCase());
        if (user == null || !user.isEnabled() || !user.getGroup().isEnabled()) {
            log.error("User " + login + " is not found");
            throw new UsernameNotFoundException("User " + login + " is not found");
        }
        log.info("User " + login + " is found and trying to authorize");

        List<MenuCategory> menu = menuCategoryService.getAll();

        List<Module> enabledModules = new ArrayList<>();
        if (user.getGroup().getId() == 1000) {
            enabledModules = moduleService.getAll();
        } else {
            List<Module> modules = user.getGroup().getModules();
            for (Module module : modules) {
                if (module.isEnabled()) {
                    enabledModules.add(module);
                }
            }
            Set<MenuCategory> tempMenu = new HashSet<>();
            for(Module module : enabledModules){
                if(!module.isVisible()) continue;
                for(MenuCategory menuCategory : menu){
                    if(menuCategory.getParent() == null) continue;
                    if(menuCategory.getModule().getId().equals(module.getId())){
                        tempMenu.add(menuCategory);
                        tempMenu.add(menuCategory.getParent());
                        break;
                    }
                }
            }
            menu = new ArrayList<MenuCategory>(tempMenu);
        }

        user.setLastEnter(UserUtils.getCurrentDateTime());
        user.setIpAddress(UserUtils.getIp());


        return new AuthorizedUser(user, enabledModules, menu);
    }
}

