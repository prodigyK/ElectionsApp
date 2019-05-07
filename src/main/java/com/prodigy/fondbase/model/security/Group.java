package com.prodigy.fondbase.model.security;

import com.prodigy.fondbase.model.AbstractNamedEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bf_group")
public class Group extends AbstractNamedEntity{

    @NotBlank
    @Column(name = "description")
    @Size(max = 255)
    private String description;

    @Column(name = "enabled")
    private boolean enabled = true;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<User> users;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "bf_module_group",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "module_id")}
    )
    private List<Module> modules = new ArrayList<>();

    public Group() {
    }

    public Group(Integer id, String name, String description) {
        super(id, name);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
