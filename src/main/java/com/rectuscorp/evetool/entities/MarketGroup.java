package com.rectuscorp.evetool.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/*-----------------------------------------------------*/
/* User: Rectus_29                 Date: 21/04/13 16:22 */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/
//@Entity
//@Table(name = "Invmarketgroups")
public class MarketGroup {

    @Column(name = "marketGroupID")
    @Id
    private long marketGroupId;

    @ManyToOne
    @JoinColumn(name = "parentGroupID")
    private MarketGroup parentGroupID;

    //@OneToMany(mappedBy = "parentGroupID")
    //private List<MarketGroup> childrenMarketGroup = new ArrayList<MarketGroup>();

    @Column(name = "marketGroupName")
    private String marketGroupName;

    @Column(name = "description")
    private String description;

    @Column(name = "iconID")
    private Integer iconId;

    @Column(name = "hasTypes")
    private int hasTypes;




    public long getMarketGroupId() {
        return marketGroupId;
    }

    public void setMarketGroupId(int marketGroupId) {
        this.marketGroupId = marketGroupId;
    }

    public MarketGroup getParentGroupID() {
        return parentGroupID;
    }

    public void setParentGroupID(MarketGroup parentGroupId) {
        this.parentGroupID = parentGroupId;
    }

    public String getMarketGroupName() {
        return marketGroupName;
    }

    public void setMarketGroupName(String marketGroupName) {
        this.marketGroupName = marketGroupName;
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

    public int getHasTypes() {
        return hasTypes;
    }

    public void setHasTypes(int hasTypes) {
        this.hasTypes = hasTypes;
    }
}
