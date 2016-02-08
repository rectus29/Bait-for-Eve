package com.rectuscorp.evetool.entities.crest;

import com.rectuscorp.evetool.entities.core.GenericEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "solarsystem")
public class SolarSystem extends GenericEntity{

	@Column(nullable = false, length = 65536)
	private String name;
	@Column
	private int securityStatus;
	@Column
	private String securityClass;
	@OneToMany(mappedBy = "solarSystem")
	private List<Planet> planetList = new ArrayList<Planet>();
	@ManyToMany()
	private List<Sovereignty> sovereigntyList = new ArrayList<Sovereignty>();
	@ManyToOne
	private Constellation constellation;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSecurityStatus() {
		return securityStatus;
	}

	public void setSecurityStatus(int securityStatus) {
		this.securityStatus = securityStatus;
	}

	public String getSecurityClass() {
		return securityClass;
	}

	public void setSecurityClass(String securityClass) {
		this.securityClass = securityClass;
	}

	public List<Planet> getPlanetList() {
		return planetList;
	}

	public void setPlanetList(List<Planet> planetList) {
		this.planetList = planetList;
	}

	public List<Sovereignty> getSovereigntyList() {
		return sovereigntyList;
	}

	public void setSovereigntyList(List<Sovereignty> sovereigntyList) {
		this.sovereigntyList = sovereigntyList;
	}

	public Constellation getConstellation() {
		return constellation;
	}

	public void setConstellation(Constellation constellation) {
		this.constellation = constellation;
	}
}
