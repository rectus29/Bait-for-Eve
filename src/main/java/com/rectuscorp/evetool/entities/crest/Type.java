package com.rectuscorp.evetool.entities.crest;

import com.rectuscorp.evetool.entities.core.GenericEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "type")
public class Type extends GenericEntity{

	@Column
	private String name;
	@Column(length = 65536)
	private String description;
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
	private List<Dogma> dogmaList = new ArrayList<Dogma>();

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

	public long getMass() {
		return mass;
	}

	public void setMass(long mass) {
		this.mass = mass;
	}

	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}

	public long getCapacity() {
		return capacity;
	}

	public void setCapacity(long capacity) {
		this.capacity = capacity;
	}

	public long getPortionSize() {
		return portionSize;
	}

	public void setPortionSize(long portionSize) {
		this.portionSize = portionSize;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	public long getRadius() {
		return radius;
	}

	public void setRadius(long radius) {
		this.radius = radius;
	}

	public List<Dogma> getDogmaList() {
		return dogmaList;
	}

	public void setDogmaList(List<Dogma> dogmaList) {
		this.dogmaList = dogmaList;
	}
}
