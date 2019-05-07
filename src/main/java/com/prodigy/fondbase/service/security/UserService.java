package com.prodigy.fondbase.service.security;

import com.prodigy.fondbase.model.security.User;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface UserService {


    User save(User user);

    User get(int id);

    boolean delete(int id);

//    @PreAuthorize("hasAuthority('MODULE_OPERATORS')")
    List<User> getAll();

    void enable(int id, boolean enabled);
}
