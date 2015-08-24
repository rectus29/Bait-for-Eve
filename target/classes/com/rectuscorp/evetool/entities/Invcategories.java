package com.rectuscorp.evetool.entities;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: Rectus_29
 * Date: 20/04/13
 * Time: 15:01
 * To change this template use File | Settings | File Templates.
 */
//@Entity
public class Invcategories {

    @Column(name = "categoryID")
        @Id
    private long categoryId;


    @Column(name = "categoryName")
    private String categoryName;

    @Column(name = "description")
    private String description;


    @Column(name = "iconID")
    private Integer iconId;

    @Column(name = "published")
    private boolean published;

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
}
