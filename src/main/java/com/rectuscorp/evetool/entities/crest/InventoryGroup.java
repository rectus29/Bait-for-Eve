package com.rectuscorp.evetool.entities.crest;

import com.rectuscorp.evetool.entities.core.GenericEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*-----------------------------------------------------*/
/* User: Rectus_29                 Date: 21/04/13 16:22 */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/
@Entity
@Table(name = "inventorygroups")
public class InventoryGroup extends GenericEntity {

	@ManyToOne
	private InventoryGroup parentGroup;
	@OneToMany(mappedBy = "parentGroup")
	private List<InventoryGroup> childrenMarketGroup = new ArrayList<>();
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "description", length = 65536)
	private String description;
	@Column
	private Boolean published = true;
	@OneToMany
	private List<Type> typeList = new ArrayList<>();

	public String getName() {
		return name;
	}

	public InventoryGroup setName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public InventoryGroup setDescription(String description) {
		this.description = description;
		return this;
	}

	public InventoryGroup getParentGroup() {
		return parentGroup;
	}

	public InventoryGroup setParentGroup(InventoryGroup parentGroup) {
		this.parentGroup = parentGroup;
		return this;
	}

	public Boolean isPublished() {
		return published;
	}

	public InventoryGroup setPublished(Boolean published) {
		this.published = published;
		return this;
	}

	public List<InventoryGroup> getChildrenMarketGroup() {
		return childrenMarketGroup;
	}

	public InventoryGroup setChildrenMarketGroup(List<InventoryGroup> childrenMarketGroup) {
		this.childrenMarketGroup = childrenMarketGroup;
		return this;
	}

	public InventoryGroup setTypeList(List<Type> typeList) {
		this.typeList = typeList;
		return this;
	}

	public List<Type> getTypeList() {
		return typeList;
	}
}
