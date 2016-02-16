package com.rectuscorp.evetool.entities.crest;

import com.rectuscorp.evetool.entities.core.GenericEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "effect")
public class Effect extends GenericEntity {
    @Column(nullable = false)
    private String name;
    @Column(length = 65536)
    private String description;
    @Column
    private String displayName;
    @Column
    private Long preExpression;
    @Column
    private Long postExpression;
    @Column
    private Boolean rangeChance = false;
    @Column
    private Long effectCategory;
    @Column
    private Boolean electronicChance = false;
    @Column
    private Boolean isWarpSafe = false;
    @Column
    private Boolean disallowAutoRepeat = false;
    @Column
    private Boolean isOffensive = false;
    @Column
    private Boolean isAssistance = false;
    @Column
    private Boolean published = true;

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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Long getPreExpression() {
        return preExpression;
    }

    public void setPreExpression(Long preExpression) {
        this.preExpression = preExpression;
    }

    public Long getPostExpression() {
        return postExpression;
    }

    public void setPostExpression(Long postExpression) {
        this.postExpression = postExpression;
    }

    public Boolean getRangeChance() {
        return rangeChance;
    }

    public void setRangeChance(Boolean rangeChance) {
        this.rangeChance = rangeChance;
    }

    public Long getEffectCategory() {
        return effectCategory;
    }

    public void setEffectCategory(Long effectCategory) {
        this.effectCategory = effectCategory;
    }

    public Boolean getElectronicChance() {
        return electronicChance;
    }

    public void setElectronicChance(Boolean electronicChance) {
        this.electronicChance = electronicChance;
    }

    public Boolean getIsWarpSafe() {
        return isWarpSafe;
    }

    public void setIsWarpSafe(Boolean isWarpSafe) {
        this.isWarpSafe = isWarpSafe;
    }

    public Boolean getDisallowAutoRepeat() {
        return disallowAutoRepeat;
    }

    public void setDisallowAutoRepeat(Boolean disallowAutoRepeat) {
        this.disallowAutoRepeat = disallowAutoRepeat;
    }

    public Boolean getIsOffensive() {
        return isOffensive;
    }

    public void setIsOffensive(Boolean isOffensive) {
        this.isOffensive = isOffensive;
    }

    public Boolean getIsAssistance() {
        return isAssistance;
    }

    public void setIsAssistance(Boolean isAssistance) {
        this.isAssistance = isAssistance;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }
}
