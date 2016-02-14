package com.rectuscorp.evetool.entities.crest;

import com.rectuscorp.evetool.entities.core.GenericEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorie")
public class Category extends GenericEntity {

    @Column(nullable = false, length = 65356)
    private String name;
    @Column
    private Boolean published = true;
    @OneToMany(mappedBy = "categorie")
    private List<Group> groupList = new ArrayList<Group>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Boolean getPublished() {
        return published;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }
}
