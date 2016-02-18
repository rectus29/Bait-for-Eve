package com.rectuscorp.evetool.entities.core;

import com.rectuscorp.evetool.entities.crest.SolarSystem;
import com.rectuscorp.evetool.entities.crest.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "avatar")
public class Character extends GenericEntity {

	@Column(nullable = false)
	private String name;
	@Column
	private SolarSystem location;
	@Column
	private Type ship;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthDay = new Date();
	@ManyToOne
	private ApiKey apiKey;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SolarSystem getLocation() {
		return location;
	}

	public void setLocation(SolarSystem location) {
		this.location = location;
	}

	public Type getShip() {
		return ship;
	}

	public void setShip(Type ship) {
		this.ship = ship;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public ApiKey getApiKey() {
		return apiKey;
	}

	public void setApiKey(ApiKey apiKey) {
		this.apiKey = apiKey;
	}
}
