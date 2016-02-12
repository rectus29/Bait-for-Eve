package com.rectuscorp.evetool.entities.crest;

import com.rectuscorp.evetool.entities.core.GenericEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "character")
public class Character extends GenericEntity {

	@Column(length = 65536)
	private String name;
	@Column
	private Boolean isNPC = false;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean isNPC() {
		return isNPC;
	}

	public void setIsNPC(Boolean isNPC) {
		this.isNPC = isNPC;
	}
}
