package com.rectuscorp.evetool.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/*-----------------------------------------------------*/
/* User: Rectus_29                 Date: 21/04/13 16:22 */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/
@Entity
public class Invtypes {

    @Id
    @Column(name = "typeID")
    private long typeId;

    @ManyToOne
    @JoinColumn(name = "groupID", referencedColumnName = "groupID")
    private Invgroups groupID;

    @Column(name = "typeName")
    private String typeName;

    @Column(name = "description")
    private String description;

    @Column(name = "mass")
    private double mass;

    @Column(name = "volume")
    private double volume;

    @Column(name = "capacity")
    private double capacity;

    @Column(name = "portionSize")
    private int portionSize;

    @Column(name = "basePrice")
    private BigDecimal basePrice;

    @Column(name = "published")
    private boolean published;

    @ManyToOne
    @JoinColumn(name = "marketGroupID", referencedColumnName = "marketGroupID")
    private MarketGroup marketGroupID;

    @Column(name = "chanceOfDuplicating")
    private double chanceOfDuplicating;

    @ManyToOne
    @JoinColumn(name = "raceID", referencedColumnName = "raceID")
    private Chrraces raceID;


    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public Invgroups getGroupID() {
        return groupID;
    }

    public void setGroupID(Invgroups groupId) {
        this.groupID = groupId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public int getPortionSize() {
        return portionSize;
    }

    public void setPortionSize(int portionSize) {
        this.portionSize = portionSize;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public MarketGroup getMarketGroupID() {
        return marketGroupID;
    }

    public void setMarketGroupID(MarketGroup marketGroupId) {
        this.marketGroupID = marketGroupId;
    }

    public double getChanceOfDuplicating() {
        return chanceOfDuplicating;
    }

    public void setChanceOfDuplicating(double chanceOfDuplicating) {
        this.chanceOfDuplicating = chanceOfDuplicating;
    }

    public Chrraces getRaceID() {
        return raceID;
    }

    public void setRaceID(Chrraces raceId) {
        this.raceID = raceId;
    }

}
