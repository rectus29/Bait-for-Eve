package com.rectuscorp.evetool.entities.crest;

import com.rectuscorp.evetool.entities.core.GenericEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "constellation")
public class Constellation extends GenericEntity {

	@Column(nullable = false, length = 65536)
	private String name;
	@OneToOne
	private Position position;
	@OneToMany(mappedBy = "constellation")
	private List<SolarSystem> solarSystemList = new ArrayList<SolarSystem>();
	@ManyToOne
	private Region region;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public List<SolarSystem> getSolarSystemList() {
		return solarSystemList;
	}

	public void setSolarSystemList(List<SolarSystem> solarSystemList) {
		this.solarSystemList = solarSystemList;
	}
}
