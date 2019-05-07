package com.prodigy.fondbase.to;

import com.prodigy.fondbase.model.security.Group;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class UserTo{

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private Group group;

}
