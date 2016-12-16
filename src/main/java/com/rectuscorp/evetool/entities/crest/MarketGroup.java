package com.rectuscorp.evetool.entities.crest;

import com.rectuscorp.evetool.entities.core.GenericEntity;
import com.rectuscorp.evetool.enums.State;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*-----------------------------------------------------*/
/* User: Rectus_29                 Date: 21/04/13 16:22 */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/
@Entity
@Table(name = "marketgroups")
public class MarketGroup extends GenericEntity {

	@ManyToOne
	private MarketGroup parentGroup;
	@OneToMany(mappedBy = "parentGroup")
	private List<MarketGroup> childrenMarketGroup = new ArrayList<>();
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "description", length = 65536)
	private String description;
	@Column
	private Boolean published = true;
	@OneToMany
	private List<Type> typeList = new ArrayList<>();
	@Column
	private State state = State.ENABLE;

	public String getName() {
		return name;
	}

	public MarketGroup setName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public MarketGroup setDescription(String description) {
		this.description = description;
		return this;
	}

	public MarketGroup getParentGroup() {
		return parentGroup;
	}

	public MarketGroup setParentGroup(MarketGroup parentGroup) {
		this.parentGroup = parentGroup;
		return this;
	}

	public Boolean isPublished() {
		return published;
	}

	public MarketGroup setPublished(Boolean published) {
		this.published = published;
		return this;
	}

	public List<MarketGroup> getChildrenMarketGroup() {
		return childrenMarketGroup;
	}

	public MarketGroup setChildrenMarketGroup(List<MarketGroup> childrenMarketGroup) {
		this.childrenMarketGroup = childrenMarketGroup;
		return this;
	}

	public MarketGroup setTypeList(List<Type> typeList) {
		this.typeList = typeList;
		return this;
	}

	public List<Type> getTypeList() {
		return typeList;
	}

	public State getState() {
		return state;
	}

	public MarketGroup setState(State state) {
		this.state = state;
		return this;
	}
}
