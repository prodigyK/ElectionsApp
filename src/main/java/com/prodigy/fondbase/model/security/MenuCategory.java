package com.prodigy.fondbase.model.security;

import com.prodigy.fondbase.model.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "bf_menu_category")
public class MenuCategory extends AbstractBaseEntity {

    @Column(name = "name")
    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @Column(name = "description")
    @Size(max = 255)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent")
    private MenuCategory parent;

    @Column(name = "menu_icon")
    private String menuIcon;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "module_id")
    private Module module;

    @Column(name = "ordered")
    private Integer ordered = 10000;

    public MenuCategory() {
    }

    public MenuCategory(Integer id, String name, String description, MenuCategory parent, Module module) {
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

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public Integer getOrdered() {
        return ordered;
    }

    public void setOrdered(Integer ordered) {
        this.ordered = ordered;
    }
}
