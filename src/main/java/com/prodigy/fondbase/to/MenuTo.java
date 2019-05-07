package com.prodigy.fondbase.to;

import com.prodigy.fondbase.model.security.MenuCategory;
import com.prodigy.fondbase.model.security.Module;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class MenuTo extends BaseTo {

    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @Size(max = 255)
    private String description;

    private MenuCategory parent;

    private Module module;

    public MenuTo() {
    }

    public MenuTo(Integer id, String name, String description, MenuCategory parent, Module module) {
        super(id);
        this.name = name;
        this.description = description;
        this.parent = parent;
        this.module = module;
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

    public MenuCategory getParent() {
        return parent;
    }

    public void setParent(MenuCategory parent) {
        this.parent = parent;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }
}
