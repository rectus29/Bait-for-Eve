package com.rectuscorp.evetool.entities.crest;

import com.rectuscorp.evetool.entities.core.GenericEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "attribute")
public class Attribute extends GenericEntity {

    @Column(nullable = false)
    private String name;
    @Column
    private String displayName;
    @Column(length = 65356)
    private String description;
    @Column
    private Long unit;
    @Column
    private Boolean highIsGood = true;
    @Column
    private Boolean stackable = true;
    @Column
    private long defaultValue = 0;
    @Column
    private Boolean published = true;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUnit() {
        return unit;
    }

    public void setUnit(Long unit) {
        this.unit = unit;
    }

    public Boolean getHighIsGood() {
        return highIsGood;
    }

    public void setHighIsGood(Boolean highIsGood) {
        this.highIsGood = highIsGood;
    }

    public Boolean getStackable() {
        return stackable;
    }

    public void setStackable(Boolean stackable) {
        this.stackable = stackable;
    }

    public long getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(long defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }
}
