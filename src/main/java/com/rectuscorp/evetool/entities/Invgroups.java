package com.rectuscorp.evetool.entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*-----------------------------------------------------*/
/* User: Rectus_29                Date: 21/04/13 16:22 */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/
//@Entity
public class Invgroups {


    @Column(name = "groupID")
    @Id
    private long groupId;

    @ManyToOne
    @JoinColumn(name = "categoryID", referencedColumnName = "categoryID")
    private Invcategories categoryID;


    @Column(name = "groupName")
    private String groupName;


    @Column(name = "description")
    private String description;


    @Column(name = "iconID", nullable = true)
    private Integer iconId;


    @Column(name = "useBasePrice")
    private boolean useBasePrice;


    @Column(name = "allowManufacture")
    private boolean allowManufacture;


    @Column(name = "allowRecycler")
    private boolean allowRecycler;


    @Column(name = "anchored")
    private boolean anchored;


    @Column(name = "anchorable")
    private boolean anchorable;


    @Column(name = "fittableNonSingleton")
    private boolean fittableNonSingleton;


    @Column(name = "published")
    private boolean published;

    @OneToMany(mappedBy = "groupID")
    private List<Invtypes> invtypesList =  new ArrayList<Invtypes>();

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public Invcategories getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Invcategories categoryId) {
        this.categoryID = categoryId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    public boolean isUseBasePrice() {
        return useBasePrice;
    }

    public void setUseBasePrice(boolean useBasePrice) {
        this.useBasePrice = useBasePrice;
    }

    public boolean isAllowManufacture() {
        return allowManufacture;
    }

    public void setAllowManufacture(boolean allowManufacture) {
        this.allowManufacture = allowManufacture;
    }

    public boolean isAllowRecycler() {
        return allowRecycler;
    }

    public void setAllowRecycler(boolean allowRecycler) {
        this.allowRecycler = allowRecycler;
    }

    public boolean isAnchored() {
        return anchored;
    }

    public void setAnchored(boolean anchored) {
        this.anchored = anchored;
    }

    public boolean isAnchorable() {
        return anchorable;
    }

    public void setAnchorable(boolean anchorable) {
        this.anchorable = anchorable;
    }

    public boolean isFittableNonSingleton() {
        return fittableNonSingleton;
    }

    public void setFittableNonSingleton(boolean fittableNonSingleton) {
        this.fittableNonSingleton = fittableNonSingleton;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
}
