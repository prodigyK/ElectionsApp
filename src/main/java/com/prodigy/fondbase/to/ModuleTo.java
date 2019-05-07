package com.prodigy.fondbase.to;

import com.prodigy.fondbase.model.security.MenuCategory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class ModuleTo extends BaseTo implements Serializable {
    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    @Size(max = 255)
    private String description;

    @NotBlank
    private String reference;

    private boolean enabled;

    private boolean visible;

    private boolean enabledInGroup = false;

    @NotBlank
    private String permission;

    private String menuParent;

    private String menuChild;

    public ModuleTo() {
    }

    public ModuleTo(Integer id, String name, String description, String reference,
                    boolean enabled, boolean visible, String permission) {
        super(id);
        this.name = name;
        this.description = description;
        this.reference = reference;
        this.enabled = enabled;
        this.visible = visible;
        this.permission = permission;
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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
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

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getMenuParent() {
        return menuParent;
    }

    public void setMenuParent(String menuParent) {
        this.menuParent = menuParent;
    }

    public String getMenuChild() {
        return menuChild;
    }

    public void setMenuChild(String menuChild) {
        this.menuChild = menuChild;
    }

    public boolean isEnabledInGroup() {
        return enabledInGroup;
    }

    public void setEnabledInGroup(boolean enabledInGroup) {
        this.enabledInGroup = enabledInGroup;
    }
}
