package com.rectuscorp.evetool.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: Rectus_29
 * Date: 20/04/13
 * Time: 15:01
 */
//@Entity
public class Chrraces {
    private int raceId;

    @Column(name = "raceID")
    @Id
    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    private String raceName;

    @Column(name = "raceName")
    @Basic
    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    private String description;

    @Column(name = "description")
    @Basic
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private int iconId;

    @Column(name = "iconID")
    @Basic
    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    private String shortDescription;

    @Column(name = "shortDescription")
    @Basic
    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chrraces chrraces = (Chrraces) o;

        if (iconId != chrraces.iconId) return false;
        if (raceId != chrraces.raceId) return false;
        if (description != null ? !description.equals(chrraces.description) : chrraces.description != null)
            return false;
        if (raceName != null ? !raceName.equals(chrraces.raceName) : chrraces.raceName != null) return false;
        if (shortDescription != null ? !shortDescription.equals(chrraces.shortDescription) : chrraces.shortDescription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = raceId;
        result = 31 * result + (raceName != null ? raceName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + iconId;
        result = 31 * result + (shortDescription != null ? shortDescription.hashCode() : 0);
        return result;
    }
}
