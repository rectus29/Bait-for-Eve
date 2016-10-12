package com.rectuscorp.evetool.entities.crest;

import com.rectuscorp.evetool.entities.core.GenericEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Type.
 */
@Entity
@Table(name = "type")
public class Type extends GenericEntity {

	@Column
	private String name;
	@Column(length = 65536)
	private String description;
	@Column
	private long iconID;
	@Column
	private long mass = 0;
	@Column
	private Boolean published = false;
	@Column
	private long capacity = 0;
	@Column
	private long portionSize = 0;
	@Column
	private long volume = 0;
	@Column
	private long radius = 0;
	@OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
	private List<Dogma> attributesList = new ArrayList<Dogma>();
	@ManyToOne
	private InventoryGroup inventoryParentGroup;
	@ManyToOne
	private MarketGroup marketParentGroup;

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
	 * Gets description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets description.
	 *
	 * @param description the description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets mass.
	 *
	 * @return the mass
	 */
	public long getMass() {
		return mass;
	}

	/**
	 * Sets mass.
	 *
	 * @param mass the mass
	 */
	public void setMass(long mass) {
		this.mass = mass;
	}

	/**
	 * Gets published.
	 *
	 * @return the published
	 */
	public Boolean getPublished() {
		return published;
	}

	/**
	 * Sets published.
	 *
	 * @param published the published
	 */
	public void setPublished(Boolean published) {
		this.published = published;
	}

	/**
	 * Gets capacity.
	 *
	 * @return the capacity
	 */
	public long getCapacity() {
		return capacity;
	}

	/**
	 * Sets capacity.
	 *
	 * @param capacity the capacity
	 */
	public void setCapacity(long capacity) {
		this.capacity = capacity;
	}

	/**
	 * Gets portion size.
	 *
	 * @return the portion size
	 */
	public long getPortionSize() {
		return portionSize;
	}

	/**
	 * Sets portion size.
	 *
	 * @param portionSize the portion size
	 */
	public void setPortionSize(long portionSize) {
		this.portionSize = portionSize;
	}

	/**
	 * Gets volume.
	 *
	 * @return the volume
	 */
	public long getVolume() {
		return volume;
	}

	/**
	 * Sets volume.
	 *
	 * @param volume the volume
	 */
	public void setVolume(long volume) {
		this.volume = volume;
	}

	/**
	 * Gets radius.
	 *
	 * @return the radius
	 */
	public long getRadius() {
		return radius;
	}

	/**
	 * Sets radius.
	 *
	 * @param radius the radius
	 */
	public void setRadius(long radius) {
		this.radius = radius;
	}

	/**
	 * Gets dogma list.
	 *
	 * @return the dogma list
	 */
	public List<Dogma> getAttributesList() {
		return attributesList;
	}

	/**
	 * Sets dogma list.
	 *
	 * @param dogmaList the dogma list
	 */
	public void setAttributesList(List<Dogma> dogmaList) {
		this.attributesList = dogmaList;
	}

	/**
	 * Gets icon id.
	 *
	 * @return the icon id
	 */
	public long getIconID() {
		return iconID;
	}

	/**
	 * Sets icon id.
	 *
	 * @param iconID the icon id
	 */
	public void setIconID(long iconID) {
		this.iconID = iconID;
	}

	/**
	 * Is published boolean.
	 *
	 * @return the boolean
	 */
	public Boolean isPublished() {
		return published;
	}
}
