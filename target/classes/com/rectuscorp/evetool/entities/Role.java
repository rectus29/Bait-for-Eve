package com.rectuscorp.evetool.entities;

/**
 * User: Olivier
 * Date: 27 mai 2009
 */

import org.apache.log4j.Logger;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@SuppressWarnings("serial")
@Entity
@Table(name = "role")
public class Role implements DomainObject, Comparable<Role> {

    private static final Logger log = Logger.getLogger(Role.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    //@Basic(optional=false)
    @Column(length = 100)
    //@Index(name="idx_roles_name")
    private String name;

    private String description;

    @Column
    private int weight = 100;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    private Set<Permission> permissions;

    @OneToMany(mappedBy = "role")
    private Set<User> users = new HashSet<User>();


    public Role() {

        this.permissions = new HashSet<Permission>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
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

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getContainerPermissions() {
        List<Permission> out = new ArrayList<Permission>();
        for (Permission temp : this.permissions) {
            if (temp.getCodeString().startsWith("container:"))
                out.add(temp);
        }
        return out;
    }

    public List<Permission> getActivityPermissions() {
        List<Permission> out = new ArrayList<Permission>();
        for (Permission temp : this.permissions) {
            if (temp.getCodeString().startsWith("activity:"))
                out.add(temp);
        }
        return out;
    }

    public void addPermission(Permission perm) {
        if (!this.permissions.contains(perm))
            this.permissions.add(perm);
    }

    public void removePermission(Permission perm) {
        this.permissions.remove(perm);
    }

    @Override
    public boolean equals(Object obj) {
        return this.getName().equals(((Role) obj).getName());
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public int compareTo(Role o) {
        return this.getId().compareTo(o.getId());
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
