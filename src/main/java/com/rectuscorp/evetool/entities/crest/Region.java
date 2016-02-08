package com.rectuscorp.evetool.entities.crest;

import com.rectuscorp.evetool.entities.core.GenericEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "region")
public class Region extends GenericEntity{

	@Column(nullable = false, length = 65536)
	private String name;
	@Column( length = 65536)
	private String description;
	@OneToMany(mappedBy = "region")
	private List<Constellation> constellationsList = new ArrayList<Constellation>();

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

	public List<Constellation> getConstellationsList() {
		return constellationsList;
	}

	public void setConstellationsList(List<Constellation> constellationsList) {
		this.constellationsList = constellationsList;
	}
}
