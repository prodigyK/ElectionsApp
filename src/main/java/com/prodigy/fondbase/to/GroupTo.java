package com.prodigy.fondbase.to;

import com.prodigy.fondbase.model.security.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class GroupTo extends BaseTo {

    @NotBlank
    @Size(min = 2, max = 30)
    protected String name;

    @NotBlank
    @Size(max = 100)
    private String description;

    private boolean enabled;

    private boolean visible;

    private boolean enabledInGroup = true;

    private List<User> users;

    private List<ModuleTo> modules = new ArrayList<>();

    public GroupTo() {
    }

    public GroupTo(Integer id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isEnabledInGroup() {
        return enabledInGroup;
    }

    public void setEnabledInGroup(boolean enabledInGroup) {
        this.enabledInGroup = enabledInGroup;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<ModuleTo> getModulesTo() {
        return modules;
    }

    public void setModulesTo(List<ModuleTo> modules) {
        this.modules = modules;
    }
}
