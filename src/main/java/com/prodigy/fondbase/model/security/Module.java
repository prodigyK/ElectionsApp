package com.prodigy.fondbase.model.security;

import com.prodigy.fondbase.model.AbstractBaseEntity;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bf_module")
public class Module extends AbstractBaseEntity implements GrantedAuthority {

    @Column(name = "name")
    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    @Column(name = "description")
    @Size(max = 255)
    private String description;

    @Column(name = "reference")
    @NotBlank
    private String reference;

    @Column(name = "enabled")
    private boolean enabled = true;

    @Column(name = "visible")
    private boolean visible = true;

    @Column(name = "permission")
    private String permission;

    @ManyToMany(mappedBy = "modules", fetch = FetchType.EAGER)
    private List<Group> groups = new ArrayList<>();

    public Module() {
    }

    public Module(Integer id, String name, String description, String reference) {
        super(id);
        this.name = name;
        this.description = description;
        this.reference = reference;
    }

    public Module(Integer id, String name, String description, String reference, String permission) {
        super(id);
        this.name = name;
        this.description = description;
        this.reference = reference;
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

    @Override
    public String getAuthority() {
        return getPermission();
    }
}
