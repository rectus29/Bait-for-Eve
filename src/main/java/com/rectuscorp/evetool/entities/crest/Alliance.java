package com.rectuscorp.evetool.entities.crest;

import com.rectuscorp.evetool.entities.core.GenericEntity;
import com.rectuscorp.evetool.enums.State;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "alliance")
public class Alliance extends GenericEntity {

	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String shortName;
	@Column(length = 65536)
	private String description;
	@Column
	private State deleted = State.ENABLE;
	@Column(length = 65536)
	private String url;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate = new Date();
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Corporation executorCorporation;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private CrestCharacter creatorCrestCharacter;
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "alliance")
	private List<Corporation> corporationList = new ArrayList<Corporation>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public State getDeleted() {
		return deleted;
	}

	public void setDeleted(State deleted) {
		this.deleted = deleted;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Corporation getExecutorCorporation() {
		return executorCorporation;
	}

	public void setExecutorCorporation(Corporation executorCorporation) {
		this.executorCorporation = executorCorporation;
	}

	public CrestCharacter getCreatorCrestCharacter() {
		return creatorCrestCharacter;
	}

	public void setCreatorCrestCharacter(CrestCharacter creatorCrestCharacter) {
		this.creatorCrestCharacter = creatorCrestCharacter;
	}

	public List<Corporation> getCorporationList() {
		return corporationList;
	}

	public void setCorporationList(List<Corporation> corporationList) {
		this.corporationList = corporationList;
	}
}
