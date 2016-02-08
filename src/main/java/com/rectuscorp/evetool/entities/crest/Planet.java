package com.rectuscorp.evetool.entities.crest;

import com.rectuscorp.evetool.entities.core.GenericEntity;

import javax.persistence.*;

@Entity
@Table(name = "planet")
public class Planet extends GenericEntity {

	@Column(nullable = false, length = 65635)
	private String name;
	@OneToOne
	private Position position;
	@ManyToOne
	private SolarSystem solarSystem;
	@ManyToOne(targetEntity = Type.class)
	private Type type;

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

	public SolarSystem getSolarSystem() {
		return solarSystem;
	}

	public void setSolarSystem(SolarSystem solarSystem) {
		this.solarSystem = solarSystem;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}
