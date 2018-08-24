package com.example.demo;


import javax.persistence.*;
import java.util.Collection;

@Entity
public class AppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String appRole;

    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private Collection<AppUser> appUsers;

    public AppRole(String role) {
        this.appRole = role;
    }

    public AppRole() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return appRole;
    }

    public void setRole(String role) {
        this.appRole = role;
    }

    public Collection<AppUser> getAppUsers() {
        return appUsers;
    }

    public void setAppUsers(Collection<AppUser> appUsers) {
        this.appUsers = appUsers;
    }
}
