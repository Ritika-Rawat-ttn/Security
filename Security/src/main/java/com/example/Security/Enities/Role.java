package com.example.Security.Enities;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role implements GrantedAuthority {

    private static final long serialVersionUID=1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(mappedBy="roles")
    private Set<User> user;

    @Override
    public String getAuthority() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
