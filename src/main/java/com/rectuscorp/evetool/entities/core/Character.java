package com.rectuscorp.evetool.entities.core;

import com.rectuscorp.evetool.entities.crest.Corporation;
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
	private Date dateOfBirth = new Date();
	@ManyToOne
	private XmlApiKey apiKey;
	@ManyToOne
	private Corporation corporation;

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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date birthDay) {
		this.dateOfBirth = birthDay;
	}

	public XmlApiKey getXmlApiKey() {
		return apiKey;
	}

	public void setXmlApiKey(XmlApiKey xmlApiKey) {
		this.apiKey = xmlApiKey;
	}

	public Corporation getCorporation() {
		return corporation;
	}

	public void setCorporation(Corporation corporation) {
		this.corporation = corporation;
	}
}
