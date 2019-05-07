package com.prodigy.fondbase.model.security;

import com.prodigy.fondbase.model.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "bf_user")
public class User extends AbstractBaseEntity {

    @Column(name = "name")
    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @Column(name = "ident_number")
    @NotBlank
    @Size(max = 10)
    private String identNumber;

    @Column(name = "passport")
    @NotBlank
    @Size(max = 8)
    private String passport;

    @Column(name = "login", nullable = false)
    @NotBlank
    @Size(min = 3, max = 32)
    private String login;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 3, max = 32)
    private String password;

    @Column(name = "enabled")
    private boolean enabled = true;

    @Column(name = "last_enter")
    private String lastEnter;

    @Column(name = "ip_address")
    private String ipAddress;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    public User() {
    }

    public User(Integer id, String name, String identNumber, String passport, String login, String password, Group group) {
        super(id);
        this.name = name;
        this.identNumber = identNumber;
        this.passport = passport;
        this.login = login;
        this.password = password;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getLastEnter() {
        return lastEnter;
    }

    public void setLastEnter(String lastEnter) {
        this.lastEnter = lastEnter;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getIdentNumber() {
        return identNumber;
    }

    public void setIdentNumber(String identNumber) {
        this.identNumber = identNumber;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }
}
