package com.rectuscorp.evetool.entities.crest;

import com.rectuscorp.evetool.entities.core.GenericEntity;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Constellation.
 */
@Entity
@Table(name = "constellation")
public class Constellation extends GenericEntity {

	@Column(nullable = false, length = 65536)
	private String name;
	@OneToOne(cascade = CascadeType.ALL)
	private Position position;
	@OneToMany(mappedBy = "constellation")
	private List<SolarSystem> solarSystemList = new ArrayList<SolarSystem>();
	@ManyToOne
	private Region region;

	/**
	 * Gets name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name.
	 *
	 * @param name the name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets position.
	 *
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * Sets position.
	 *
	 * @param position the position
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * Gets solar system list.
	 *
	 * @return the solar system list
	 */
	public List<SolarSystem> getSolarSystemList() {
		return solarSystemList;
	}

	/**
	 * Sets solar system list.
	 *
	 * @param solarSystemList the solar system list
	 */
	public void setSolarSystemList(List<SolarSystem> solarSystemList) {
		this.solarSystemList = solarSystemList;
	}

	/**
	 * Gets region.
	 *
	 * @return the region
	 */
	public Region getRegion() {
		return region;
	}

	/**
	 * Sets region.
	 *
	 * @param region the region
	 */
	public void setRegion(Region region) {
		this.region = region;
	}
}
