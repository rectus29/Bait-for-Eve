package com.rectuscorp.evetool.entities.crest;

import com.rectuscorp.evetool.entities.core.GenericEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sovereignty")
public class Sovereignty extends GenericEntity{
	@ManyToMany(mappedBy = "sovereigntyList")
	private List<SolarSystem> solarSystems = new ArrayList<SolarSystem>();

	public List<SolarSystem> getSolarSystems() {
		return solarSystems;
	}

	public void setSolarSystems(List<SolarSystem> solarSystems) {
		this.solarSystems = solarSystems;
	}
}
